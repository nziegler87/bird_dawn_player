from library_model import *
from library_utils import *
import subprocess


def go():
    finished = False
    library = initialize_dataframe()
    while not finished:

        isbn = input("Enter a 10 or 13 digit ISBN number, type Q to end book entry, or P to print the list: ")

        if isbn == "Q":
            save_dataframe(library)
            print("Library saved.")
            break

        if isbn == "P":
            print_book_list(library)
            continue

        if not check_valid_isbn(isbn):
            print("ISBN is not valid")
            play_error()
            continue

        if book_exists(library, isbn):
            print("This book is already in the CSV file.")
            play_error()
            continue

        book = get_book(isbn)

        if book is None:
            print("Book cannot be found.")
            play_error()
            continue

        library = add_book_to_dataframe(library, book)
        play_success()
        print_book_list(library)

def play_error():
    subprocess.call(["afplay", "Sosumi.wav"])

def play_success():
    subprocess.call(["afplay", "temple.wav"])

