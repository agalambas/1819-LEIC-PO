package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

import sth.app.exceptions.NoSuchDisciplineException;
import sth.exceptions.NoSuchDisciplineNameException;
import sth.app.exceptions.NoSuchProjectException;
import sth.exceptions.NoSuchProjectNameException;

/**
 * 4.3.3. Show project submissions.
 */
public class DoShowProjectSubmissions extends Command<SchoolManager> {

  /** Discipline name */
  Input<String> _discipline;

  /** Project name */
  Input<String> _project;

  /**
   * @param receiver
   */
  public DoShowProjectSubmissions(SchoolManager receiver) {
    super(Label.SHOW_PROJECT_SUBMISSIONS, receiver);
    _discipline = _form.addStringInput(Message.requestDisciplineName());
    _project = _form.addStringInput(Message.requestProjectName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _display.addLine( _receiver.getProjectSubmissions(_discipline.value(), _project.value()) );
      _display.display();
    } catch (NoSuchDisciplineNameException e) {
      throw new NoSuchDisciplineException(_discipline.value());
    } catch (NoSuchProjectNameException e) {
      throw new NoSuchProjectException(_discipline.value(), _project.value());
    }
  }

}
