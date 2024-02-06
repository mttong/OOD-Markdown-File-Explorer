package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.controller.Controller;
import cs3500.pa01.controller.StudySessionController;
import cs3500.pa01.model.MockModel;
import cs3500.pa01.view.MockView;
import org.junit.jupiter.api.Test;

/**
 * test StudySessionController with MockView and MockModel
 */
public class StudySessionControllerTest {
  private Controller controller;
  private MockView view;
  private MockModel model;


  @Test
  public void view1() {

    view = new MockView();
    model = new MockModel();
    controller = new StudySessionController(view, model);
    controller.run();

    assertEquals("getFile called.getNumber called.getNumber called.printStats called.",
        view.log.toString());

    assertEquals(
        "loadQuestions called.getStudyQuestions called.updateStats called.getAnswered "
            + "called.getHardToEasy called.getEasyToHard called.getNumHard called.getNumEasy "
            + "called.getQuestionsBank called.",
        model.log.toString());


  }

  @Test
  public void view2() {

    view = new MockView("2");
    model = new MockModel();
    controller = new StudySessionController(view, model);
    controller.run();


    assertEquals("getFile called.getNumber called.getNumber called.printStats called.",
        view.log.toString());

    assertEquals(
        "loadQuestions called.getStudyQuestions called.updateStats called.getAnswered "
            + "called.getHardToEasy called.getEasyToHard called.getNumHard called.getNumEasy "
            + "called.getQuestionsBank called.",
        model.log.toString());

  }

  @Test
  public void view3() {

    view = new MockView("3");
    model = new MockModel();
    controller = new StudySessionController(view, model);
    controller.run();


    assertEquals(
        "getFile called.getNumber called.getNumber called.printInput "
            + "called.printStats called.",
        view.log.toString());

    assertEquals(
        "loadQuestions called.getStudyQuestions called.updateStats "
            + "called.getAnswered called.getHardToEasy called.getEasyToHard called.getNumHard "
            + "called.getNumEasy called.getQuestionsBank called.",
        model.log.toString());

  }

  @Test
  public void view4() {

    view = new MockView("4");
    model = new MockModel();
    controller = new StudySessionController(view, model);
    controller.run();

    assertEquals("getFile called.getNumber called.getNumber called.printStats called.",
        view.log.toString());

    assertEquals(
        "loadQuestions called.getStudyQuestions called.getAnswered called.getHardToEasy "
            + "called.getEasyToHard called.getNumHard called.getNumEasy "
            + "called.getQuestionsBank called.",
        model.log.toString());

  }


}

