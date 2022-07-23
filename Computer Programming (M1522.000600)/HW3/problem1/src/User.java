

import java.util.*;

public class User {
    private String username;
    HashMap<Movie, Integer> searchMovies = new HashMap<Movie, Integer>();

    public void search (Movie movie){
        if(searchMovies.containsKey(movie)){
            int frequency = searchMovies.get(movie);
            frequency++;
            searchMovies.remove(movie);
            searchMovies.put(movie, frequency);
        }
        else{
            searchMovies.put(movie, 1);
        }
    }

    public Integer getFrequency (Movie movie){
        return searchMovies.get(movie);
    }


    public User(String username) { this.username = username; }
    @Override
    public String toString() {
        return username;
    }

    // problem 3
    private Map<Movie, Integer> ratedMoviesMap = new HashMap<>();
    public void ratingMovie(Movie movie, int rating){
        ratedMoviesMap.put(movie, rating);
    }

    public int getRating(Movie movie){
        if (ratedMoviesMap.containsKey(movie)){
            return ratedMoviesMap.get(movie);
        }
        return 0;
    }
}
