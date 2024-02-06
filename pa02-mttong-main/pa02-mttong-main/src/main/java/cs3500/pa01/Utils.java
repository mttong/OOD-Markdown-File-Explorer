package cs3500.pa01;

import cs3500.pa01.comparators.CompareCreated;
import cs3500.pa01.comparators.CompareModified;
import cs3500.pa01.comparators.CompareName;
import cs3500.pa01.controller.Controller;
import cs3500.pa01.controller.FileCreationController;
import cs3500.pa01.controller.StudySessionController;
import cs3500.pa01.enums.OrganizationalFlag;
import cs3500.pa01.model.Model;
import cs3500.pa01.model.StudySessionModel;
import cs3500.pa01.view.StudySessionView;
import cs3500.pa01.view.View;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Utilities class for methods to use in Driver
 */
public class Utils {

  /**
   * sorting MarkdownFiles based on organizational flag
   *
   * @param orgFlag given organizational flag
   * @param mds     list of MarkdownFiles
   */
  public static void sort(OrganizationalFlag orgFlag, ArrayList<MarkdownFile> mds) {
    switch (orgFlag) {
      case FILENAME -> mds.sort(new CompareName());
      case MODIFIED -> mds.sort(new CompareModified());
      case CREATED -> mds.sort(new CompareCreated());
      default -> throw new IllegalArgumentException("No valid switch case");
    }
  }

  /**
   * converts input path to Path object, throws exception if not possible
   *
   * @param path String
   * @return Path
   */
  public static Path convertPath(String path) {
    Path input;
    try {
      input = Path.of(path);
    } catch (InvalidPathException e) {
      throw new IllegalArgumentException("Path doesn't exist!");
    }
    return input;
  }

  /**
   * decides which controller to use based on arguments
   *
   * @param arguments command line arguments
   * @return Controller
   */
  public static Controller selectController(String[] arguments) {
    if (arguments.length == 0) {
      View view = new StudySessionView(new InputStreamReader(System.in), System.out);
      Model model = new StudySessionModel();
      return new StudySessionController(view, model);
    } else if (arguments.length == 3) {
      return new FileCreationController(arguments);
    } else {
      throw new IllegalArgumentException("No correct arguments!");
    }
  }

  /**
   * converts String from command line to integer
   *
   * @param numInput number input as String
   * @return int
   */
  public static int convertNum(String numInput) {
    int num;
    try {
      num = Integer.parseInt(numInput);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Not a valid number!");
    }
    return num;
  }

  /**
   * reads the content of a file
   *
   * @param path Path to file
   * @return String of content
   */
  public static String getContent(Path path) {
    try {
      return new String(Files.readAllBytes(path));
    } catch (IOException e) {
      throw new IllegalStateException("Contents not readable (not valid file)");
    }
  }

  /**
   * writes to a filepath
   *
   * @param content  content to write
   * @param filepath filepath to write to
   * @throws IOException if unable to write to file
   */
  public static void writeToFile(StringBuilder content, String filepath) throws IOException {
    FileWriter fileWriter = new FileWriter(filepath);
    fileWriter.write(content.toString());
    fileWriter.close();
  }
}
