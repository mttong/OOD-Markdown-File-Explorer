package cs3500.pa01;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Readers contents of an MarkdownFile and creates StringBuilder of valid content
 */
public class ContentReader {
  private final ArrayList<MarkdownFile> mdFiles;
  private final StringBuilder parsed;
  private final StringBuilder questions;
  private final StringBuilder content;
  private boolean inBrackets;

  /**
   * Constructor
   *
   * @param mdFiles list of markdown files
   */
  public ContentReader(ArrayList<MarkdownFile> mdFiles) {
    this.parsed = new StringBuilder();
    this.questions = new StringBuilder();
    this.content = new StringBuilder();
    this.mdFiles = mdFiles;

    parseFile();
    extractQuestions();
  }

  /**
   * parses file for valid content (headers , bracket info)
   */
  private void parseFile() {
    for (MarkdownFile mdf : mdFiles) {
      Scanner scan = new Scanner(mdf.getContent());

      inBrackets = false;
      while (scan.hasNextLine()) {
        String line = scan.nextLine();
        lineReader(line);
      }
    }
  }

  /**
   * helper for parseFile that reads each line of the file
   *
   * @param line String of each file line
   */
  private void lineReader(String line) {
    if (line.startsWith("#")) {
      this.parsed.append(line).append("\n");
    } else if (inBrackets) {
      if (line.contains("]]")) {
        int end = line.indexOf("]]");
        this.parsed.append(line, 0, end).append("\n");
        inBrackets = false;
        lineReader(line.substring(end));
      } else {
        this.parsed.append(line);
      }
    } else if (line.contains("[[")) {
      inBrackets = true;
      int start = line.indexOf("[[");
      this.parsed.append("- ");
      lineReader(line.substring(start + 2));
    }
  }

  /**
   * separates parsed content into questions and study guide material
   */
  private void extractQuestions() {
    String[] lines = this.parsed.toString().split("(?<=\n)");
    for (String line : lines) {
      if (line.contains(":::")) {
        this.questions.append("HARD").append(line);
      } else {
        this.content.append(line);
      }
    }
  }

  /**
   * content getter
   *
   * @return StringBuilder
   */
  public StringBuilder getContent() {
    return content;
  }

  /**
   * questions getter
   *
   * @return StringBuilder
   */
  public StringBuilder getQuestions() {
    return questions;
  }
}


