import java.util.ArrayList;
import java.util.List;

public class LibraryModel implements ILibraryModel {
  private List<Long> loadedBooks;

  public LibraryModel() {
    loadedBooks = new ArrayList<>();
  }

  @Override
  public boolean isValid(long isbn) {


    return true;
  }
}
