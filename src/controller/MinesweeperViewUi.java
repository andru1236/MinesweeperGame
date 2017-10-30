package controller;

import arquitectura.logicGame.LogicGame;
import arquitectura.Setup;
import views.CellButton;
import views.Ui;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MinesweeperViewUi extends Controller implements MouseListener {

  private Ui view;

  public MinesweeperViewUi(LogicGame game, Setup setup) {
    this.game = game;
    this.setup = setup;
    view = new Ui(this.setup);
    view.addController(this);
    view.addListenerController(this);
    view.setGamePanel(game.nameGame, game.modality, "" + game.valueModality);
    game.addObservertoTable(view);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Point point = ((CellButton) e.getSource()).getPoint();
    int x = (int) point.getX();
    int y = (int) point.getY();
    catchMove(e.getButton(), x, y);
  }

  private void catchMove(int button, int x, int y) {
    switch (button) {
      case MouseEvent.BUTTON1:
        play(UNDER_MINE, x, y);
        break;
      case MouseEvent.BUTTON3:
        play(FLAG, x, y);
        break;

      case MouseEvent.BUTTON2:
        play(2, x, y);
        break;
    }
  }

  @Override
  public void restart() {
    game.restartGame();
    view.setGamePanel(game.nameGame, game.modality, "" + game.valueModality);
  }

  @Override
  public void mousePressed(MouseEvent me) {
  }

  @Override
  public void mouseReleased(MouseEvent me) {
  }

  @Override
  public void mouseEntered(MouseEvent me) {
  }

  @Override
  public void mouseExited(MouseEvent me) {
  }

}
