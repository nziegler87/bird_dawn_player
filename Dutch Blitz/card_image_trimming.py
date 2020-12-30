from PIL import Image, ImageChops
import os

IMAGE_FOLDER = 'images_for_upload'
SAVE_FOLDER = './images_trimmed2/'
FILE_EXTENSION = '.jpg'


def trim(im):
    '''
    method: trim()
    does: removes extra white border around image
    param: an image
    return: cropped image
    '''
    bg = Image.new(im.mode, im.size, im.getpixel((1, 1)))
    difference = ImageChops.difference(im, bg)
    difference = ImageChops.add(difference, difference, 2.0, -100)
    bbox = difference.getbbox()
    if bbox:
        return im.crop(bbox)


for file in os.listdir('./' + IMAGE_FOLDER):
    
    # for some reason ./images_for_upload was missing, so all this adds it back in
    abs_path = os.path.abspath(file)
    abs_path = abs_path.split("/")
    abs_path.insert(-1, IMAGE_FOLDER)
    abs_path = "/".join(abs_path)

    rel_path = os.path.relpath(file)
    og_filename = os.path.splitext(rel_path)[0]

    original_image = Image.open(abs_path)
    cropped_file = trim(original_image)
    cropped_file.save(SAVE_FOLDER + og_filename + FILE_EXTENSION)
