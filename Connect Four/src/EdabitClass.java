import java.util.LinkedHashSet;
import java.util.Set;

public class EdabitClass {
  public static boolean getXO (String str) {
    int x = 0;
    int o = 0;
    for (int i = 0; i < str.length(); i++) {
      if (Character.toLowerCase(str.charAt(i)) == 'x') {
        x++;
      }
      if (Character.toLowerCase(str.charAt(i)) == 'o') {
        o++;
      }
    }
    return x == o;
  }
}

