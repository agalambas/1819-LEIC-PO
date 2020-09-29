package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

import sth.app.exceptions.NoSuchDisciplineException;
import sth.exceptions.NoSuchDisciplineNameException;

import java.util.List;
import java.util.ArrayList;
/**
 * 4.3.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

  /** Discipline name */
  Input<String> _discipline;

  /**
   * @param receiver
   */
  public DoShowDisciplineStudents(SchoolManager receiver) {
    super(Label.SHOW_COURSE_STUDENTS, receiver);
    _discipline = _form.addStringInput(Message.requestDisciplineName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      List<String> persons = _receiver.getDisciplineStudents(_discipline.value());
      for (String person: persons)
        _display.addLine(person);
      _display.display();
    } catch (NoSuchDisciplineNameException e) {
      throw new NoSuchDisciplineException(_discipline.value());
    }
  }

}
