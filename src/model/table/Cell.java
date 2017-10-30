package model.table;

public class Cell {

  private int x;
  private int y;
  private int value;
  private boolean exposed;
  private Bomb bomb;
  private boolean flag;

  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
    this.value = 0;
    this.exposed = false;
    this.bomb = null;
    this.flag = false;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean getExposed() {
    return exposed;
  }

  public void setExposed(boolean estado) {
    this.exposed = estado;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int valor) {
    this.value = valor;
  }

  public boolean isBomb() {
    if (this.bomb == null) {
      return false;
    } else {
      return this.bomb.isCreated(this.x, this.y);
    }
  }

  public void setBomb(Bomb bomb) {
    this.bomb = bomb;
  }

  public TypeBomb getTypeBomb() {
    return this.bomb.getTypeBomb();
  }

  public boolean getFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

}
