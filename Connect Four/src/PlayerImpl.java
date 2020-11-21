public class PlayerImpl implements IPlayer {
  private final String name;
  private final int score;
  private final PlayerColor color;

  public PlayerImpl(String name, int score, PlayerColor color) {
    this.name = name;
    this.score = score;
    this.color = color;
  }

}
