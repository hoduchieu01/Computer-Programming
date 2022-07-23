package cpta;

import cpta.environment.Compiler;
import cpta.environment.Executer;
import cpta.exam.ExamSpec;
import cpta.exam.Problem;
import cpta.exam.Student;
import cpta.exam.TestCase;
import cpta.exceptions.CompileErrorException;
import cpta.exceptions.FileSystemRelatedException;
import cpta.exceptions.InvalidFileTypeException;
import cpta.exceptions.RunTimeErrorException;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;


public class Grader {
    Compiler compiler;
    Executer executer;

    public Grader(Compiler compiler, Executer executer) {
        this.compiler = compiler;
        this.executer = executer;
    }

    public Map<String,Map<String, List<Double>>> gradeSimple(ExamSpec examSpec, String submissionDirPath) {
        // TODO Problem 1-1
        // Implement a grader that grades students submitted codes according to the specification of an exam.
        // ExamSpec examSpec: It contains information about the given exam
        // String submissionDirPath: It points to the directory where student's submitted codes are located. It is guaranteed that a string variable whose name end with '/'
        // An exam consists of several problems, and each problem will be tested with several test cases.
        // return a Map <studentId, <problemId, list of<scores of individual test cases>>>
        // a list of scores for individual test cases should be in ascending order of test case ID.
        Map<String, Map<String, List<Double>>> studentIdProblemIdScore = new HashMap<>();
        List<Problem> problems = examSpec.problems;
        List<Student> students = examSpec.students;
        for (Student student:students){
            Map<String, List<Double>> studentScore = new HashMap<>();
            for (Problem problem:problems){
                studentScore.put(problem.id, markProblem(student, problem, submissionDirPath));
            }
            studentIdProblemIdScore.put(student.id, studentScore);
        }
        return studentIdProblemIdScore;
    }

    private List<Double> markProblem(Student student, Problem problem, String submissionDirPath){
        String problemPathString = submissionDirPath + student.id + "/" + problem.id + "/";
        File problemFile = new File(problemPathString);
        if (!problemFile.isDirectory()) setProblemScoreZero(problem);
        String problemSugoPath = null;
        File[] list = problemFile.listFiles(File::isFile);
        if (list==null) return setProblemScoreZero(problem);
        for (File file : list){
            if (file.getName().equals(problem.targetFileName) && file.isFile()) {
                problemSugoPath = file.getPath();
                break;
            }
        }
        if (problemSugoPath == null) return setProblemScoreZero(problem);
        try {
            compiler.compile(problemSugoPath);
            Map<String, Double> testIdScore = new HashMap<>();
            List<String> testId = new ArrayList<>();
            for (TestCase testCase:problem.testCases){
                testIdScore.put(testCase.id, markTestCase(student, problem, testCase, submissionDirPath));
                testId.add(testCase.id);
            }
            Collections.sort(testId);
            List<Double> scores = new ArrayList<>();
            for (String id:testId){
                scores.add(testIdScore.get(id));
            }
            return scores;
        } catch (CompileErrorException | InvalidFileTypeException | FileSystemRelatedException e) {
            return setProblemScoreZero(problem);
        }
    }

    private double markTestCase(Student student, Problem problem, TestCase testCase, String submissionDirPath){
        String problemPathString = submissionDirPath +  student.id + "/" + problem.id + "/";
        File problemFile = new File(problemPathString);
        if (!problemFile.isDirectory()) setProblemScoreZero(problem);
        String problemSugoPath = null;
        File[] list = problemFile.listFiles(File::isFile);
        if (list==null) return 0.0;
        for (File file : list){
            if (file.getName().equals(problem.targetFileName) && file.isFile()) {
                problemSugoPath = file.getPath();
                break;
            }
        }
        if (problemSugoPath == null) return 0.0;
        problemSugoPath = problemSugoPath.replace(".sugo", ".yo");
        File yoFile = new File(problemSugoPath);
        if(!yoFile.isFile()) return 0.0;
        try {
            String targetIn = (new File(problem.testCasesDirPath, testCase.inputFileName)).getPath();
            String targetOut = (new File(problemPathString, testCase.outputFileName)).getPath();
            executer.execute(problemSugoPath, targetIn, targetOut);
            String testOut = (new File(problem.testCasesDirPath, testCase.outputFileName)).getPath();
            if (isCorrectAns(targetOut,testOut)) return testCase.score;
            else return 0.0;
        } catch (RunTimeErrorException | InvalidFileTypeException | FileSystemRelatedException e) {
            return 0.0;
        }
    }

    public boolean isCorrectAns(String path1, String path2) {
        try{
            boolean ans = Files.readString(Path.of(path1)).equals(Files.readString(Path.of(path2)));
            return ans;
        }
        catch (IOException e){
            return false;
        }
    }

    private List<Double> setProblemScoreZero(Problem problem){
        List<Double> scoreZero = new ArrayList<>();
        for (TestCase testCase:problem.testCases){
            scoreZero.add(0.0);
        }
        return scoreZero;
    }

