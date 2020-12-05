from library_utils import *
from book import *
from library_model_class import get_book
import subprocess

OPEN_SAVE = ["OPEN", "NEW"]
BOOK_DETAILS = ["Title", "Subtitle", "Authors", "ISBN10", "ISBN13", "Published Date"]

OPEN_PROMPT = "\nDo you want to open an existing csv file or create a new one? Enter OPEN for open and NEW for create: "
FILENAME_PROMPT = "Enter the filename: "
FILE_LOAD_ERROR = "\nError loading file. Check that file exists. Filename must end in .csv"
CREATE_FILE_ERROR = "\nError creating file. Filename must end in .csv"
MENU_PROMPT = {"ISBN ADD": "Add books to the list using either a 10 or 13 digit ISBN number.",
               "MANUAL ADD": "Manually add a book to the list.",
               "PRINT": "Print book list.",
               "QUIT": "Save and close the book list."}
ADD_BOOK_PROMPT = "\nEnter the following information. If you don't have a piece of information, leave it blank."
BOOK_EXISTS_PROMPT = "\nA book with that ISBN is already in your library."


class LibraryController:

    def __init__(self, librarymodel):
        self.librarymodel = librarymodel

    def go(self):
        finished = False

        # does user want to open an existing file or create a new one
        while True:
            command = input(OPEN_PROMPT)
            if command not in OPEN_SAVE:
                continue
            else:
                break

        # code to open existing file
        if command == "OPEN":
            while True:
                try:
                    filename = input(FILENAME_PROMPT)
                    self.librarymodel.load_csv_file(filename)
                    break
                except ValueError:
                    print(FILE_LOAD_ERROR)

        # code to create a new file
        if command == "NEW":
            while True:
                try:
                    filename = input(FILENAME_PROMPT)
                    self.librarymodel.create_csv_file(filename)
                    break
                except ValueError:
                    print(CREATE_FILE_ERROR)

        # with file "open", let user interact with menu prompts
        while not finished:

            # show menu prompt
            while True:
                print("\nSelect one of the following commands: ")
                for k, v in MENU_PROMPT.items():
                    print("\t" + k + " - " + v)
                isbn = input("\nWhat do you want to do? ")

                if isbn not in MENU_PROMPT.keys():
                    print("\nInvalid entry")
                    continue
                else:
                    break

            # user selects quit
            if isbn == "QUIT":
                self.librarymodel.save_csv_file()
                print("\nLibrary saved.")
                break

            # user selects add
            if isbn == "MANUAL ADD":
                while True:
                    book = []
                    print(ADD_BOOK_PROMPT)

                    for i in range(len(BOOK_DETAILS)):
                        book.append(input(BOOK_DETAILS[i] + ": "))

                    if book[3] and self.librarymodel.book_exists(book[3]) or book[4] and self.librarymodel.book_exists(book[4]):
                        print(BOOK_EXISTS_PROMPT)
                        play_error()
                        continue

                    new_book = Book(book[0], book[1], book[2], book[3], book[4], book[5])

                    try:
                        self.librarymodel.add_book(new_book)
                        print("\nBook added")
                    except RuntimeError:
                        print("\nBook cannot be added.")
                        play_error()

                    while True:
                        response = input("\nDo you want to add another book? Enter Y for yes or N for no: ")

                        if response != "Y" and response != "N":
                            print("Invalid response.")
                            continue
                        else:
                            break

                    if response == "Y":
                        continue
                    else:
                        break

            if isbn == "PRINT":
                self.librarymodel.print_book_list()
                continue

            if isbn == "ISBN ADD":
                while True:
                    print("\nEnter an ISBN (10 or 13) to add it to the library. "
                          "This operation will loop until you enter QUIT.")
                    isbn = input("\nEnter ISBN: ")

                    if isbn == "QUIT":
                        break

                    if not check_valid_isbn(isbn):
                        print("\nISBN is not valid")
                        play_error()
                        continue

                    if self.librarymodel.book_exists(isbn):
                        print("\nThis book is already in the CSV file.")
                        play_error()
                        continue

                    try:
                        self.librarymodel.add_book(get_book(isbn));
                        print("\nBook added!")
                        play_success()

                    except RuntimeError:
                        print("\nBook cannot be added.")
                        play_error()


def play_error():
    subprocess.call(["afplay", "Sosumi.wav"])


def play_success():
    subprocess.call(["afplay", "temple.wav"])
