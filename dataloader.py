from torch.utils.data import Dataset
from torchvision.io import read_image
import glob
import os

class Burned(Dataset):
	def __init__(self, path, transform_img, transform_target):
		split = path.split('/')[-1]
		self.images = glob.glob(os.path.join(split, '*.jpg'))
		self.transform_img = transform_img
		self.transform_target = transform_target
		#self.labels = glob.glob(os.path.join(split, '*.txt')) Solo utillizaremos las imágenes

	def __getitem__(self, index):
		image = read_image(self.images[index])
		image = self.transform_img(image)
		#file_exists = os.path.exists(self.images[index].replace('.jpg', '.txt'))
		#if file_exists:
		label_file = self.images[index].replace('.jpg', '.txt')
		with open(label_file) as f:
			info_annotation = f.readlines()
		label = int(info_annotation[0].split(' ')[0])
		label = self.transform_target(label)
		return image, label

	def __len__(self):
		return len(self.images)
