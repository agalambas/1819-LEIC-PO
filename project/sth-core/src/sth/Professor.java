package sth;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.text.Collator;

/** 
 * Professor implementation
 */
public class Professor extends PersonWithMethods implements Serializable {

    private List<Discipline> _disciplines = new ArrayList<Discipline>();

    public Professor(int id, String phoneNumber, String name) {
        super(id, phoneNumber, name, "DOCENTE");
    }

    public Collection<Student> getStudents(String disciplineName) {
        return getDiscipline(disciplineName).getStudents();
    }

    @Override
    public void addDiscipline(Discipline discipline) {
        super.addDiscipline(discipline);
        _disciplines.add(discipline);
    }

    public void createProject(String disciplineName, String projectName) {
        getDiscipline(disciplineName).createProject(projectName);
    }

    public void closeProject(String disciplineName, String projectName) {
        getDiscipline(disciplineName).closeProject(projectName);
    }

    public String printSurvey(String disciplineName, String projectName) {
        Survey survey = getSurvey(disciplineName, projectName);
        return "\n* Número de submissões: " + survey.getNumSubmissions()
             + "\n* Número de respostas: " + survey.getNumAnswers()
             + "\n* Tempos de resolução (horas) (mínimo, médio, máximo): " + survey.getMinTime() + ", " + survey.getMidTime() + ", " + survey.getMaxTime();
    }
    
    @Override
    public String toString() {
        String print = super.toString();
        // Collator comparator = Collator.getInstance();
        // comparator.setStrength(Collator.PRIMARY);
        Collator comparator = Collator.getInstance(Locale.getDefault());
        _disciplines.sort((a, b) -> comparator.compare(a.toString(), b.toString()));
        for (Discipline discipline: _disciplines)
            print += "\n* " + discipline.toString();
        return print;
    }

}