    public Map<String,Map<String, List<Double>>> gradeRobust(ExamSpec examSpec, String submissionDirPath) {
        // TODO Problem 1-2
        Map<String, Map<String, List<Double>>> studentIdProblemIdScore = new HashMap<>();
        List<Problem> problems = examSpec.problems;
        List<Student> students = examSpec.students;
        for (Student student:students){
            Map<String, List<Double>> studentScore = new HashMap<>();
            for (Problem problem:problems){
                studentScore.put(problem.id, markProblemRobust(student, problem, submissionDirPath));
            }
            studentIdProblemIdScore.put(student.id, studentScore);
        }
        return studentIdProblemIdScore;
    }

    private List<Double> markProblemRobust(Student student, Problem problem, String submissionDirPath){
        String submission = null;
        try {
            // collect file
            getSubDirectoryFile(student, problem, submissionDirPath);
            getWrapperFiles(student, problem, submissionDirPath);
            String targetFilePath = getTargetFilePathRobust(student, problem, submissionDirPath);
            // execute non target file
            String problemDirString = getProblemDirRobust(student, problem, submissionDirPath);
            if (problemDirString != null){
                File problemDir = new File(problemDirString);
                if (problemDir.isDirectory()){
                    File[] files = problemDir.listFiles(File::isFile);
                    for (File file:files){
                        if (!file.getName().equals(problem.targetFileName) && file.getName().endsWith(".sugo"))
                            compiler.compile(file.getPath());
                    }
                }
            }
            if (targetFilePath != null) {
                compiler.compile(targetFilePath);
                submission = targetFilePath.replace(".sugo", ".yo");
            }
            if (submission == null){
                problemDirString = getProblemDirRobust(student, problem, submissionDirPath);
                if (problemDirString != null){
                    File filePath = new File(problemDirString, problem.targetFileName.replace(".sugo", ".yo"));
                    if (filePath.isFile()) submission = filePath.getPath();
                }
            }
            boolean filesNotMatch = onlyYoFiles(student, problem, submissionDirPath);
            if (submission == null) return setProblemScoreZero(problem);
            else {
                List<Double> scores = new ArrayList<>();
                List<String> testId = new ArrayList<>();
                Map<String, Double> testIdScore = new HashMap<>();
                for (TestCase testCase: problem.testCases){
                    testId.add(testCase.id);
                    if (filesNotMatch) {
                        testIdScore.put(testCase.id, 0.5 * markTestCaseRobust(student, problem, testCase, submission));
                    }
                    else testIdScore.put(testCase.id, markTestCaseRobust(student, problem, testCase, submission));
                }
                Collections.sort(testId);
                for (String id:testId){
                    scores.add(testIdScore.get(id));
                }
                return scores;
            }
        } catch (IOException | CompileErrorException | InvalidFileTypeException | FileSystemRelatedException e) {
            return setProblemScoreZero(problem);
        }
    }

    private String getProblemDirRobust(Student student, Problem problem, String submissionDirPath){
        String studentDirectoryRobust = null;
        File path = new File(submissionDirPath);
        File[] subDirectoryList = path.listFiles(File::isDirectory);
        if (subDirectoryList == null) return null;
        for (File subDirectory:subDirectoryList)
            if (subDirectory.getName().contains(student.id)){
                studentDirectoryRobust = subDirectory.getPath();
                break;
            }
        if (studentDirectoryRobust == null) return null;
        File studentDir = new File(studentDirectoryRobust);
        File[] studentDirectoryList = studentDir.listFiles(File::isDirectory);
        if (studentDirectoryList == null) return null;
        for (File directory:studentDirectoryList){
            if (directory.getName().equals(problem.id)) return directory.getPath();
        }
        return null;
    }

    private void getSubDirectoryFile(Student student, Problem problem, String submissionDirPath) throws IOException{
        String problemDirectoryString = getProblemDirRobust(student, problem, submissionDirPath);
        if (problemDirectoryString == null) return;
        File problemDirectory = new File(problemDirectoryString);
        File[] subDirectoryList = problemDirectory.listFiles(File::isDirectory);
        // moveOutOfSrc
        if (subDirectoryList == null || subDirectoryList.length == 0) return;
        if (subDirectoryList.length == 1) {
            moveFilestoParentFolder(subDirectoryList[0].getPath());
        }
    }

