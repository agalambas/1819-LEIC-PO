package sth;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

/** 
 * PersonWithMethods implementation
 */
public abstract class PersonWithMethods extends Person implements Serializable {
    
    private Map<String, Discipline> _disciplines = new HashMap<String, Discipline>();

    public PersonWithMethods(int id, String phoneNumber, String name, String type) {
        super(id, phoneNumber, name, type);
    }

    public List<Discipline> getDisciplines() {
        return new ArrayList<Discipline>(_disciplines.values());
    }

    public Discipline getDiscipline(String disciplineName) {
        return _disciplines.get(disciplineName);
    }

    public Project getProject(String disciplineName, String projectName) {
        return getDiscipline(disciplineName).getProject(projectName);
    }

    public Survey getSurvey(String disciplineName, String projectName) {
        return getProject(disciplineName, projectName).getSurvey();
    }

    public void addDiscipline(Discipline discipline) {
        _disciplines.put(discipline.getName(), discipline);
    }

    public boolean hasDiscipline(String disciplineName) {
        return _disciplines.containsKey(disciplineName);
    }

    public void linkSurvey(String disciplineName, String projectName) {
        getSurvey(disciplineName, projectName).setPerson(this);
    }

    public abstract String printSurvey(String disciplineName, String projectName);
}