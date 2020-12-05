import csv
import requests
import pandas
from library_utils import remove_comma
from book import Book
from tabulate import tabulate
from os import path

# settings for book scanner
CSV_HEADERS = {"index": str, "title": str, "subtitle": str, "authors": str, "published": str, "isbn_10": str,
               "isbn_13": str}
INDEX_COL = "index"

# Google Book API Url
GOOGLE_URL = "https://www.googleapis.com/books/v1/volumes?q=isbn:"


def check_filename(filename):
    """
    Takes in a filename and returns True if it ends with ".csv", otherwise false.

    :param filename: a filename, a string.
    :return: true if it ends with ".csv", otherwise false.
    """
    return filename[len(filename) - 4:] == ".csv"


def get_author_string(authors_list):
    """
    Takes in a list of authors strings, combines them in to a single string, and removes any commas.

    :param authors_list: a list of author names, each a string
    :return: a concatenated string of authors with no commas
    """
    authors = ""
    for i in authors_list:
        authors += i
        authors += " | "
    no_comma = remove_comma(authors)
    final_string = no_comma[0:len(no_comma) - 2]
    return final_string


def get_title(json):
    """
    Parses the book title from the json object.

    :param json: A json object, pulled from Google.
    :return: The title a string, which is blank of there is an error.
    """
    try:
        return json.json()["items"][0]["volumeInfo"]['title']
    except KeyError:
        return ""


def get_subtitle(json):
    """
    Parses the book subtitle from the json object.

    :param json: A json object, pulled from Google.
    :return: The subtitle a string, which is blank of there is an error.
    """
    try:
        return json.json()["items"][0]["volumeInfo"]['subtitle']
    except KeyError:
        return ""


def get_published_date(json):
    """
    Parses the book published date from the json object.

    :param json: A json object, pulled from Google.
    :return: The published a string, which is blank of there is an error.
    """
    try:
        return json.json()["items"][0]["volumeInfo"]['publishedDate']
    except KeyError:
        return ""


def get_authors(json):
    """
    Parses the book authors from the json object.

    :param json: A json object, pulled from Google.
    :return: The author a string, which is blank of there is an error.
    """
    try:
        return get_author_string(json.json()["items"][0]["volumeInfo"]['authors'])
    except KeyError:
        return ""


def get_isbn(json, isbn):
    """
    Parses the book isbn_10 and isbn_13 from the json object.

    :param json: A json object, pulled from Google.
    :return: A tuple is isbn numbers (isbn_10, isbn_13). If there is an error in either, the field is returned blank.
    """
    isbn_10 = ""
    isbn_13 = ""

    # pull data from Google
    for i in range(len(json.json()["items"][0]["volumeInfo"]['industryIdentifiers'])):
        if json.json()["items"][0]["volumeInfo"]['industryIdentifiers'][i]["type"] == "ISBN_10":
            isbn_10 = json.json()["items"][0]["volumeInfo"]['industryIdentifiers'][i]["identifier"]
        if json.json()["items"][0]["volumeInfo"]['industryIdentifiers'][i]["type"] == "ISBN_13":
            isbn_13 = json.json()["items"][0]["volumeInfo"]['industryIdentifiers'][i]["identifier"]

    # if google doesn't have info, use given isbn from scan
    if not isbn_10 and len(isbn) == 10:
        isbn_10 = isbn
    if not isbn_13 and len(isbn) == 13:
        isbn_13 = isbn

    return isbn_10, isbn_13


def get_book(isbn):
    """
    Uses Google book api to look up book information using isbn (10 or 13) and return a book object.

    :param isbn: an ISBN number (10 or 13)
    :return: a book object based on the ISBN number
    """
    # retrieve book
    full_url = GOOGLE_URL + str(isbn)
    raw_book = requests.get(full_url)

    # check that it was loaded correctly
    if raw_book.status_code != 200:
        raise RuntimeError("Error with API request.")

    if raw_book.json()["totalItems"] == 0:
        raise RuntimeError("Book not found on Google.")

    # parse data from json object
    title = get_title(raw_book)
    subtitle = get_title(raw_book)
    published_date = get_published_date(raw_book)
    authors = get_authors(raw_book)
    isbn_10, isbn_13 = get_isbn(raw_book, isbn)

    # create book
    return Book(title, subtitle, authors, isbn_10, isbn_13, published_date)


class LibraryModel:

    """
    A class that represents a model to store library books.
    """
    def __init__(self):
        """
        Constructor for the library model.
        """
        self.library = None
        self.library_loaded = False
        self.filename = ""

    def load_csv_file(self, filename):
        """
        Takes in a filename with .csv extension, a string, and loads that file as a pandas dataframe.

        :param filename: an existing csv filename, a string, with the .csv extension.
        :return: nothing; updates the self.library and self.filename attributes
        """
        # raise error if filename does not end in csv
        if not check_filename(filename):
            raise ValueError("Filename must end in .csv")

        self.filename = filename
        self.library = pandas.read_csv(filename, index_col=INDEX_COL, dtype=CSV_HEADERS)
        self.library_loaded = True

    def create_csv_file(self, filename):
        """
        Creates a new csv file using given filename, write the appropriate headers, and the loads the file as a pandas
        dataframe.

        :param filename: a filename, a string, with a ".csv" extension
        :return: nothing; creates the csv file and updates the self.library and self.filename attributes
        """
        # raise error if filename does not end in csv
        if not check_filename(filename):
            raise ValueError("Filename must end in .csv")

        if path.exists(filename):
            raise ValueError("File already exists.")

        with open(filename, mode="w") as outfile:
            csv_writer = csv.writer(outfile)
            csv_writer.writerow(CSV_HEADERS.keys())

        self.filename = filename
        self.library = pandas.read_csv(filename, index_col=INDEX_COL, dtype=CSV_HEADERS)

    def save_csv_file(self):
        """
        Saves pandas dataframe to csv file.
        :return: nothing
        """
        self.library.to_csv(self.filename, index_label=INDEX_COL)

    def get_book_dataframe(self):
        """
        Returns a copy of the pandas dataframe.

        :return: a copy of the pandas dataframe.
        """
        return self.library

    def add_book(self, book):
        """
        Method to retrieve book data by ISBN number.

        :param book: a book object
        :return: the book information as Book object
        """

        if not self.library_loaded:
            raise RuntimeError("Library is not initialized. Please load or create a new library.")

        self.library = self.library.append(book.to_dict(), ignore_index=True)

    def book_exists(self, isbn):
        """
        Checks to see if given ISBN is in BOOKS list.

        :param isbn: a 10 or 13 digit ISBN number
        :return: true if isbn is in BOOKS list, otherwise false
        """
        isbn = str(isbn)
        exists = isbn in self.library.isbn_10.values or isbn in self.library.isbn_13.values
        return exists

    def print_book_list(self):
        """
        Prints book list.
        :return: nothing
        """
        print(tabulate(self.library, headers="keys", tablefmt="simple") + "\n")
