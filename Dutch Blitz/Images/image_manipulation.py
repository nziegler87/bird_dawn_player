import os

count = 1
file_count = 11
for file in os.listdir('./images'):
    name_search = f'./images/GREEN-RED-{file_count} (dragged).tiff'
    if os.path.isfile(name_search):
        # print('yes' + file)
        rename_file = "./images/GREEN-" + str(count) + ".tiff"
        os.rename(name_search, rename_file)
        file_count += 1
        count += 1
