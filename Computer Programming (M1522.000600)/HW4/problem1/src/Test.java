
import cpta.environment.Compiler;
import cpta.environment.Executer;
import cpta.Grader;
import cpta.exam.ExamSpec;
import cpta.exam.Problem;
import cpta.exam.Student;
import cpta.exam.TestCase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("<Test Sub-problem 1>");
        testSubProblem1();
        System.out.println("\n<Test Sub-problem 2>");
        testSubProblem2();
    }

    static void testSubProblem1() {
        String submissionDirPath = "data/exam-simple/";
        resetData(submissionDirPath);

        Student s1 = new Student("2020-12345", "Youngki Lee");
        Student s2 = new Student("2020-12346", "Kiroong Choe");
        List<Student> students = new ArrayList<>(); students.add(s1); students.add(s2);

        TestCase t1 = new TestCase("1", "1.in", "1.out", 50);
        TestCase t2 = new TestCase("2", "2.in", "2.out", 50);
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(t1);
        testCases.add(t2);

        Problem p1 = new Problem(
                "problem1", "data/test-cases/test-exam-simple/problem1/",
                testCases, "Problem1.sugo", null, null
        );
        Problem p2 = new Problem(
                "problem2", "data/test-cases/test-exam-simple/problem2/",
                testCases, "Problem2.sugo", null, null
        );
        List<Problem> problems = new ArrayList<>(); problems.add(p1); problems.add(p2);

        ExamSpec examSpec = new ExamSpec(problems, students);
        Grader grader = new Grader(new Compiler(), new Executer());
        Map<String,Map<String,List<Double>>> result = grader.gradeSimple(examSpec, submissionDirPath);

        Map<String,Map<String,List<Double>>> expectedResult = new HashMap<>();
        expectedResult = Map.of("2020-12345", Map.of("problem1", Arrays.asList(50.0, 50.0),
                "problem2", Arrays.asList(50.0, 50.0)),
                "2020-12346", Map.of("problem1", Arrays.asList(0.0, 0.0),
                        "problem2", Arrays.asList(50.0, 0.0)));

        testAndPrintResult(result, expectedResult);
    }

    static void testSubProblem2() {
        String submissionDirPath = "data/exam-robust/";
        resetData(submissionDirPath);


        Student s1 = new Student("2020-12345", "Youngki Lee");
        Student s2 = new Student("2020-12346", "Kiroong Choe");
        Student s3 = new Student("2020-12347", "Clumsy Kang");
        Student s4 = new Student("2020-12348", "Multi-file Jung");
        Student s5 = new Student("2020-12349", "Troller Park");
        Student s6 = new Student("2020-12350", "Dropper Kim");
        List<Student> students = new ArrayList<>();
        students.add(s1); students.add(s2); students.add(s3);
        students.add(s4); students.add(s5); students.add(s6);

        TestCase t2_1 = new TestCase("1", "1.in", "1.out", 50);
        TestCase t2_2 = new TestCase("2", "2.in", "2.out", 50);
        List<TestCase> testCases2 = new ArrayList<>(); testCases2.add(t2_1); testCases2.add(t2_2);
        TestCase t3_1 = new TestCase("1", "1.in", "1.out", 50);
        TestCase t3_2 = new TestCase("2", "2.in", "2.out", 50);
        TestCase t3_3 = new TestCase("3", "3.in", "3.out", 50);
        List<TestCase> testCases3 = new ArrayList<>();
        testCases3.add(t3_1); testCases3.add(t3_2); testCases3.add(t3_3);

        Set<String> judgingTypesA = new TreeSet<>(); judgingTypesA.add(Problem.LEADING_WHITESPACES);
        Set<String> judgingTypesB = new TreeSet<>(); judgingTypesB.add(Problem.IGNORE_WHITESPACES);
        Set<String> judgingTypesC = new TreeSet<>(); judgingTypesC.add(Problem.CASE_INSENSITIVE);

        Problem p1 = new Problem(
                "problem1", "data/test-cases/test-exam-robust/problem1/",
                testCases2, "Problem1.sugo", null, judgingTypesA
        );
        Problem p2 = new Problem(
                "problem2", "data/test-cases/test-exam-robust/problem2/",
                testCases2, "Problem2.sugo", null, judgingTypesB
        );
        Problem p3 = new Problem(
                "problem3", "data/test-cases/test-exam-robust/problem3/",
                testCases3, "Wrapper.sugo",
                "data/test-cases/test-exam-robust/problem3/wrappers/",judgingTypesC
        );
        List<Problem> problems = new ArrayList<>(); problems.add(p1); problems.add(p2); problems.add(p3);

        ExamSpec examSpec = new ExamSpec(problems, students);

        Grader grader = new Grader(new Compiler(), new Executer());
        Map<String,Map<String,List<Double>>> result = grader.gradeRobust(examSpec, submissionDirPath);

        Map<String,Map<String,List<Double>>> expectedResult = new HashMap<>();
        expectedResult = Map.of("2020-12345", Map.of("problem1", Arrays.asList(50.0, 50.0),
                "problem2", Arrays.asList(50.0, 50.0),
                "problem3", Arrays.asList(50.0, 50.0, 50.0)),
                "2020-12346", Map.of("problem1", Arrays.asList(0.0, 0.0),
                        "problem2", Arrays.asList(50.0, 0.0),
                        "problem3", Arrays.asList(0.0, 0.0, 0.0)),
                "2020-12347", Map.of("problem1", Arrays.asList(50.0, 50.0),
                        "problem2", Arrays.asList(50.0, 50.0),
                        "problem3", Arrays.asList(50.0, 50.0, 50.0)),
                "2020-12348", Map.of("problem1", Arrays.asList(50.0, 50.0),
                        "problem2", Arrays.asList(50.0, 50.0),
                        "problem3", Arrays.asList(50.0, 50.0, 50.0)),
                "2020-12349", Map.of("problem1", Arrays.asList(50.0, 50.0),
                        "problem2", Arrays.asList(25.0, 25.0),
                        "problem3", Arrays.asList(0.0, 0.0, 0.0)),
                "2020-12350", Map.of("problem1", Arrays.asList(0.0, 0.0),
                        "problem2", Arrays.asList(0.0, 0.0),
                        "problem3", Arrays.asList(0.0, 0.0, 0.0)));

        testAndPrintResult(result, expectedResult);
    }

    static void testAndPrintResult(Map<String,Map<String,List<Double>>> result, Map<String,Map<String,List<Double>>> expected) {
        boolean testResult = test(result, expected);
        printOX(testResult);

        if (!testResult) {
            System.out.println("Your Result : ");
            printResult(result);
            System.out.println("Expected Result : ");
            printResult(expected);
        }
    }

    static void printResult(Map<String,Map<String,List<Double>>> result) {
        if (result == null) {
            return;
        }

        SortedSet<String> studentIdSet = new TreeSet<String>(result.keySet());
        for(String studentId : studentIdSet) {
            System.out.println(studentId);
            Map<String,List<Double>> studentResult = result.get(studentId);
            SortedSet<String> problemIdSet = new TreeSet<String>(studentResult.keySet());
            for(String problemId : problemIdSet) {
                List<Double> problemResult = studentResult.get(problemId);
                System.out.print("\t" + problemId + ": ");
                for(double score : problemResult) {
                    System.out.print(score + " ");
                }
                System.out.println();
            }
        }
    }

    static void printOX(boolean result) {
        System.out.println(result ? "O" : "X");
    }

    static boolean test(Map<String,Map<String,List<Double>>> result, Map<String,Map<String,List<Double>>> expected) {
        if (result == null) {
            return false;
        }

        if (!(expected.keySet()).equals(result.keySet())) {
            return false;
        }

        for (String id : result.keySet()) {
            Map<String, List<Double>> gradesResult = result.get(id);
            Map<String, List<Double>> gradesExpected = expected.get(id);
            if (!gradesResult.keySet().equals(gradesExpected.keySet())) {
                return false;
            }
            for (String problem : gradesResult.keySet()) {
                List<Double> res = gradesResult.get(problem);
                List<Double> exp = gradesExpected.get(problem);
                if (res.size() != exp.size()) {
                    return false;
                }
                for (int i=0; i<res.size(); i++) {
                    if (!res.get(i).equals(exp.get(i))) {
                        System.out.printf("student %s %s %d-th different %f %f\n", id, problem, i, res.get(i), exp.get(i));
                        return false;
                    }
                }
            }
        }

        return true;
    }


    static void resetData(String origPath) {
        // Delete directory
        deleteDirectory(new File(origPath));


        // Copy new data from backup
        String backupPath = null;
        if (origPath.contains("exam-robust")) backupPath = origPath.replace("exam-robust", "exam-robust-backup/");
        if (origPath.contains("exam-simple")) backupPath = origPath.replace("exam-simple", "exam-simple-backup/");

        try {
            Path sourceDir = Paths.get(backupPath);
            Path destinationDir = Paths.get(origPath);
            // Traverse the file tree and copy each file/directory.
            Files.walk(sourceDir)
                    .forEach(sourcePath -> {
                        try {
                            Path targetPath = destinationDir.resolve(sourceDir.relativize(sourcePath));
                            Files.copy(sourcePath, targetPath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void deleteDirectory(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectory(entry);
                }
            }
        }
        file.delete();
    }
}
