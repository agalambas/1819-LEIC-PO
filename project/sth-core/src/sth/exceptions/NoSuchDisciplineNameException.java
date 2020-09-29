package sth.exceptions;

/** Exception thrown when the requested discipline does not exist or the professor does not teach it. */
public class NoSuchDisciplineNameException extends Exception {

  /** Discipline name. */
  private String _name;

  /**
   * @param name
   */
  public NoSuchDisciplineNameException(String name) {
    _name = name;
  }

  /** @return name */
  public String getName() {
    return _name;
  }

}
