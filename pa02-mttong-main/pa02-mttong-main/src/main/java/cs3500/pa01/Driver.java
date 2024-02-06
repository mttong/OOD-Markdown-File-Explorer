package cs3500.pa01;

import cs3500.pa01.controller.Controller;

/**
 * This is the main driver of this project.
 */
public class Driver {

  /**
   * Project entry point, decides controller
   *
   * @param args - input path of directory, organizational flag, output path or no args
   */
  public static void main(String[] args) {
    Controller control = Utils.selectController(args);
    control.run();
  }
}