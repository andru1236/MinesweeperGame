package arquitectura;

import arquitectura.allgames.Game;
import arquitectura.allgames.GameA;
import arquitectura.allgames.GameB;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Juego MinesweeperGame    PRESIONE 1");
    System.out.println("Juego MinesweeperGame A  PRESIONE 2");
    System.out.println("Juego MinesweeperGame B  PRESIONE 3");
    String game = input.nextLine().toUpperCase();
    switch (game) {
      case "1":
        new Game();
        break;
      case "2":
        new GameA();
        break;
      case "3":
        new GameB();
        break;
    }
  }
}
