package arquitectura;

public class Setup {

  public static final int EASY = 10;
  public static final int MEDIUM = 40;
  public static final int HARD = 99;
  private int numBombs;
  private int row;
  private int col;
  private BoardSize size;

  public Setup() {
    hard();
    size = BoardSize.HARD;
  }

  public void easy() {
    size = BoardSize.EASY;
    modifyAttrib(size, EASY);

  }

  public void medium() {
    size = BoardSize.MEDIUM;
    modifyAttrib(size, MEDIUM);
  }

  public void hard() {
    size = BoardSize.HARD;
    modifyAttrib(size, HARD);
  }

  private void modifyAttrib(BoardSize size, int numBombs) {
    this.row = size.getRow();
    this.col = size.getCol();
    this.numBombs = numBombs;
  }

  public BoardSize getBoardSize() {
    return size;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public int getNumBombs() {
    return numBombs;
  }

}
