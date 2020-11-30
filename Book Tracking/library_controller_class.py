from library_utils import *
import subprocess


class LibraryController:

    def __init__(self, librarymodel):
        self.librarymodel = librarymodel

    def go(self):
        finished = False

        while True:
            try:
                filename = input("Enter the filename: ")
                self.librarymodel.load_csv_file(filename)
                break
            except ValueError:
                print("Filename must end in .csv")

        while not finished:

            isbn = input("Enter a 10 or 13 digit ISBN number to add it to the list. Type P to print the library list. "
                         "Type Q to end book entry.\n")

            if isbn == "Q":
                self.librarymodel.save_csv_file()
                print("Library saved.")
                break

            if isbn == "P":
                self.librarymodel.print_book_list()
                continue

            if not check_valid_isbn(isbn):
                print("ISBN is not valid")
                play_error()
                continue

            if self.librarymodel.book_exists(isbn):
                print("This book is already in the CSV file.")
                play_error()
                continue

            try:
                self.librarymodel.add_book(isbn)
                self.librarymodel.print_book_list()
                play_success()

            except RuntimeError:
                print("Book cannot be added.")
                play_error()


def play_error():
    subprocess.call(["afplay", "Sosumi.wav"])


def play_success():
    subprocess.call(["afplay", "temple.wav"])
