package cs3500.pa01;

import cs3500.pa01.enums.Difficulty;

/**
 * Question class
 */
public class Question implements Comparable<Question> {
  private final String question;
  private final String answer;
  private Difficulty difficulty;

  /**
   * constructor
   *
   * @param q question
   * @param a answer
   * @param l difficulty: Hard or Easy
   */
  public Question(String q, String a, Difficulty l) {
    this.question = q;
    this.answer = a;
    this.difficulty = l;
  }

  /**
   * returns question
   *
   * @return String
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * returns answer
   *
   * @return String
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * returns the difficulty level
   *
   * @return Difficulty
   */
  public Difficulty getDifficulty() {
    return this.difficulty;
  }

  /**
   * sets the difficulty
   *
   * @param d difficulty
   */
  public void setDifficulty(Difficulty d) {
    this.difficulty = d;
  }

  /**
   * comparator for comparing difficulty (HARD then EASY) for sort
   *
   * @param o the object to be compared.
   * @return int
   */
  @Override
  public int compareTo(Question o) {
    return this.difficulty.compareTo(o.getDifficulty());
  }

  /**
   * converts question into formatted string
   *
   * @return String
   */
  @Override
  public String toString() {
    return difficulty + "- " + question + ":::" + answer + "\n";
  }

}
