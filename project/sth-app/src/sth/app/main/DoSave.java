package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.SchoolManager;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {
  
  /** File name. */
  Input<String> _filename;

  /**
   * @param receiver
   */
  public DoSave(SchoolManager receiver) {
    super(Label.SAVE, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    try {
      if (_receiver.getFilename().equals("")) {
        _filename = _form.addStringInput(Message.newSaveAs());
        _form.parse();
        _receiver.save(_filename.value());
      }
      else if (_receiver.filechanged())
        _receiver.save(_receiver.getFilename());
    } catch (FileNotFoundException fnfe) {
      _display.popup(Message.fileNotFound(_filename.value()));
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }
  }

}
