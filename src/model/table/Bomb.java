package model.table;

public class Bomb {

  protected int x;
  protected int y;
  protected TypeBomb typeBomb;

  public Bomb(TypeBomb typeBomb, int x, int y) {
    this.x = x;
    this.y = y;
    this.typeBomb = typeBomb;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean isCreated(int x, int y) {
    return this.x == x && this.y == y;
  }

  public boolean isEqualsTo(Bomb a) {
    return x == a.getX() && y == a.getY();
  }

  public TypeBomb getTypeBomb() {
    return this.typeBomb;
  }
}
