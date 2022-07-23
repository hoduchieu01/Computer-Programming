import java.util.*;
import java.time.LocalDateTime;

public class FrontEnd {
    private UserInterface ui;
    private BackEnd backend;
    private User user;

    public FrontEnd(UserInterface ui, BackEnd backend) {
        this.ui = ui;
        this.backend = backend;
    }
    
    public boolean auth(String authInfo){
        // TODO sub-problem 1
        // authenticate th user
        // login can be possible with a valid password


        String[] userAuthInfo = authInfo.split("\\n");
        if(userAuthInfo.length != 2){
            return false;
        }

        String idInput = userAuthInfo[0];
        String passwordInput = userAuthInfo[1];

        if(!backend.isValidId(idInput)) return false;

        String password = backend.getUserPassword(idInput);
        // for successful authentication, the input password and the stored password should be identical including white spaces but case-insensitive
        if(password.toLowerCase().equals(passwordInput.toLowerCase())){
            user = new User(idInput, password);
            return true;
        }
        return false;
    }

    public void post(Pair<String, String> titleContentPair) {
        // TODO sub-problem 2
        // Implement this method to store the written post in the server.
        String title = titleContentPair.key;
        String content = titleContentPair.value;
        backend.writePost(user, title, content);
    }
    
    public void recommend(int N){
        // TODO sub-problem 3
        // implement this method to print the latest posts of the user's friends
        List <Post> posts = backend.recommend(user, N);
        for(Post post : posts)
            ui.println(post.toString());
    }

    public void search(String command) {
        // TODO sub-problem 4
        // implement this method to display up 10 posts that contain at least one keyword
        String[] keyword = command.split(" ");
        HashSet<String> keywords = new HashSet<>(Arrays.asList(keyword).subList(1, keyword.length));
        List<Post> posts = backend.search(keywords);
        // note: The posts should be displayed using the format provided by the getSummary method of the Post class, not the toString method.
        for(Post post : posts)
            ui.println(post.getSummary());
    }
    User getUser(){
        return user;
    }
}
