from library_controller_class import *
from library_model_class import *

if __name__ == "__main__":
    model = LibraryModel()
    controller = LibraryController(model)
    controller.go()