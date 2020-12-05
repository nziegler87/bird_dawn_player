from json.decoder import JSONDecodeError

from library_utils import check_valid_isbn
from OpenLibraryAPI import get_book

while True:
    isbn = input("Scan book: ")
    if check_valid_isbn(isbn):
        try:
            print("Title: " + get_book(isbn).json()["title"] + "\n")
        except JSONDecodeError:
            print("Error getting book information.")

    else:
        print("Not a valid ISBN")
