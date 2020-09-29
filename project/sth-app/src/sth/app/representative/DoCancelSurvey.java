package sth.app.representative;

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
import sth.app.exceptions.NonEmptySurveyException;
import sth.exceptions.AlreadyAnsweredSurveyException;
import sth.app.exceptions.SurveyFinishedException;
import sth.exceptions.AlreadyFinishedSurveyException;

/**
 * 4.5.2. Cancel survey.
 */
public class DoCancelSurvey extends Command<SchoolManager> {

  /** Discipline name */
  Input<String> _discipline;

  /** Project name */
  Input<String> _project;

  /**
   * @param receiver
   */
  public DoCancelSurvey(SchoolManager receiver) {
    super(Label.CANCEL_SURVEY, receiver);
    _discipline = _form.addStringInput(Message.requestDisciplineName());
    _project = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _receiver.cancelSurvey(_discipline.value(), _project.value());
    } catch (NoSuchDisciplineNameException e) {
      throw new NoSuchDisciplineException(_discipline.value());
    } catch (NoSuchProjectNameException e) {
      throw new NoSuchProjectException(_discipline.value(), _project.value());
    } catch (NoOpenedSurveyException e) {
      throw new NoSurveyException(_discipline.value(), _project.value());
    } catch (AlreadyAnsweredSurveyException e) {
      throw new NonEmptySurveyException(_discipline.value(), _project.value());
    } catch (AlreadyFinishedSurveyException e) {
      throw new SurveyFinishedException(_discipline.value(), _project.value());
    }
  }

}
