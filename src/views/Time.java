package views;

import javax.swing.JLabel;

public class Time implements Runnable {

  private Thread thread;
  private JLabel timer;
  private int seconds;
  private int minutes;

  public Time(JLabel timer) {
    this.timer = timer;
    thread = new Thread(this);
    thread.start();
  }

  public void stop() {
    thread.interrupt();
  }

  @Override
  public void run() {
    try {
      while (true) {
        timer.setText(minutes + ":" + seconds);
        Thread.sleep(1000);
        seconds += 1;
        if (seconds == 60) {
          seconds = 0;
          minutes += 1;
          timer.setText(minutes + ":" + seconds);
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