    private void moveFilestoParentFolder(String childFolderPath) throws IOException{
        if (childFolderPath == null) return;
        File childFolderFile = new File(childFolderPath);
        File[] childFolderFileList = childFolderFile.listFiles(File::isFile);
        if (childFolderFileList != null && childFolderFileList.length != 0){
            for (File file:childFolderFileList){
                Path destination = (new File(file.getParentFile().getParentFile(), file.getName())).toPath();
                Files.copy(file.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private void getWrapperFiles(Student student, Problem problem, String submissionDirPath) throws IOException{
        if (problem.wrappersDirPath == null) return;
        String problemDirRobust = getProblemDirRobust(student, problem, submissionDirPath);
        if (problemDirRobust == null) return;
        File problemDir = new File(problemDirRobust);
        if (!problemDir.isDirectory()) return;
        File wrappersDir = new File(problem.wrappersDirPath);
        if (!wrappersDir.isDirectory()) return;
        File[] wrapperFilesList = wrappersDir.listFiles(File::isFile);
        if (wrapperFilesList != null && wrapperFilesList.length != 0){
            for (File wrapperFile: wrapperFilesList){
                if (wrapperFile.getName().endsWith(".sugo")){
                    Path destination = (new File(problemDir, wrapperFile.getName())).toPath();
                    Files.copy(wrapperFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    private String getTargetFilePathRobust(Student student, Problem problem, String submissionDirPath){
        String problemDirRobust = getProblemDirRobust(student, problem, submissionDirPath);
        if (problemDirRobust == null) return null;
        File problemDir = new File(problemDirRobust);
        if (!problemDir.isDirectory()) return null;
        File[] filesList = problemDir.listFiles(File::isFile);
        if (filesList == null || filesList.length == 0) return null;
        for (File file:filesList){
            if (file.getName().equals(problem.targetFileName)) {
                return file.getPath();
            }
        }
        return null;
    }

    // Submitted only .yo files
    private boolean onlyYoFiles(Student student, Problem problem, String submissionDirPath){
        String problemDirRobust  = getProblemDirRobust(student, problem, submissionDirPath);
        if (problemDirRobust == null) return false;
        File problemDir = new File(problemDirRobust);
        if (!problemDir.isDirectory()) return false;
        File[] files = problemDir.listFiles(File::isFile);
        List<String> yoFiles = new ArrayList<>();
        List<String> sugoFiles = new ArrayList<>();
        if (files == null) return true;
        for (File file:files) {
            String fileName = file.getName();
            if (fileName.endsWith(".sugo")){
                String nameOfFileSUGO = null;
                if(fileName.lastIndexOf(".") > 0)
                    nameOfFileSUGO = fileName.substring(0, fileName.lastIndexOf("."));
                sugoFiles.add(nameOfFileSUGO);
            }
            if (fileName.endsWith(".yo")) {
                String nameOfFileYO = null;
                if(fileName.lastIndexOf(".") > 0)
                    nameOfFileYO = fileName.substring(0, fileName.lastIndexOf("."));
                yoFiles.add(nameOfFileYO);
            }
        }
        for (String yoFile:yoFiles){
            if (!sugoFiles.contains(yoFile)) {
                return true;
            }
        }
        return false;
    }

    private double markTestCaseRobust(Student student, Problem problem, TestCase testCase, String targetFilePathString){
        try {
            File targetFile = new File(targetFilePathString);
            if (targetFile.isFile()){
                String targetIn = (new File(problem.testCasesDirPath, testCase.inputFileName)).getPath();
                String targetOut = (new File(targetFile.getParent(), testCase.outputFileName)).getPath();
                executer.execute(targetFilePathString, targetIn, targetOut);
            }
            File targetYoFile = new File(targetFilePathString);
            String studentOutputPath = (new File(targetYoFile.getParent(), testCase.outputFileName)).getPath();
            String answerOutputPath = (new File(problem.testCasesDirPath, testCase.outputFileName)).getPath();
            String studentOutput = Files.readString(Path.of(studentOutputPath));
            String answerOutput = Files.readString(Path.of(answerOutputPath));
            if (compareOutputRobust(studentOutput, answerOutput, problem.judgingTypes)){
                return testCase.score;
            } else{
                return 0.0;
            }
        } catch (RunTimeErrorException | InvalidFileTypeException | FileSystemRelatedException | IOException e){
            return 0.0;
        }
    }

    private boolean compareOutputRobust(String output1, String output2, Set<String> judgingTypes){
        if (judgingTypes == null || judgingTypes.isEmpty())
            return output1.equals(output2);
        if (judgingTypes.contains(Problem.LEADING_WHITESPACES)){
            output1 = removeBeginWhiteSpace(output1);
            output2 = removeBeginWhiteSpace(output2);
        }
        if (judgingTypes.contains(Problem.IGNORE_WHITESPACES)){
            output1 = ignoreWhiteSpaces(output1);
            output2 = ignoreWhiteSpaces(output2);
        }
        if (judgingTypes.contains(Problem.IGNORE_SPECIAL_CHARACTERS)){
            output1 = removeSpecialCharacter(output1);
            output2 = removeSpecialCharacter(output2);
        }
        if (judgingTypes.contains(Problem.CASE_INSENSITIVE)){
            return output1.equalsIgnoreCase(output2);
        }
        return output1.equals(output2);
    }

    private String removeSpecialCharacter(String text){
        if (text == null) return null;
        text = text.replaceAll("[^A-Za-z0-9 \t]", "");
        return text;
    }

    private String removeBeginWhiteSpace(String text){
        int i;
        if (text == null) return null;
        int n = text.length();
        for (i = 0; i < n; i++){
            if (!isWhiteSpace(text.charAt(i)))
                break;
        }
        return text.substring(i, n);
    }

    private boolean isWhiteSpace(char ch){
        return Character.compare(ch, ' ') == 0 || Character.compare(ch, '\t') == 0;
    }

    private String ignoreWhiteSpaces(String text){
        if (text == null) return null;
        text = text.replaceAll("[ \t]", "");
        return text;
    }
}