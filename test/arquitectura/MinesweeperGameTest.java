package arquitectura;

import arquitectura.logicGame.BombsRandom;
import arquitectura.logicGame.MinesweeperGame;
import java.util.ArrayList;
import model.table.Bomb;
import model.TableMinesweeper;
import model.table.TypeBomb;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MinesweeperGameTest {

  TableMinesweeper model;
  MinesweeperGame game;
  Setup setup;
  ArrayList<Bomb> bombs;

  @Test
  public void testPutFlag() {
    setup = new Setup();
    setup.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    boolean expectedResult = false;
    int randx = (int) (Math.random() * (setup.getRow()));
    int randy = (int) (Math.random() * (setup.getCol()));
    assertEquals(expectedResult, model.isFlag(randx, randy));
    expectedResult = true;
    game.putFlag(randx, randy);
    assertEquals(expectedResult, model.isFlag(randx, randy));
    game.putFlag(randx, randy);
    expectedResult = false;
    assertEquals(expectedResult, model.isFlag(randx, randy));

    setup.medium();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    expectedResult = false;
    randx = (int) (Math.random() * (setup.getRow()));
    randy = (int) (Math.random() * (setup.getCol()));
    assertEquals(expectedResult, model.isFlag(randx, randy));
    expectedResult = true;
    game.putFlag(randx, randy);
    assertEquals(expectedResult, model.isFlag(randx, randy));
    game.putFlag(randx, randy);
    expectedResult = false;
    assertEquals(expectedResult, model.isFlag(randx, randy));

    setup.hard();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    expectedResult = false;
    randx = (int) (Math.random() * (setup.getRow()));
    randy = (int) (Math.random() * (setup.getCol()));
    assertEquals(expectedResult, model.isFlag(randx, randy));
    expectedResult = true;
    game.putFlag(randx, randy);
    assertEquals(expectedResult, model.isFlag(randx, randy));
    game.putFlag(randx, randy);
    expectedResult = false;
    assertEquals(expectedResult, model.isFlag(randx, randy));
  }

  @Test
  public void testUnderMine() {
    setup = new Setup();
    setup.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    int randx = (int) (Math.random() * (setup.getRow()));
    int randy = (int) (Math.random() * (setup.getCol()));
    boolean expectedResult = false;
    assertEquals(expectedResult, model.isExposed(randx, randy));

    game.discoverCell(randx, randy);
    expectedResult = true;
    assertEquals(expectedResult, model.isExposed(randx, randy));

    setup.medium();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    randx = (int) (Math.random() * (setup.getRow()));
    randy = (int) (Math.random() * (setup.getCol()));
    expectedResult = false;
    assertEquals(expectedResult, model.isExposed(randx, randy));

    game.discoverCell(randx, randy);
    expectedResult = true;
    assertEquals(expectedResult, model.isExposed(randx, randy));

    setup.hard();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    randx = (int) (Math.random() * (setup.getRow()));
    randy = (int) (Math.random() * (setup.getCol()));
    expectedResult = false;
    assertEquals(expectedResult, model.isExposed(randx, randy));

    game.discoverCell(randx, randy);
    expectedResult = true;
    assertEquals(expectedResult, model.isExposed(randx, randy));
  }

  @Test
  public void testGameover() {
    setup = new Setup();
    setup.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    boolean expectedResult = false;
    assertEquals(model.isLose(), expectedResult);

    Bomb fristBomb = model.getBombs().get(0);
    int x = fristBomb.getX();
    int y = fristBomb.getY();
    game.discoverCell(x, y);
    expectedResult = true;
    assertEquals(model.isLose(), expectedResult);

    setup.medium();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    expectedResult = false;
    assertEquals(model.isLose(), expectedResult);

    fristBomb = model.getBombs().get(0);
    x = fristBomb.getX();
    y = fristBomb.getY();
    game.discoverCell(x, y);
    expectedResult = true;
    assertEquals(model.isLose(), expectedResult);

    setup.hard();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    expectedResult = false;
    assertEquals(model.isLose(), expectedResult);

    fristBomb = model.getBombs().get(0);
    x = fristBomb.getX();
    y = fristBomb.getY();
    game.discoverCell(x, y);
    expectedResult = true;
    assertEquals(model.isLose(), expectedResult);
  }

  @Test
  public void testBlockGame() {
    setup = new Setup();
    setup.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    boolean expectedResult = false;
    assertEquals(expectedResult, model.isBlock());

    expectedResult = true;
    Bomb fristBomb = model.getBombs().get(0);
    int x = fristBomb.getX();
    int y = fristBomb.getY();
    game.discoverCell(x, y);
    assertEquals(expectedResult, model.isBlock());

    setup.medium();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    expectedResult = false;
    assertEquals(expectedResult, model.isBlock());

    expectedResult = true;
    fristBomb = model.getBombs().get(0);
    x = fristBomb.getX();
    y = fristBomb.getY();
    game.discoverCell(x, y);
    assertEquals(expectedResult, model.isBlock());

    setup.hard();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    expectedResult = false;
    assertEquals(expectedResult, model.isBlock());

    expectedResult = true;
    fristBomb = model.getBombs().get(0);
    x = fristBomb.getX();
    y = fristBomb.getY();
    game.discoverCell(x, y);
    assertEquals(expectedResult, model.isBlock());

  }

  @Test
  public void testWinGame() {
    setup = new Setup();
    setup.easy();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    boolean expectedResult = false;
    assertEquals(expectedResult, model.isWin());

    expectedResult = false;
    boolean block = false;
    boolean lose = false;

    ArrayList<Bomb> bombsTable = model.getBombs();
    for (Bomb bomb : bombsTable) {
      int x = bomb.getX();
      int y = bomb.getY();
      game.putFlag(x, y);
    }

    assertEquals(expectedResult, model.isWin());
    assertEquals(lose, model.isLose());
    assertEquals(block, model.isBlock());

    for (int i = 0; i < setup.getRow(); i++) {
      for (int j = 0; j < setup.getCol(); j++) {
        game.discoverCell(i, j);
      }
    }

    expectedResult = true;
    block = true;
    lose = false;

    assertEquals(expectedResult, model.isWin());
    assertEquals(block, model.isBlock());
    assertEquals(lose, model.isLose());

    setup.hard();
    bombs = new ArrayList<>();
    bombs = BombsRandom.getRandomBombs(setup.getNumBombs(), TypeBomb.BOMB, bombs, setup.getRow(), setup.getCol());
    model = new TableMinesweeper(setup, bombs);
    game = new MinesweeperGame(model);
    expectedResult = false;
    assertEquals(expectedResult, model.isWin());

    expectedResult = false;
    block = false;
    lose = false;

    bombsTable = model.getBombs();
    for (Bomb bomb : bombsTable) {
      int x = bomb.getX();
      int y = bomb.getY();
      game.putFlag(x, y);
    }

    assertEquals(expectedResult, model.isWin());
    assertEquals(lose, model.isLose());
    assertEquals(block, model.isBlock());

    for (int i = 0; i < setup.getRow(); i++) {
      for (int j = 0; j < setup.getCol(); j++) {
        game.discoverCell(i, j);
      }
    }

    expectedResult = true;
    block = true;
    lose = false;

    assertEquals(expectedResult, model.isWin());
    assertEquals(block, model.isBlock());
    assertEquals(lose, model.isLose());
  }

}
