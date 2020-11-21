class Book:
    """
    A class that represents a book object.
    """

    def __init__(self, title, subtitle, authors, isbn_10, isbn_13, published):
        """
        Creates a book object.

        :param title: The book's title, a string
        :param subtitle: the book's subtitle, a string
        :param authors: the book's author, a string
        :param isbn_13: the book's 13-digit isbn, a string
        :param isbn_10: the book's 10-digit isbn, a string
        :param published: the book's published date, a string as MM/DD/YYYY
        """
        self.title = title
        self.subtitle = subtitle
        self.authors = authors
        self.isbn_13 = isbn_13
        self.isbn_10 = isbn_10
        self.published = published

    def to_dict(self):
        return {"title": self.title, "subtitle": self.subtitle, "authors": self.authors,
                "published": self.published, "isbn_10": self.isbn_10, "isbn_13": self.isbn_13}
