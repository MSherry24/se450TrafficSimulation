package main;

import view.UI;
import model.TimeServerLinked;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    UI ui;
    ui = new view.TextUI();
    TimeServerLinked timeserver = new TimeServerLinked();

    Control control = new Control(ui);
    control.run();
  }
}
