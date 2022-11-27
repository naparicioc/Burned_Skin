#! /usr/bin/env python

#Code implemented by @BIT

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
from torchvision.io import read_image
from torchvision import models, datasets, transforms

# Arguments
parser = argparse.ArgumentParser(description='BURNApp Parser for Burned Classification')
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

#Upload Best Model
model = models.resnet101(num_classes=args.n_classes)

if args.cuda:
    model.cuda()
load_model = False

with open(args.save, 'rb') as fp: #Instead of args.save, you can upload the name of the .pt file -it means tour model-
    state = torch.load(fp)
    model.load_state_dict(state)
    load_model = True
    model_ready = True

cp_model = copy.deepcopy(model)
criterion = nn.CrossEntropyLoss() #The loss criterion is defined 

def BIT_prediction(image_path: str) -> int:
    image = read_image(image_path) #We are reading the image path
    transforms_pred = transforms.Compose([transforms.ToPILImage(),
                transforms.Resize((256,256)),
                transforms.ToTensor(),
                transforms.Normalize(mean = [0.485, 0.456, 0.406], std = [0.229, 0.224, 0.225])]) #We transfor the image
    image = transforms_pred(image)
    if args.cuda:
            image = image.cuda()
    image = Variable(image)
    with torch.no_grad():
        output = model(torch.unsqueeze(image,0))
    breakpoint()
    pred = F.softmax(output, dim=1).numpy()
    pred = np.argmax(pred, axis=1)
    class_type = int(pred)+1
    print(class_type)
    return class_type

if __name__ == '__main__':
    if model_ready:
        BIT_prediction(image_path='/media/SSD6/naparicioc/Burned_Skin/Classification/Burned_Data/train/img2.jpg')