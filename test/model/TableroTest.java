package model;

import model.table.TypeBomb;
import arquitectura.Setup;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import model.table.Bomb;
import arquitectura.logicGame.BombsRandom;

public class TableroTest {

  Setup s;
  ArrayList<Bomb> bombs;

  public TableroTest() {
    s = new Setup();
  }

  @Test
  public void testRestartTablero() {
    s.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(s.getNumBombs(), TypeBomb.BOMB, bombs, s.getRow(), s.getCol());
    TableMinesweeper t1 = new TableMinesweeper(s, bombs);
    assertEquals(s.getNumBombs(), t1.getBombs().size());
    s.medium();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(s.getNumBombs(), TypeBomb.BOMB, bombs, s.getRow(), s.getCol());
    t1.restartTable(bombs);
    assertEquals(s.getNumBombs(), t1.getBombs().size());
    s.hard();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(s.getNumBombs(), TypeBomb.BOMB, bombs, s.getRow(), s.getCol());
    t1.restartTable(bombs);
    assertEquals(s.getNumBombs(), t1.getBombs().size());

  }

  @Test
  public void testAddFlag() {
    s.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(s.getNumBombs(), TypeBomb.BOMB, bombs, s.getRow(), s.getCol());
    int x = 5;
    int y = 5;
    TableMinesweeper t1 = new TableMinesweeper(s, bombs);
    t1.putFlag(x, y);
    assertEquals(t1.isFlag(x, y), true);
    x = 0;
    y = 0;
    assertEquals(t1.isFlag(x, y), false);
  }

  @Test
  public void testDisableFlag() {
    s.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(s.getNumBombs(), TypeBomb.BOMB, bombs, s.getRow(), s.getCol());
    int x = 5;
    int y = 5;
    TableMinesweeper t1 = new TableMinesweeper(s, bombs);
    t1.putFlag(x, y);
    assertEquals(t1.isFlag(x, y), true);
    t1.removeFlag(x, y);
    assertEquals(t1.isFlag(x, y), false);
  }

  @Test
  public void testDiscover() {
    s.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(s.getNumBombs(), TypeBomb.BOMB, bombs, s.getRow(), s.getCol());
    TableMinesweeper t1 = new TableMinesweeper(s, bombs);
    ArrayList<Bomb> bombas = t1.getBombs();
    TypeBomb expect = TypeBomb.BOMB;
    for (int i = 0; i < bombas.size(); i++) {
      int x = bombas.get(i).getX();
      int y = bombas.get(i).getY();
      assertEquals(t1.discoverCell(x, y), expect);
    }
  }

  @Test
  public void testDiscoverExposed() {
    s.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(s.getNumBombs(), TypeBomb.BOMB, bombs, s.getRow(), s.getCol());
    TableMinesweeper t1 = new TableMinesweeper(s, bombs);
    ArrayList<Bomb> bombas = t1.getBombs();
    boolean exposedExpect = true;
    for (int i = 0; i < bombas.size(); i++) {
      int x = bombas.get(i).getX();
      int y = bombas.get(i).getY();
      t1.discoverCell(x, y);
      assertEquals(t1.isExposed(x, y), exposedExpect);
    }
  }

  @Test
  public void testRandomedBomb() {
    s.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(s.getNumBombs(), TypeBomb.BOMB, bombs, s.getRow(), s.getCol());
    TableMinesweeper t1 = new TableMinesweeper(s, bombs);
    ArrayList<Bomb> bombs = t1.getBombs();
    boolean expectedResul = true;
    for (int i = 0; i < bombs.size(); i++) {
      int x = bombs.get(i).getX();
      int y = bombs.get(i).getY();
      assertEquals(t1.isBomb(x, y), expectedResul);
    }
  }

  @Test
  public void testCheckWin() {
    s.easy();
    bombs = new ArrayList<>();
    TableMinesweeper t1 = new TableMinesweeper(s, bombs);
    boolean expect = true;
    for (int i = 0; i < s.getRow(); i++) {
      for (int j = 0; j < s.getCol(); j++) {
        t1.discoverCell(i, j);
      }
    }
    t1.checkWin();
    assertEquals(t1.isWin(), expect);
  }
}
