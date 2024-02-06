package cs3500.pa01.model;

import cs3500.pa01.Question;
import cs3500.pa01.Utils;
import cs3500.pa01.enums.Difficulty;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * study session model class, stores data
 */
public class StudySessionModel implements Model {
  int numQuestions;
  ArrayList<Question> questionsBank;
  ArrayList<Question> studyQuestions;

  //stats
  int numQuestionsAnswered;
  int easyToHard;
  int hardToEasy;
  int numHard;
  int numEasy;

  /**
   * constructor
   */
  public StudySessionModel() {

    this.questionsBank = new ArrayList<>();
    this.studyQuestions = new ArrayList<>();

    this.numQuestionsAnswered = 0;
    this.easyToHard = 0;
    this.hardToEasy = 0;
    this.numHard = 0;
    this.numEasy = 0;

  }

  /**
   * gets content from sr file and adds questions to list of Questions
   */
  public void loadQuestions(Path srfile, int numbQuestions) {
    this.numQuestions = numbQuestions;
    Scanner scan = new Scanner(Utils.getContent(srfile));
    while (scan.hasNextLine()) {
      String line = scan.nextLine();
      questionsBank.add(loadLine(line));
    }
  }

  /**
   * parses line helper
   *
   * @param line a single question
   * @return Question
   */
  private Question loadLine(String line) {
    int dash = line.indexOf("-");
    int colons = line.indexOf(":::");
    Difficulty level = Difficulty.valueOf(line.substring(0, dash).toUpperCase());
    if (level == Difficulty.HARD) {
      numHard++;
    } else {
      numEasy++;  //setting number of hard and easy questions
    }
    String question = line.substring(dash + 2, colons);
    String answer = line.substring(colons + 3);
    return new Question(question, answer, level);
  }

  /**
   * shuffles and returns the number request of study questions
   *
   * @return a list of questions
   */
  public ArrayList<Question> getStudyQuestions() {

    //shuffle and sort with HARD on top
    Collections.shuffle(questionsBank);
    questionsBank.sort(Question::compareTo);

    if (questionsBank.size() > numQuestions) {
      for (int i = 0; i < numQuestions; i++) {
        studyQuestions.add(questionsBank.get(i));
      }
    } else {
      studyQuestions.addAll(questionsBank);
    }
    return this.studyQuestions;
  }

  /**
   * updates statistics
   *
   * @param prev previous Difficulty
   * @param now  new Difficulty
   */
  public void updateStats(Difficulty prev, Difficulty now) {
    if (prev == Difficulty.HARD && now == Difficulty.EASY) {
      hardToEasy++;
      numHard--;
      numEasy++;

    } else if (prev == Difficulty.EASY && now == Difficulty.HARD) {
      easyToHard++;
      numEasy--;
      numHard++;
    }
    numQuestionsAnswered++;
  }

  /**
   * get number of questions answered
   *
   * @return int
   */
  public int getNumQuestionsAnswered() {
    return numQuestionsAnswered;
  }

  /**
   * get number of questions that went from hard to easy
   *
   * @return int
   */
  public int getHardToEasy() {
    return hardToEasy;
  }

  /**
   * get number of questions that went from easy to hard
   *
   * @return int
   */
  public int getEasyToHard() {
    return easyToHard;
  }

  /**
   * get number of hard questions
   *
   * @return int
   */
  public int getNumHard() {
    return numHard;
  }

  /**
   * get number of easy questions
   *
   * @return int
   */
  public int getNumEasy() {
    return numEasy;
  }

  /**
   * gets the question bank
   *
   * @return a list of questions
   */
  public ArrayList<Question> getQuestionsBank() {
    return this.questionsBank;
  }
}
