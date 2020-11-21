import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LibraryModelTest {
  private ILibraryModel libraryModel;

  @Before
  public void setUp() {
    libraryModel = new LibraryModel();
  }

  @Test
  public void test(){
    libraryModel.isValid(34567234);
  }
}