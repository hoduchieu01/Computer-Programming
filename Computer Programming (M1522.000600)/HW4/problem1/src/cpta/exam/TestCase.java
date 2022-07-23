package cpta.exam;

public class TestCase {
    public String id;
    public String inputFileName;
    public String outputFileName;
    public double score;

    public TestCase(String id, String inputFileName, String outputFileName, double score) {
        this.id = id;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.score = score;
    }
}

