package cpta.exam;

import java.util.List;

public class ExamSpec {
    public List<Problem> problems;
    public List<Student> students;

    public ExamSpec(List<Problem> problems, List<Student> students) {
        this.problems = problems;
        this.students = students;
    }
}

