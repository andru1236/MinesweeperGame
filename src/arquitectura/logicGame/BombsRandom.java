package arquitectura.logicGame;

import java.util.ArrayList;
import model.table.Bomb;
import model.table.TypeBomb;

public class BombsRandom {

  public static ArrayList<Bomb> getRandomBombs(int numBombs, TypeBomb typeBomb, ArrayList<Bomb> bombs, int row, int col) {
    int count = 0;
    int randx = 0;
    int randy = 0;
    while (count < numBombs) {
      randx = (int) (Math.random() * row);
      randy = (int) (Math.random() * col);
      Bomb randomBomb = new Bomb(typeBomb, randx, randy);
      if (!existsBomb(randomBomb, bombs)) {
        bombs.add(randomBomb);
        count++;
      }
    }
    return bombs;
  }

  private static boolean existsBomb(Bomb randomBomb, ArrayList<Bomb> bombs) {
    for (Bomb bomb : bombs) {
      if (bomb.isEqualsTo(randomBomb)) {
        return true;
      }
    }
    return false;
  }

}
