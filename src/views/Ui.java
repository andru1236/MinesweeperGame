package views;

import arquitectura.BoardSize;
import arquitectura.Setup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Controller;
import java.awt.Insets;
import model.table.Cell;
import model.table.TypeBomb;

public class Ui extends JFrame implements MouseListener, View {

  private CellButton table[][];
  private int row;
  private int col;
  private int sizeX;
  private int sizeY;
  private JPanel boardPanel;
  private JPanel headerPanel;
  private JPanel buttonPanel;
  private JPanel newGamePanel;
  private JLabel modalityName;
  private JLabel valueModality;
  private JLabel nameGameLabel;
  private JLabel remainingBombsLabel;
  private JLabel remainingTimeLabel;
  private Time remainingTime;
  private JButton easyButton;
  private JButton mediumButton;
  private JButton hardButton;
  private GridLayout minesBoard;

  private Controller controller;
  private Setup setup;

  public Ui(Setup setup) {
    this.setup = setup;
    init();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
  }

  public void init() {
    loadBoardSettingsFactory(setup.getBoardSize());

    newGamePanel = new JPanel();
    nameGameLabel = new JLabel("New Game");
    modalityName = new JLabel("");
    valueModality = new JLabel("");
    nameGameLabel.setBackground(Color.lightGray);
    nameGameLabel.setForeground(Color.black);
    newGamePanel.add(nameGameLabel);
    newGamePanel.add(modalityName);
    newGamePanel.add(valueModality);

    buttonPanel = new JPanel();
    easyButton = new JButton("Easy");
    easyButton.addMouseListener(this);
    mediumButton = new JButton("Medium");
    mediumButton.addMouseListener(this);
    hardButton = new JButton("Hard");
    hardButton.addMouseListener(this);
    buttonPanel.add(easyButton);
    buttonPanel.add(mediumButton);
    buttonPanel.add(hardButton);

    remainingBombsLabel = new JLabel("" + setup.getNumBombs());
    remainingBombsLabel.setOpaque(true);

    remainingTimeLabel = new JLabel("");
    remainingTimeLabel.setOpaque(true);
    remainingTime = new Time(remainingTimeLabel);

    headerPanel = new JPanel(new BorderLayout());
    headerPanel.add(newGamePanel, "North");
    headerPanel.add(remainingBombsLabel, "West");
    headerPanel.add(buttonPanel, "Center");
    headerPanel.add(remainingTimeLabel, "East");

    headerPanel.setBackground(Color.lightGray);

    minesBoard = new GridLayout(row, col);
    boardPanel = new JPanel(minesBoard);

    initializeBoard();

    setLayout(new BorderLayout());
    add(headerPanel, "North");
    add(boardPanel, "Center");
    setSize(sizeX, sizeY);
  }

  private void loadBoardSettingsFactory(BoardSize boardSize) {
    row = boardSize.getRow();
    col = boardSize.getCol();
    sizeX = boardSize.getSizeX();
    sizeY = boardSize.getSizeY();
  }

  private void initializeBoard() {
    table = new CellButton[row][col];
    for (int x = 0; x < row; x++) {
      for (int y = 0; y < col; y++) {
        table[x][y] = new CellButton(x, y);
        table[x][y].setOpaque(true);
        table[x][y].setEnabled(true);
        table[x][y].setText("");
        table[x][y].setBackground(Color.gray);
        table[x][y].setForeground(Color.white);
        table[x][y].setMargin(new Insets(0, 0, 0, 0));
        boardPanel.add(table[x][y]);
      }
    }
  }

