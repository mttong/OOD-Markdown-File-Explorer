package cs3500.pa01.controller;

import cs3500.pa01.ContentReader;
import cs3500.pa01.MarkdownCollector;
import cs3500.pa01.MarkdownFile;
import cs3500.pa01.Utils;
import cs3500.pa01.enums.OrganizationalFlag;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Markdown and Sr file creator controller, for 3 arguments
 */
public class FileCreationController implements Controller {
  String[] args;

  /**
   * constructor
   *
   * @param a command line arguments
   */
  public FileCreationController(String[] a) {
    this.args = a;
  }

  /**
   * run program method
   */
  public void run() {

    //converting command line inputs
    Path inputPath = Utils.convertPath(args[0]);
    OrganizationalFlag flag = OrganizationalFlag.valueOf(args[1].toUpperCase());
    String output = args[2];
    String sroutput = args[2].substring(0, output.length() - 2) + "sr";

    //collect Markdown files
    MarkdownCollector fileVisit = new MarkdownCollector();
    try {
      Files.walkFileTree(inputPath, fileVisit);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    ArrayList<MarkdownFile> mdArr = fileVisit.getMarkdownFiles();

    //sort based on organizational flag
    Utils.sort(flag, mdArr);

    //read and write content to markdown file for StudyGuide and sr file for Q&A
    ContentReader reader = new ContentReader(mdArr);
    try {
      Utils.writeToFile(reader.getContent(), output);
      Utils.writeToFile(reader.getQuestions(), sroutput);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
