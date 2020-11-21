public class ConnectFourModelImpl implements IConnectFourModel {
  private final IBoard board;
  private IPlayer currentPlayer;

  public ConnectFourModelImpl(IBoard board) {
    this.board = board;
  }
}
