public class BoardImpl implements IBoard {
  private final int row;
  private final int col;

  public BoardImpl(int row, int col) throws IllegalArgumentException {
    if (row < 4 || col < 4) {
      throw new IllegalArgumentException("Row and col must be greater than four");
    }

    this.row = row;
    this.col = col;
  }
}
