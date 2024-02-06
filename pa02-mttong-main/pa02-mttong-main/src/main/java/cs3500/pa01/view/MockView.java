package cs3500.pa01.view;

/**
 * Mock view for testing
 */
public class MockView implements View {
  public StringBuilder log;
  public String number;

  /**
   * Constructor
   */
  public MockView() {
    this.log = new StringBuilder();
    this.number = "1";
  }

  /**
   * another constructor for operation switch case
   *
   * @param num switch case, "2", "3", "4"
   */
  public MockView(String num) {
    this.log = new StringBuilder();
    this.number = num;
  }

  /**
   * mock getFile
   *
   * @return String
   */
  @Override
  public String getFile() {
    this.log.append("getFile called.");
    return "src/test/resources/SampleFileInputs/vectors.md";
  }

  /**
   * mock getNumber
   *
   * @param s any prompt
   * @return String
   */
  @Override
  public String getNumber(String s) {
    this.log.append("getNumber called.");
    return number;
  }

  /**
   * mock printStats
   *
   * @param questionsAnswered  number of questions answered
   * @param hardToEasy number of questions changed from hard to easy
   * @param easyToHard number of questions changed from easy to hard
   * @param numHard    number of hard questions
   * @param numEasy    number of easy questions
   */
  @Override
  public void printStats(int questionsAnswered, int hardToEasy, int easyToHard, int numHard,
                         int numEasy) {
    this.log.append("printStats called.");
  }

  /**
   * mock print input
   *
   * @param input prompt
   */
  @Override
  public void printInput(String input) {
    this.log.append("printInput called.");
  }
}