  @Override
  public void restartGame() {
    boardPanel.removeAll();
    minesBoard.setRows(row);
    minesBoard.setColumns(col);
    remainingBombsLabel.setText("" + setup.getNumBombs());
    setSize(sizeX, sizeY);
    initializeBoard();
    if (controller != null) {
      addListenerController((MouseListener) controller);
    }
    remainingTime.stop();
    remainingTime = new Time(remainingTimeLabel);
    revalidate();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getSource() == easyButton) {
      controller.setup.easy();
      loadBoardSettingsFactory(setup.getBoardSize());
    }
    if (e.getSource() == mediumButton) {
      controller.setup.medium();
      loadBoardSettingsFactory(setup.getBoardSize());
    }
    if (e.getSource() == hardButton) {
      controller.setup.hard();
      loadBoardSettingsFactory(setup.getBoardSize());
    }
    if (e.getSource() == easyButton || e.getSource() == mediumButton
            || e.getSource() == hardButton) {
      controller.restart();
    }
  }

  public void addController(Controller controller) {
    this.controller = controller;
  }

  public void addListenerController(MouseListener controller) {
    for (int x = 0; x < row; x++) {
      for (int y = 0; y < col; y++) {
        table[x][y].addMouseListener(controller);
      }
    }
  }

  public void setGamePanel(String nameGame, String modalityName, String defaultValueModality) {
    this.nameGameLabel.setText(nameGame);
    this.modalityName.setText(modalityName);
    this.valueModality.setText(defaultValueModality);
  }

  @Override
  public void updateCellFlag(Cell c) {
    int numBombs = Integer.parseInt(remainingBombsLabel.getText());
    if (c.getFlag()) {
      table[c.getX()][c.getY()].setText("|>");
      table[c.getX()][c.getY()].setForeground(Color.RED);
      numBombs--;
    } else {
      table[c.getX()][c.getY()].setText("");
      table[c.getX()][c.getY()].setForeground(Color.WHITE);
      numBombs++;
    }
    remainingBombsLabel.setText("" + numBombs);
  }

  @Override
  public void updateCellDiscover(Cell c) {
    if (c.getValue() == 0) {
      table[c.getX()][c.getY()].setBackground(Color.DARK_GRAY);
    } else {
      table[c.getX()][c.getY()].setBackground(Color.DARK_GRAY);
      table[c.getX()][c.getY()].setText("" + c.getValue());
      switch (c.getValue()) {
        case 1:
          table[c.getX()][c.getY()].setForeground(Color.BLUE);
          break;
        case 2:
          table[c.getX()][c.getY()].setForeground(Color.GREEN);
          break;
        case 3:
          table[c.getX()][c.getY()].setForeground(Color.RED);
          break;
        case 4:
          table[c.getX()][c.getY()].setForeground(Color.BLACK);
          break;
      }
    }
  }

  @Override
  public void updateCellBomb(Cell c) {
    TypeBomb typeBomb = c.getTypeBomb();
    switch (typeBomb) {
      case BOMB:
        table[c.getX()][c.getY()].setText("*");
        break;
      case BOMB_A:
        table[c.getX()][c.getY()].setText("+");
        break;
      case BOMB_B:
        table[c.getX()][c.getY()].setText("+");
    }
    table[c.getX()][c.getY()].setBackground(Color.RED);
  }

  @Override
  public void updateLose() {
    int x = (int) (row / 2);
    int y = (int) (col / 2);
    table[x][y - 4].setText("Y");
    table[x][y - 3].setText("O");
    table[x][y - 2].setText("U");
    table[x][y - 1].setText("");
    table[x][y].setText("L");
    table[x][y + 1].setText("O");
    table[x][y + 2].setText("S");
    table[x][y + 3].setText("E");
    table[x][y + 4].setText("!");
    for (int i = -4; i < 5; i++) {
      table[x][y + i].setBackground(Color.WHITE);
      table[x][y + i].setForeground(Color.BLACK);
    }
  }

  @Override
  public void updateWin() {
    int x = (int) (row / 2);
    int y = (int) (col / 2);
    table[x][y - 4].setText("Y");
    table[x][y - 3].setText("O");
    table[x][y - 2].setText("U");
    table[x][y - 1].setText("");
    table[x][y].setText("W");
    table[x][y + 1].setText("I");
    table[x][y + 2].setText("N");
    table[x][y + 3].setText("!");
    table[x][y + 4].setText("!");
    for (int i = -4; i < 5; i++) {
      table[x][y + i].setBackground(Color.WHITE);
      table[x][y + i].setForeground(Color.BLACK);
    }
  }

  @Override
  public void updateReset() {
    loadBoardSettingsFactory(setup.getBoardSize());
    restartGame();
  }

  @Override
  public void updateScore(int score) {
    this.valueModality.setText("" + score);
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }
}
