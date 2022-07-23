public class UserInterface {
    private FrontEnd frontend;
    public PostView postView;
    public AuthView authView;
    public View mainView;

    private boolean query(String command) {
        String[] commandSlices = command.split(" ");
        String instruction = commandSlices[0];
        switch(instruction){
            case "exit" :
                return false;
            case "post" :
                post();
                break;
            case "search" :
                search(command);
                break;
            case "recommend" :
                recommend(Integer.parseInt(commandSlices[1]));
                break;
            default:
                println("Illegal Command Format : " + command);
        }
        return true;
    }
    public void createUI(FrontEnd frontend){
        this.frontend = frontend;
        authView = new AuthView();
        postView = new PostView();
        mainView = authView;
    }
    public void createUITest(FrontEnd frontend, String authInput, String postInput){
        this.frontend = frontend;
        authView = new AuthView(authInput);
        postView = new PostView(postInput);
        mainView = authView;
    }
    private void post(){
        frontend.post(postView.getPost("New Post"));
    }
    private void search(String command){
        frontend.search(command);
    }
    private void recommend(int number){
        frontend.recommend(number);
    }
    public void println(Object object){
        mainView.println(object);
    }
    public void print(Object object){
        mainView.print(object);
    }

    public void run(){
        String command;
        String authInfo = authView.getUserInput("------ Authentication ------\n");
        if (frontend.auth(authInfo) ) {
            mainView = postView;
            do {
                command = postView.getUserInput(
                        "-----------------------------------\n" +
                                frontend.getUser().id + "@sns.com\n" +
                                "post : Post contents\n" +
                                "recommend <number> : recommend <number> interesting posts\n" +
                                "search <keyword> : List post entries whose contents contain <keyword>\n" +
                                "exit : Terminate this program\n" +
                                "-----------------------------------\n" +
                                "Command : ");

            } while (query(command));
        }
        else{
            println("Failed Authentication.");
        }
    }

}
