public interface IBook {

  /**
   * Returns title of the book object.
   *
   * @return book title, a string
   */
  String getTitle();

  /**
   * Returns subtitle of book object.
   *
   * @return book subtitle, a string
   */
  String getSubtitle();

  /**
   * Returns authors of book.
   *
   * @return book authors, a string.
   */
  String getAuthors();

  /**
   * Returns book object's ISBN13.
   *
   * @return the book's ISBN13, a long
   */
  long getISBN13();

  /**
   * Returns the book object's ISBN10.
   *
   * @return the book's ISBN10, a long
   */
  long getISBN10();

  /**
   * Returns the books publication date.
   *
   * @return the book's publication date, a string.
   */
  String getPublicationDate();

  /**
   * Returns a thumbnail url of the book.
   *
   * @return the book thumbnail url.
   */
  String getThumbnail();

}
