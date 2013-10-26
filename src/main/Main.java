package main;

import view.UI;
import model.TimeServer;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    UI ui;
    ui = new view.TextUI();
//    TimeServer q = TimeServer ();


    Control control = new Control(ui);
    control.run();
  }
}
