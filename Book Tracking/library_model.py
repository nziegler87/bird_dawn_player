import requests
import pandas
from library_utils import remove_comma
from book import Book
from tabulate import tabulate
import subprocess

# names of where book and people csv files are stored. For books, key is ISBN-13 number. For people, key is ID number.
BOOK_CSV_FILE = "books.csv"

# Google Book API Url
GOOGLE_URL = "https://www.googleapis.com/books/v1/volumes?q=isbn:"


def initialize_dataframe():
    return pandas.read_csv(BOOK_CSV_FILE, index_col="index", dtype={"index": str,"title": str,"subtitle": str,
                                                                    "authors": str,"published": str,"isbn_10": str,
                                                                    "isbn_13": str})


def save_dataframe(dataframe):
    dataframe.to_csv(BOOK_CSV_FILE, index_label="index")


def print_book_list(dataframe):
    """
    Prints book list.
    :return: nothing
    """
    print(tabulate(dataframe, headers="keys", tablefmt="simple") + "\n")


def get_book(isbn):
    """
    Method to retrieve book data by ISBN number.

    :param isbn: a 10 or 13-digit isbn number
    :return: the book information as Book object
    """
    # retrieve book
    full_url = GOOGLE_URL + str(isbn)
    raw_book = requests.get(full_url)

    # check that it was loaded correctly
    if raw_book.status_code != 200:
        print("Error loading book.")
        return None

    if raw_book.json()["totalItems"] == 0:
        print("Book not found")
        return None

    if raw_book.json()["totalItems"] > 1:
        print("More than one book match found. Using the first match.")

    # parse data from json object
    try:
        title = raw_book.json()["items"][0]["volumeInfo"]['title']
        title = remove_comma(title)
    except KeyError:
        title = ""

    try:
        subtitle = raw_book.json()["items"][0]["volumeInfo"]['subtitle']
        subtitle = remove_comma(subtitle)
    except KeyError:
        subtitle = ""

    try:
        published_date = raw_book.json()["items"][0]["volumeInfo"]['publishedDate']
    except KeyError:
        published_date = ""

    try:
        authors = get_author_string(raw_book.json()["items"][0]["volumeInfo"]['authors'])
    except KeyError:
        authors = ""

    isbn_10 = ""
    isbn_13 = ""
    for i in range(len(raw_book.json()["items"][0]["volumeInfo"]['industryIdentifiers'])):
        if raw_book.json()["items"][0]["volumeInfo"]['industryIdentifiers'][i]["type"] == "ISBN_10":
            isbn_10 = raw_book.json()["items"][0]["volumeInfo"]['industryIdentifiers'][i]["identifier"]
        if raw_book.json()["items"][0]["volumeInfo"]['industryIdentifiers'][i]["type"] == "ISBN_13":
            isbn_13 = raw_book.json()["items"][0]["volumeInfo"]['industryIdentifiers'][i]["identifier"]

    # create book
    new_book = Book(title, subtitle, authors, isbn_10, isbn_13, published_date)

    return new_book


def add_book_to_dataframe(dataframe, new_book):
    return dataframe.append(new_book.to_dict(), ignore_index=True)


def book_exists(dataframe, isbn):
    """
    Checks to see if given ISBN is in BOOKS list.

    :param dataframe: pandas dataframe
    :param isbn: a 10 or 13 digit ISBN number
    :return: true if isbn is in BOOKS list, otherwise false
    """
    isbn = str(isbn)
    exists = isbn in dataframe.values
    return exists


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
