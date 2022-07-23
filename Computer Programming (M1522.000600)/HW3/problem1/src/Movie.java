
import java.util.*;

public class Movie implements Comparable<Movie>{
    private String title;
    private List<String> tags;

    public Movie(String title) { this.title = title;}

    public void addTags(String[] tags){
        this.tags = Arrays.asList(tags);
    }

    @Override
    public String toString() {
        return title;
    }

    // problem 2
    @Override
    public int compareTo(Movie movie) {
        return this.toString().compareTo(movie.toString());
    }

    public boolean containsTags(String[] tags){
        if (tags == null || tags.length == 0) {
            return false;
        }
        for (String tag:tags){
            if (!this.tags.contains(tag)) return false;
        }
        return true;
    }

    public String getTitle() {
        return title;
    }

    // problem 3
    private Map<User, Integer> userRatingMap = new HashMap<>();
    public void userRating(User user, int rating){
        userRatingMap.put(user, rating);
    }

    // problem 4
    public Double getMedianRating(){
        if(userRatingMap.size() == 0) return 0.0;
        List<Integer> entryValue = new ArrayList<>();
        for (Map.Entry<User, Integer> entry : userRatingMap.entrySet()){
                entryValue.add(entry.getValue());
        }
        double medianRating = 0.0;
        Collections.sort(entryValue);
        // if there are movies with the same values even in the median ratings, use the average value of two middle values as the median raing
        if(entryValue.size() % 2 == 0){
            medianRating = (double) (entryValue.get((entryValue.size() - 1) / 2) + entryValue.get((entryValue.size()) / 2)) / 2;
        }
        else{
            medianRating = (double) entryValue.get((entryValue.size() - 1) / 2);
        }
        return medianRating;
    }
}
