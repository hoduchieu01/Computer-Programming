import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {
        String rootAuthFile = "test/root_auth.in";
        onTest("Test 1 - Login O", rootAuthFile, "test/exit_post.in", "test/1_1.out");
        onTest("Test 1 - Login X", "test/1.in", "test/exit_post.in", "test/1_2.out");
        onTest("Test 2", rootAuthFile, "test/2_post.in", "test/2.out");
        onTest("Test 3", rootAuthFile, "test/3_post.in", "test/3.out");
        onTest("Test 4", rootAuthFile, "test/4_post.in", "test/4.out");
    }

    static void printOX(String prompt, boolean condition) {
        if (condition) {
            print(prompt + " : O");
        } else {
            print(prompt + " : X");
        }
    }
    
    static void print(Object o){
        System.out.println(o);
    }
    
    static void onTest(String testName, String authInput, String postInput, String output) {
        UserInterface ui = new UserInterface();
        BackEnd backend = new BackEnd();
        FrontEnd frontEnd = new FrontEnd(ui,backend);
        ui.createUITest(frontEnd, authInput, postInput);
        ui.run();

        try {
            String content = Files.readString(Paths.get(output)).replaceAll("\\s", "");
            String content2 = (ui.authView.getOutput() + ui.postView.getOutput()).replaceAll("\\s", "");
            printOX(testName, content2.equals(content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
