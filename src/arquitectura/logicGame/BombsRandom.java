package arquitectura.logicGame;

import java.util.ArrayList;
import model.bomb.Bomb;
import model.bomb.CommonBomb;
import model.bomb.NuclearBomb;
import model.bomb.BombTest;

public class BombsRandom {
  
  public static ArrayList<Bomb> getCommonBombs(int numBombs, ArrayList<Bomb> bombs, int row, int col){
    int count = 0;
    int randx = 0;
    int randy = 0;
    while (count < numBombs) {
      randx = (int) (Math.random() * row);
      randy = (int) (Math.random() * col);
      Bomb randomBomb = new CommonBomb(randx, randy);
      if(!exists(randomBomb, bombs)){
        bombs.add(randomBomb);
        count ++;
      }
    }
    return bombs;
  }

  protected static ArrayList<Bomb> getNuclearBombs(int numBombs, ArrayList<Bomb> bombs, int row, int col){
    int count = 0;
    int randx = 0;
    int randy = 0;
    while (count < numBombs) {
      randx = (int) (Math.random() * row);
      randy = (int) (Math.random() * col);
      Bomb randomBomb = new NuclearBomb(randx, randy);
      if(!exists(randomBomb, bombs)){
        bombs.add(randomBomb);
        count ++;
      }
    }
    return bombs;
  }
  
  public static ArrayList<Bomb> getRandomBombs(int numBombs, String typeBomb, ArrayList<Bomb> bombs, int row, int col){
    int count = 0;
    int randx = 0;
    int randy = 0;
    while (count < numBombs) {
      randx = (int) (Math.random() * row);
      randy = (int) (Math.random() * col);
      Bomb randomBomb = new NuclearBomb(randx, randy);
      if(!exists(randomBomb, bombs)){
        bombs.add(randomBomb);
        count ++;
      }
    }
    return bombs;
  }
  
  private static boolean exists(Bomb randomBomb, ArrayList<Bomb> bombs){
    for(Bomb bomb : bombs){
      if(bomb.isEqualsTo(randomBomb)){
        return true;
      }
    }
    return false;
  }
          
}
