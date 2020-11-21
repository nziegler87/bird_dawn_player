public interface IBookKeeperModel {

  void addBook(long ... isbn);

  void retrieveBook(long isbn);
}
