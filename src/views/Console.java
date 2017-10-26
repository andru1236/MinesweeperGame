package views;

import arquitectura.Setup;
import model.table.Cell;
import model.table.TypeBomb;

public class Console implements View{
  
  private int row;
  private int col;
  private String[][] cells;
  private Setup setup;

  public Console(Setup setup) {
    this.setup = setup;
    this.row = setup.getRow();
    this.col = setup.getCol();
    cells = new String[row][col];
    restartGame();
  }

  public void show() {
    for(String[] cell : cells){
      for(String c : cell){
        System.out.print(c);
      }
      System.out.println("");
    }
  }

  @Override
  public void restartGame() {
    this.row = setup.getRow();
    this.col = setup.getCol();
    this.cells = new String[row][col];
    for (int x = 0; x < row; x++) {
      for (int y = 0; y < col; y++) {
        cells[x][y] = " -";
      }
    }
  }

  public void showCommands(){
    System.out.println("Cambiar Dificultad E = EASY, M = MEDIUM, H = HARD");
    System.out.println("Colocar y quitar BANDERA= F x y (Posiciones del tablero)");
    System.out.println("Descubrir campo = P x y (Posiciones del tablero)");
    System.out.println("Finalizar juego = Q");
  }
     
  @Override
  public void updateCellFlag(Cell cell) {
    if(cell.getFlag()){
      cells[cell.getX()][cell.getY()] = " >";
    }
    else{
      cells[cell.getX()][cell.getY()] = " -";
    }
    showCommands();
    show();
  }
  
  @Override
  public void updateCellDiscover(Cell cell) {
    cells[cell.getX()][cell.getY()] = " "+cell.getValue();
    showCommands();
    show();
  }
  
  @Override
  public void updateCellBomb(Cell cell){
    TypeBomb typeBomb = cell.getTypeBomb();
    switch (typeBomb){
      case BOMB:
        cells[cell.getX()][cell.getY()] = " *";
        break;
      case BOMB_A:
        cells[cell.getX()][cell.getY()] = " +";        
        break;
      case BOMB_B:
        cells[cell.getX()][cell.getY()] = " +";        
        break;
    }
    showCommands();
    show();
  }
  
  @Override
  public void updateLose(){
    System.out.println("YOU LOST");
    show();
    showCommands();
  }
  
  @Override
  public void updateWin(){
    System.out.println("YOU WIN");
    show();
    showCommands();
  }

  @Override
  public void updateReset() {
    restartGame();
    show();
    showCommands();
  }

  @Override
  public void updateScore(int score) {
    showCommands();    
    System.out.println("SCORE " + score);
  }

}
