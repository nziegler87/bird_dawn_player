import org.junit.Test;

import static org.junit.Assert.*;

public class EdabitClassTest {
  @Test
  public void testWords() {
    System.out.println(EdabitClass.countWords(new String[]{"A1", "B2"}));
  }

}