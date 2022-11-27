#! /usr/bin/env python

#Code implemented by @Nicolas Aparicio & Daniela Ruiz

#Libraries implamentation
import os
import copy
import time
import glob
import torch
import random
import argparse
import numpy as np
from PIL import Image
import os.path as osp
import torch.nn as nn
from tqdm import tqdm
from dataset import Burned
import torch.optim as optim
import matplotlib.pyplot as plt
import torch.nn.functional as F
from torch.autograd import Variable
import torchvision.models as models
from torchvision import models, datasets, transforms

# Arguments
parser = argparse.ArgumentParser(description='PyTorch Deeplab v3 Example')
parser.add_argument('--seed', type=int, default=1, metavar='S',
                    help='random seed (default: 1)')
parser.add_argument('--batch-size', type=int, default=32, metavar='N',
                    help='input batch size for training (default: 32)')
parser.add_argument('--no-cuda', action='store_true', default=False,
                    help='disables CUDA training')
parser.add_argument('--n_classes', type=int, default=3, 
                    help="Number of classes to segment"),
parser.add_argument('--save', type=str, default='model101_CE_W.pt',
                    help='file on which to save model weights')
args = parser.parse_args()

args.cuda = not args.no_cuda and torch.cuda.is_available()

torch.manual_seed(args.seed)
if args.cuda:
    torch.cuda.manual_seed(args.seed)

kwargs = {'num_workers': 0, 'pin_memory': True} if args.cuda else {}

#images_valid = glob.glob(os.path.join('media', 'SSD6', 'naparicioc', 'Burned_Skin', 'Classification', 'Burned_Data', 'test', '*.jpg'))
images_valid = os.path.join('media', 'SSD6', 'naparicioc', 'Burned_Skin', 'Classification', 'Burned_Data', 'test', '*.jpg')

#Valid paths are prepared
testBurnedDataset = Burned('Burned_Data/test', transform_img=transforms.Compose([transforms.ToPILImage(),
                transforms.Resize((256,256)),
                transforms.ToTensor(),
                transforms.Normalize(mean = [0.485, 0.456, 0.406], std = [0.229, 0.224, 0.225])]), 
                transform_target=None)
test_loader = torch.utils.data.DataLoader(testBurnedDataset, 
                       batch_size=args.batch_size, shuffle=True, **kwargs)

#Upload Best Model
print('You are using demo.py on the best FCN model.')
model = models.resnet101(num_classes=args.n_classes)

if args.cuda:
    model.cuda()
load_model = False
if osp.exists(args.save):
    with open(args.save, 'rb') as fp:
        state = torch.load(fp)
        model.load_state_dict(state)
        load_model = True
        model_ready = True
else:
    state = model_zoo.load_url(RESNET_101)
    state = {x: state[x] for x in state if not x.startswith('fc')}
    model_state = model.state_dict()
    model_state.update(state)
    model.load_state_dict(model_state)

cp_model = copy.deepcopy(model)

#In this part we set up a weighted loss whith the metrics class
#criterion = nn.CrossEntropyLoss(ignore_index=255)
criterion = nn.CrossEntropyLoss()

def test(epoch):
    model.eval()
    test_loss = 0
    correct = 0
    for data, target, name in test_loader:
        if args.cuda:
            data, target = data.cuda(), target.cuda()
        data, target = Variable(data, volatile=True), Variable(target)
        output = model(data)
        loss = criterion(output, target)
        #loss = F.nll_loss(output, target)
        test_loss += loss
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

#We create this function to show a subplot with the original image, the prediction and the GT.
def visual_results():
    font = {'family': 'serif',
        'color':  'black',
        'weight': 'normal',
        'size': 14,
        }
    id = random.randint(0, len(images_valid))
    data, target, name = testBurnedDataset.__getitem__(id)
    if args.cuda:
            data, target = data.cuda(), target.cuda() * 255.
    data, target = Variable(data), Variable(target)
    with torch.no_grad():
        output = model(torch.unsqueeze(data,0))
    pred = F.softmax(output, dim=1).numpy()
    target = target.cpu().numpy()
    pred = np.argmax(pred, axis=1)
    path_image = os.path.join('/media/SSD6/naparicioc/Burned_Skin/Classification/'+name)
    plt.figure()
    plt.suptitle('Predicción: '+str(pred[0]+1), fontsize=18)
    plt.title('Anotación: '+str(target+1), fontsize=10)
    plt.imshow(Image.open(path_image))
    plt.axis(False)
    plt.savefig("VisualResults19.png")

if __name__ == '__main__':
    best_loss = None
    if model_ready:
        best_loss = test(0)
        visual_results()