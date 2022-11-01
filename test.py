#! /usr/bin/env python

import time
import argparse
import os.path as osp

import copy
import torch
import torch.nn as nn
import torch.optim as optim
import torch.nn.functional as F
from torch.autograd import Variable
import torch.utils.model_zoo as model_zoo
from torchvision import datasets, transforms, models
from dataset import Burned
from alexnet_pytorch import AlexNet

RESNET_101 = 'https://download.pytorch.org/models/resnet101-5d3b4d8f.pth'
RESNET_18 = 'https://download.pytorch.org/models/resnet18-5c106cde.pth'

parser = argparse.ArgumentParser(description='PyTorch MNIST Example')
parser.add_argument('--batch-size', type=int, default=64, metavar='N',
                    help='input batch size for training (default: 64)')
parser.add_argument('--test-batch-size', type=int, default=1000, metavar='N',
                    help='input batch size for testing (default: 1000)')
parser.add_argument('--epochs', type=int, default=10, metavar='N',
                    help='number of epochs to train (default: 10)')
parser.add_argument('--lr', type=float, default=0.01, metavar='LR',
                    help='learning rate (default: 0.01)')
parser.add_argument('--momentum', type=float, default=0.5, metavar='M',
                    help='SGD momentum (default: 0.5)')
parser.add_argument('--gamma', type=float, default=2, metavar='M',
                    help='learning rate decay factor (default: 0.5)')
parser.add_argument('--no-cuda', action='store_true', default=False,
                    help='disables CUDA training')
parser.add_argument('--seed', type=int, default=1, metavar='S',
                    help='random seed (default: 1)')
parser.add_argument('--log-interval', type=int, default=10, metavar='N',
                    help='how many batches to wait before '
                         'logging training status')
parser.add_argument('--save', type=str, default='model.pt',
                    help='file on which to save model weights')

args = parser.parse_args()
args.cuda = not args.no_cuda and torch.cuda.is_available()

torch.manual_seed(args.seed)
if args.cuda:
    torch.cuda.manual_seed(args.seed)


kwargs = {'num_workers': 1, 'pin_memory': True} if args.cuda else {}

test_loader = torch.utils.data.DataLoader(Burned('Burned_Data/test', transform_img=transforms.Compose([transforms.ToPILImage(),
                transforms.Resize((256,256)),
                transforms.ToTensor()]), 
                       transform_target=None), 
                       batch_size=args.batch_size, shuffle=True, **kwargs)

model = AlexNet.from_pretrained('alexnet', num_classes=3)
cp_model = copy.deepcopy(model)

# required_grad attribute 
for param in model.parameters():
    param.requires_grad = True

if args.cuda:
    model.cuda()

optimizer = optim.SGD(model.parameters(), lr=args.lr, momentum=args.momentum)

def test(epoch):
    model.eval()
    test_loss = 0
    correct = 0
    for data, target in test_loader:
        if args.cuda:
            data, target = data.cuda(), target.cuda()
        data, target = Variable(data, volatile=True), Variable(target)
        output = model(data)
        test_loss += F.nll_loss(output, target).data
        # get the index of the max log-probability
        pred = output.data.max(1)[1]
        correct += pred.eq(target.data).cpu().sum()

    test_loss = test_loss
    # loss function already averages over batch size
    test_loss /= len(test_loader)
    acccuracy = 100. * correct / len(test_loader.dataset)
    print('\nTest set: Average loss: {:.4f}, '
          'Accuracy: {}/{} ({:.0f}%)\n'.format(test_loss,
                                               correct,
                                               len(test_loader.dataset),
                                               acccuracy))
    return test_loss


def adjust_learning_rate(optimizer, gamma, step):
    """Sets the learning rate to the initial LR decayed
       by 10 at every specified step
       Adapted from PyTorch Imagenet example:
       https://github.com/pytorch/examples/blob/master/imagenet/main.py
    """
    lr = args.lr * (gamma ** (step))
    for param_group in optimizer.param_groups:
        param_group['lr'] = lr


if __name__ == '__main__':
    best_loss = test(0)