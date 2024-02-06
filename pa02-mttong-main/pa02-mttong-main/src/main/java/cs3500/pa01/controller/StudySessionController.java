package cs3500.pa01.controller;

import cs3500.pa01.Question;
import cs3500.pa01.Utils;
import cs3500.pa01.enums.Difficulty;
import cs3500.pa01.model.Model;
import cs3500.pa01.view.View;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Study session controller, no arguments
 */
public class StudySessionController implements Controller {
  View view;
  Model model;

  /**
   * constructor
   *
   * @param view view to user
   * @param model initialized after path and number of questions is given
   */
  public StudySessionController(View view, Model model) {
    this.view = view;
    this.model = model;
  }

  /**
   * run program method
   */
  public void run() {

    String filepath;
    try {
      //gets path and number of questions from user
      filepath = this.view.getFile();
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot get file!");
    }
    Path path = Utils.convertPath(filepath);
    String numInput;
    try {
      numInput = this.view.getNumber("How many questions would you like to practice?");
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot get number!");
    }
    int num = Utils.convertNum(numInput);

    //creates model
    model.loadQuestions(path, num);
    ArrayList<Question> questions = this.model.getStudyQuestions();


    //loops through each question to present to the user and get input
    for (Question question : questions) {
      try {
        if (!operation(question)) {
          break;
        }
      } catch (IOException e) {
        throw new IllegalArgumentException("Cannot send question!");
      }
    }

    try {

      //prints stats after questions are done
      view.printStats(model.getNumQuestionsAnswered(), model.getHardToEasy(), model.getEasyToHard(),
          model.getNumHard(), model.getNumEasy());
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot print Stats!");
    }


    //write back to file
    try {
      Utils.writeToFile(listToString(model.getQuestionsBank()), filepath);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }

  }

  /**
   * present given question and allow for user input
   *
   * @param q question
   * @return boolean, to continue or break
   */
  private boolean operation(Question q) throws IOException {
    String select = this.view.getNumber(
        q.getQuestion() + "\n 1. Mark easy   2. Mark hard   3. Show Answer   4.Exit \n");

    int option = Utils.convertNum(select); //should probably do nextInt();

    switch (option) {
      case 1 -> {
        model.updateStats(q.getDifficulty(), Difficulty.EASY);
        q.setDifficulty(Difficulty.EASY);
        return true;
      }
      case 2 -> {
        model.updateStats(q.getDifficulty(), Difficulty.HARD);
        q.setDifficulty(Difficulty.HARD);
        return true;
      }
      case 3 -> {
        model.updateStats(q.getDifficulty(), q.getDifficulty());
        view.printInput("Answer: " + q.getAnswer() + "\n");
        return true;
      }
      case 4 -> {
        return false;
      }
      default -> {
        view.printInput("not an option, try again: ");
        return operation(q);
      }
    }
  }

  /**
   * converts list of questions to a StringBuilder
   *
   * @param questions list of questions
   * @return StringBuilder
   */
  private StringBuilder listToString(ArrayList<Question> questions) {
    StringBuilder result = new StringBuilder();
    for (Question q : questions) {
      result.append(q.toString());
    }
    return result;
  }

}
