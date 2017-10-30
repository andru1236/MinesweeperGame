package arquitectura;

public enum BoardSize {
  EASY (10, 10, 300, 346),
  MEDIUM (16, 16, 496, 540),
  HARD(16, 30, 780, 492);

  private final int row;
  private final int col;
  private final int sizeX;
  private final int sizeY;

  BoardSize(int row, int col, int sizeX, int sizeY) {
    this.row = row;
    this.col = col;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public int getSizeX() {
    return sizeX;
  }

  public int getSizeY() {
    return sizeY;
  }
}
