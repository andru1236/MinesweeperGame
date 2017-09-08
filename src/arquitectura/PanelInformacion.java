package arquitectura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;

public class PanelInformacion extends Panel {

  private Label bombs;
  private Label timeRemaning;
  private Label ng;
  private Panel p3;
  private PanelBotones pb;

  public PanelInformacion(int bombsremaining) {
    init(bombsremaining);
  }

  public void addObservador(Observador o) {
    pb.addObservador(o);
  }

  public void actualizarLabelBombas(int bombas) {
    bombs.setText(Integer.toString(bombas));
  }

  private void init(int bombsremaining) {
    setLayout(new BorderLayout());
    pb = new PanelBotones();
    timeRemaning = new Label("");
    ng = new Label("New Game");
    bombs = new Label(Integer.toString(bombsremaining));
    this.add(bombs, "West");
    this.add(pb, "Center");
    p3 = new Panel();
    p3.add(ng);
    this.add(p3, "North");
    ng.setBackground(Color.LIGHT_GRAY);
    ng.setForeground(Color.BLACK);
    bombs.setBackground(Color.LIGHT_GRAY);
    bombs.setForeground(Color.WHITE);
  }
}
