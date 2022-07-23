import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BackEnd extends ServerResourceAccessible {
    // Use getServerStorageDir() as a default directory
    // TODO sub-program 1 ~ 4 :
    // Create helper functions to support FrontEnd class

    private final String serverStorageDir;
    BackEnd() {
        serverStorageDir = getServerStorageDir();
        load();
    }

    // Problem 1
    // GetUserPassword from userId
    public String getUserPassword(String id) {
        List<String> line = readFile(getFile(id + "/password.txt"));
        String userPassword = null;
        if(line.size() == 1){
            userPassword = line.get(0);
        }
        return userPassword;
    }

    // Check the valid of userId
    public boolean isValidId(String id){
        File filePath = new File(getServerStorageDir());
        List<String> userDirList = Arrays.asList(filePath.list());
        return userDirList.contains(id);
    }

    // sub-problem 2
    private int fileId = -1;
    private Map<String, List<Post>> wrotePostMap = new HashMap<>();

    public void writePost(User user, String title, String content) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int id = ++ fileId;
        Post newPost = new Post(id, currentDateTime, title, content);
        String postPath = serverStorageDir + String.format("%s/post/%d.txt", user.id, id);

        try {
            FileWriter myWriter = new FileWriter(postPath);
            myWriter.write(newPost.getDate() + "\n");
            myWriter.write(newPost.getTitle() + "\n");
            myWriter.write("\n");
            myWriter.write(newPost.getContent());
            myWriter.close();
        }
        catch (IOException ignored) {
        }

        if(wrotePostMap.containsKey(user.id)) {
            wrotePostMap.get(user.id).add(new Post(id, currentDateTime, title, content));
        }
        wrotePostMap.put(user.id, new ArrayList<>());
    }

    public void load() {
        File[] userDirsList = (new File(serverStorageDir)).listFiles();
        for(File dir : userDirsList) {
            String user = dir.getName();

            File[] postFiles = (new File(dir.getPath() + "/post/")).listFiles();
            List<Post> posts = new ArrayList<>();

            if(postFiles != null) {
                for (File post : postFiles) {
                    List<String> line = readFile(post);
                    String dateTimeString = line.get(0);

                    int id = Integer.parseInt(post.getName().split("\\.")[0]);
                    fileId = Math.max(id, fileId);

                    LocalDateTime dateTime = Post.parseDateTimeString(dateTimeString, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                    String title = line.get(1);
                    String content = String.join("\n", line.subList(3, line.size()));
                    posts.add(new Post(id, dateTime, title, content));
                }
            }
            wrotePostMap.put(user, posts);
        }
    }
    // sub-problem 3

    public List<Post> recommend(User user, int n) {
        List<String> friendList = getFriend(user);

        List<Post> posts = new ArrayList<>();
        for(String friend : friendList) {
            List<Post> friendPosts = getPost(friend);
            if (friendPosts != null) posts.addAll(friendPosts);
        }
        posts.sort((Post o1, Post o2) -> {
            LocalDateTime dateTime1 = Post.parseDateTimeString(o1.getDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
            LocalDateTime dateTime2 = Post.parseDateTimeString(o2.getDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
            return dateTime2.compareTo(dateTime1);
        });
        return posts.size() < n ? posts : posts.subList(0, n);
    }

    public List<String> getFriend(User user){
        String userId = user.id;
        File file = new File(serverStorageDir + userId + "/friend.txt");
        List<String> friendList = readFile(file);
        return friendList;
    }

    public List<Post> getPost(String id){
        if(wrotePostMap.containsKey(id)){
            return wrotePostMap.get(id);
        }
        return null;
    }
    // sub-problem 4 search keywords
    public List<Post> getAllPost(){
        ArrayList<Post> listAllPost = new ArrayList<>();
        File[] userDirsList = (new File(serverStorageDir)).listFiles();
        for(File dir : userDirsList) {
            String user = dir.getName();
            listAllPost.addAll(wrotePostMap.get(user));
        }
        return listAllPost;
    }

    public int searchKeywords(Post post, HashSet<String> keywords){
        String postContent = post.getTitle() + " " + post.getContent();
        int count = 0;
        for (String word : postContent.split("[ \n.,;?!:]")){
            if(keywords.contains(word))
                count ++;
        }
        return count;
    }

    public List<Post> search(HashSet<String> keywords){
        List<Post> allPost = getAllPost();
        HashMap<Post, Integer> frequency = new HashMap<Post, Integer>();
        for (Post post : allPost){
            frequency.put(post, searchKeywords(post, keywords));
        }

        allPost.sort(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                if(frequency.get(o1).compareTo(frequency.get(o2)) != 0)
                    return - frequency.get(o1).compareTo(frequency.get(o2));
                return wordCount(o2.getContent()) - wordCount(o1.getContent());
            }
        });
        return allPost.size() < 10 ? allPost : allPost.subList(0, 10);
    }

    public static int wordCount(String s){
        int numWords = 0;
        char firstChar = s.charAt(0);
        if(firstChar != ' ' && firstChar != '\t' && firstChar != '\n'){
            numWords ++;
        }
        for(int i = 1; i < s.length(); i ++){
            char secondChar = s.charAt(i);
            if(firstChar == ' ' && secondChar != ' ' && secondChar != '\n' && secondChar != '\t'){
                numWords ++;
            }
            else if(firstChar == '\n' && secondChar != ' ' && secondChar != '\n' && secondChar != '\t'){
                numWords ++;
            }
            else if(firstChar == '\t' && secondChar != ' ' && secondChar != '\n' && secondChar != '\t'){
                numWords ++;
            }
            firstChar = secondChar;
        }
        return numWords;
    }


    // Java Read File
    public List<String> readFile(File file) {
        if(file == null) return null;
        List<String> line = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNext()) {
                line.add(myReader.nextLine());
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            return null;
        }
        return line;
    }

    // Java Get File
    public File getFile(String path) {
        return new File(serverStorageDir + path);
    }


}
