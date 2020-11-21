public class Book implements IBook {
  private final String title;
  private final String subtitle;
  private final String authors;
  private final long ISBN10;
  private final long ISBN13;
  private final String publicationDate;
  private final String thumbnailURL;

  /**
   * Constructs a book object.
   *
   * @param title book title, a string
   * @param subtitle book subtitle, a string
   * @param authors book authors, a string
   * @param ISBN10 book ISBN10, a long
   * @param ISBN13 book ISBN13, a long
   * @param publicationDate book publication date, a string
   * @param thumbnailURL book thumbnail URL, a string
   */
  public Book(String title, String subtitle, String authors, long ISBN10, long ISBN13,
              String publicationDate, String thumbnailURL) {
    this.title = title;
    this.subtitle = subtitle;
    this.authors = authors;
    this.ISBN10 = ISBN10;
    this.ISBN13 = ISBN13;
    this.publicationDate = publicationDate;
    this.thumbnailURL = thumbnailURL;
  }

  /**
   * Returns title of the book object.
   *
   * @return book title, a string
   */
  @Override
  public String getTitle() {
    return this.title;
  }

  /**
   * Returns subtitle of book object.
   *
   * @return book subtitle, a string
   */
  @Override
  public String getSubtitle() {
    return this.subtitle;
  }

  /**
   * Returns authors of book.
   *
   * @return book authors, a string.
   */
  @Override
  public String getAuthors() {
    return this.authors;
  }

  /**
   * Returns book object's ISBN13.
   *
   * @return the book's ISBN13, a long
   */
  @Override
  public long getISBN13() {
    return this.ISBN13;
  }

  /**
   * Returns the book object's ISBN10.
   *
   * @return the book's ISBN10, a long
   */
  @Override
  public long getISBN10() {
    return this.ISBN10;
  }

  /**
   * Returns the books publication date.
   *
   * @return the book's publication date, a string.
   */
  @Override
  public String getPublicationDate() {
    return this.publicationDate;
  }

  /**
   * Returns a thumbnail url of the book.
   *
   * @return the book thumbnail url.
   */
  @Override
  public String getThumbnail() {
    return this.thumbnailURL;
  }
}
