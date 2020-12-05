from library_utils import *
from book import *
from library_model_class import get_book
import subprocess
import tkinter as tk
from tkinter.filedialog import askopenfilename, asksaveasfilename
from tkinter import messagebox

OPEN_SAVE = ["OPEN", "NEW"]
BOOK_DETAILS = ["Title", "Subtitle", "Authors", "ISBN10", "ISBN13", "Published Date"]

OPEN_PROMPT = "\nDo you want to open an existing csv file or create a new one? Enter OPEN for open and NEW for create: "
FILENAME_PROMPT = "Enter the filename: "
FILE_LOAD_ERROR = "\nError loading file. Check that file exists. Filename must end in .csv"
CREATE_FILE_ERROR = "\nError creating file. Check to see if file already exists. Filename must end in .csv"
MENU_PROMPT = {"ISBN ADD": "Add books to the list using either a 10 or 13 digit ISBN number.",
               "MANUAL ADD": "Manually add a book to the list.",
               "PRINT": "Print book list.",
               "QUIT": "Save and close the book list."}
ADD_BOOK_PROMPT = "\nEnter the following information. If you don't have a piece of information, leave it blank. " \
                  "Type :END: at any point to stop entry."
BOOK_EXISTS_PROMPT = "\nA book with that ISBN is already in your library."


class LibraryController:

    def __init__(self, librarymodel):
        self.librarymodel = librarymodel

    def new_file(self):
        while True:
            try:
                messagebox.showinfo(title="See Terminal", message="See terminal to enter filename.")
                filename = input(FILENAME_PROMPT)
                self.librarymodel.create_csv_file(filename)
                messagebox.showinfo(title="File Created", message="File created.")
                break
            except ValueError:
                messagebox.showerror(title="File Creation Error", message=CREATE_FILE_ERROR)

    def open_file(self):
        while True:
            try:
                filepath = askopenfilename(filetypes=[("Text Files", "*.txt")])

                if not filepath:
                    break

                self.librarymodel.load_csv_file(filepath)
                break

            except ValueError:
                messagebox.showerror(title="Error Opening File", message=FILE_LOAD_ERROR)

    def save_file(self):
        if self.librarymodel.filename == "":
            messagebox.showerror(title="File Save Error", message="No file has been opened.")
        else:
            self.librarymodel.save_csv_file()

    def quit(self):
        filepath = asksaveasfilename(
            defaultextension="csv",
            filetypes=[("CSV Files", "*.csv")])

        if not filepath:
            return

        self.librarymodel.filename = filepath
        self.librarymodel.save_csv_file()
        messagebox.showinfo(title="File Saved", message="File Saved!")

    def manual_add(self):
        while True:
            book = []
            print(ADD_BOOK_PROMPT)

            # get book details from user
            for i in range(len(BOOK_DETAILS)):
                entry = input(BOOK_DETAILS[i] + ": ")

                if entry == ":END:":
                    return

                book.append(entry)

            # if user enters an ISBN, check to see if it exists in dataframe
            if (book[3] and self.librarymodel.book_exists(book[3])) \
                    or (book[4] and self.librarymodel.book_exists(book[4])):
                print(BOOK_EXISTS_PROMPT)
                play_error()
                continue

            # create book object
            new_book = Book(book[0], book[1], book[2], book[3], book[4], book[5])

            if new_book.is_empty():
                print("\nBook was blank and was not added.")
                play_error()
                continue

            try:
                self.librarymodel.add_book(new_book)
                print("\nBook added")
            except RuntimeError:
                print("\nBook cannot be added.")
                play_error()

            # see if they want to add another book
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

    def print(self):
        self.librarymodel.print_book_list()

    def isbn_add(self):
        while True:
            print("\nEnter an ISBN (10 or 13) to add it to the library. "
                  "This operation will loop until you enter :END:.")
            isbn = input("\nEnter ISBN: ")

            if isbn == ":END:":
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

    def go(self):

        # create and configure window
        window = tk.Tk()
        window.title("Save Your Books!")

        # create row/column for output display
        window.rowconfigure(1, minsize=800, weight=1)
        window.columnconfigure(0, minsize=800, weight=1)

        # create area where library information will be displayed
        text_display = tk.Text(master=window)

        # create area for button commands
        buttons_frame = tk.Frame(master=window, borderwidth=1, relief="raised")

        # add buttons
        new_button = tk.Button(master=buttons_frame, text="Create New CSV File", command=self.new_file)
        open_button = tk.Button(master=buttons_frame, text="Open an Existing CSV File", command=self.open_file)
        save_button = tk.Button(master=buttons_frame, text="Save", command=self.save_file)
        save_as_button = tk.Button(master=buttons_frame, text="Save As", command=self.quit)
        new_button.grid(row=0, column=0, sticky="ew", padx=10, pady=10)
        open_button.grid(row=0, column=1, sticky="ew", padx=10, pady=10)
        save_button.grid(row=0, column=2, sticky="ew", padx=10, pady=10)
        save_as_button.grid(row=0, column=3, sticky="ew", padx=10, pady=10)

        text_display.grid(row=1, column=0, sticky="nsew")
        buttons_frame.grid(row=0, column=0, sticky="nsew")

        # with file "open", let user interact with menu prompts
        while True:
            isbn = show_menu()

            # user selects quit
            if isbn == "QUIT":
                self.quit()
                break

            # user selects add
            if isbn == "MANUAL ADD":
                self.manual_add()
                continue

            if isbn == "PRINT":
                self.print()
                continue

            if isbn == "ISBN ADD":
                self.isbn_add()
                continue


def play_error():
    subprocess.call(["afplay", "Sosumi.wav"])


def play_success():
    subprocess.call(["afplay", "temple.wav"])
