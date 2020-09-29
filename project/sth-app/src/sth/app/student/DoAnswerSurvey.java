package sth.app.student;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

import sth.app.exceptions.NoSuchDisciplineException;
import sth.exceptions.NoSuchDisciplineNameException;
import sth.app.exceptions.NoSuchProjectException;
import sth.exceptions.NoSuchProjectNameException;
import sth.app.exceptions.NoSurveyException;
import sth.exceptions.NoOpenedSurveyException;

//FIXME: exceptions

/**
 * 4.4.2. Answer survey.
 */
public class DoAnswerSurvey extends Command<SchoolManager> {

  /** Discipline name */
  Input<String> _discipline;

  /** Project name */
  Input<String> _project;

  /** Survey hours */
  Input<String> _hours;

  /** Survey comments */
  Input<String> _comment;

  /**
   * @param receiver
   */
  public DoAnswerSurvey(SchoolManager receiver) {
    super(Label.ANSWER_SURVEY, receiver);
    _discipline = _form.addStringInput(Message.requestDisciplineName());
    _project = _form.addStringInput(Message.requestProjectName());
    _hours = _form.addStringInput(Message.requestProjectHours());
    _comment = _form.addStringInput(Message.requestComment());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _receiver.answerSurvey(_discipline.value(), _project.value(), _hours.value(), _comment.value());
    } catch (NoSuchDisciplineNameException e) {
      throw new NoSuchDisciplineException(_discipline.value());
    } catch (NoSuchProjectNameException e) {
      throw new NoSuchProjectException(_discipline.value(), _project.value());
    } catch (NoOpenedSurveyException e) {
      throw new NoSurveyException(_discipline.value(), _project.value());
    }
  }

}
