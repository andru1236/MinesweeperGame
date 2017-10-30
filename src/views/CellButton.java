package views;

import javax.swing.JButton;
import java.awt.Point;

public class CellButton extends JButton {

  Point point;

  public CellButton(int x, int y) {
    point = new Point(x, y);
  }

  public Point getPoint() {
    return point;
  }
}
