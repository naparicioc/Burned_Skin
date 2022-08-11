from torch.utils.data import Dataset
from torchvision.io import read_image
import glob

class Burned(Dataset):
	def __init__(self, path, transform_img, transform_target):
		split = path.split('/')[-1]
		self.images = glob.glob(os.path.join(split, '*.jpg'))
		self.labels = glob.glob(os.path.join(split, '*.txt'))

	def __getitem__(self, index):
		image = read_image(self.images[index])
		label_file = self.labels[index]
		with open(label_file) as f:
			info_annotation = f.readlines()
		label = int(info_annotation[0].split(' ')[0])
		return image, label

	def __len__(self):
		return len(self.images)
