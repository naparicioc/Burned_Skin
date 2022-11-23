import matplotlib.pyplot as plt

loss_train18 = [1.112409, 1.079900, 1.048669, 1.018681, 0.989895, 0.962273, 0.935773, 0.910325, 0.885867, 0.862364, 0.839757, 0.818000, 0.797041, 0.776826, 0.757314, 0.738466, 0.718366, 0.698858, 0.679919, 0.661524, ]
loss_test18 = [1.1498, 1.1355, 1.1211, 1.1057, 1.0901, 1.0747, 1.0594, 1.0440, 1.0290, 1.0141,  0.9997, 0.9860, 0.9730, 0.9605, 0.9487, 0.9367, 0.9252, 0.9141, 0.9035, 0.8935]
loss_train101 = [1.0846, 1.063196, 1.022073, 0.983293, 0.946530, 0.911530, 0.878004, 0.845718, 0.814514, 0.784215, 0.754697, 0.725844, 0.697579, 0.669870, 0.642689, 0.616038, 0.589886, 0.564218, 0.539069, 0.514441, ]
loss_test101 = [1.0825, 1.0658, 1.0485, 1.0309, 1.0134, 0.9966, 0.9802, 0.9640, 0.9481, 0.9323, 0.9170, 0.9021, 0.8878, 0.8741, 0.8610, 0.8484, 0.8362, 0.8245, 0.8131, 0.8019]
acc_18 = [0.33, 0.36, 0.35, 0.36, 0.38, 0.41, 0.42, 0.44, 0.48, 0.48, 0.50, 0.55, 0.56, 0.56, 0.57, 0.57, 0.57, 0.58, 0.59, 0.60, 0.61]
acc_101 = [0.35, 0.38, 0.42, 0.45, 0.51, 0.54, 0.59, 0.63, 0.67, 0.68, 0.70, 0.69, 0.71, 0.71, 0.71, 0.70, 0.72, 0.73, 0.72, 0.72]

plt.figure()
plt.style.use('ggplot')
plt.subplot(211)
#plt.title('Comportamiento del Loss a través de las épocas')
plt.plot(loss_train18, '--', color='rebeccapurple', label=r'$ResNet_{50}$ Train Loss')
plt.plot(loss_test18, color='rebeccapurple', label=r'$ResNet_{50}$ Test Loss')  
plt.plot(loss_train101, '--', color='deepskyblue', label=r'$ResNet_{101}$ Train Loss')
plt.plot(loss_test101, color='deepskyblue', label=r'$ResNet_{101}$ Test Loss') 
plt.ylabel('Loss') 
plt.legend(loc = 'best', facecolor="white", edgecolor='black')
plt.subplot(212)
#plt.title('Comportamiento de la Precisión a través de las épocas')
plt.plot(acc_18, color='rebeccapurple', label=r'$ResNet_{50}$ Acc')
plt.plot(acc_101, color='deepskyblue', label=r'$ResNet_{101}$ Acc')
plt.legend(loc = 'best', facecolor="white", edgecolor='black')
plt.ylabel('Precisión')
plt.xlabel('Épocas')
plt.show()