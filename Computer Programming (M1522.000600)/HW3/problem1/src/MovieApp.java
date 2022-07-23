
import java.util.*;

public class MovieApp {

    private List<Movie> movieList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<Movie> ratedMovieList = new ArrayList<>();
    Map<User, Set<Movie>> searchHistory = new HashMap<>();

    public boolean addMovie(String title, String[] tags) {
        // TODO sub-problem 1
        // Create a Movie Object with the title and tags and save its in the collection
        // If the given tags are empty, do not create a Movie object and return false
        if(tags.length == 0) {
            return false;
        }
        // If the Movie object with the same title was already registered, do not create Movie object and return false.
        // Return True if a new Movie object is created and stored successfully
        if(findMovie(title) == null){
            Movie movie = new Movie(title);
            movie.addTags(tags);
            movieList.add(movie);
            return true;
        }
        return false;
    }

    public boolean addUser(String name) {
        // TODO sub-problem 1
        // Creates a User object with the name and saves it in the collection
        // If there already exists a user with the given name, do not create, return false
        // Return true if a new object is created successfully
        if(findUser(name) == null){
            User user = new User(name);
            userList.add(user);
            return true;
        }
        return false;
    }

    public Movie findMovie(String title) {
        // TODO sub-problem 1
        // Returns a Movie object with the given title
        // Return null if there is no movie with the given title
        for (Movie movie: movieList){
            if(movie.toString().equals(title))
                return movie;
        }
        return null;
    }

    public User findUser(String username) {
        // TODO sub-problem 1
        // Returns a User object with the given surname
        // Return null if there is no user with the given name
        for (User user: userList){
            if(user.toString().equals(username))
                return user;
        }
        return null;
    }

    public List<Movie> findMoviesWithTags(String[] tags) {
        // TODO sub-problem 2
        // The methods return a List of Movie objects matching all given tags
        // If the given tags are a subset of the tags of a movie, the movie should be included in the output list. Otherwise, the movie should not be included
        // There should be not duplicated movies in the output list
        // The output list should be in reverse lexicographical order of the title of a movie
        // If the given tags match one of the movies, the empty list should be returned.
        // If the empty String array or null is given as a tags argument, the empty list should be returned.

        List<Movie> moviesWithTags = new LinkedList<>();
        for (Movie movie: movieList){
            if (movie.containsTags(tags))
                moviesWithTags.add(movie);
        }
        Collections.sort(moviesWithTags);
        Collections.reverse(moviesWithTags);
        return moviesWithTags;
    }

    public boolean rateMovie(User user, String title, int rating) {
        // TODO sub-problem 3
        // adds a rating of a movie from a user. Note that it is necessary to store which movie was rated
        // do nothing and return false in one of the following cases
        // the title is null or there is no movie with the given title
        // the user is null or not registered
        // the given rating is out of range
        // the valid rating should be between 1 <= rating <= 5
        // otherwise store rating information and return true
        // if a user rates the same movie multiple times, only the last rating is stored
        if(title != null && findMovie(title) != null && user != null && findUser(user.toString()) != null && rating >= 1 && rating <= 5){
            Movie movie = findMovie(title);
            ratedMovieList.add(movie);
            movie.userRating(user, rating);
            user.ratingMovie(movie, rating);
            return true;
        }
        return false;
    }

    public int getUserRating(User user, String title) {
        // TODO sub-problem 3
        // This method returns the rating of the user for the movie with the given title
        // if the user or the movie with the title is null or not registered, return -1
        // if the user has not rated the movie return 0
        if (user != null && title != null && findMovie(title) != null){
            return user.getRating(findMovie(title));
        }
        return -1;
    }

    public List<Movie> findUserMoviesWithTags(User user, String[] tags) {
        // TODO sub-problem 4
        // This method to store the search history of a user. The search history is used for the recommendation
        // This method does two tasks: stores the search history and returns the list of movies matching the given tags
        // If the user is null or not registered, do nothing and return the empty list
        // Otherwise use the findMoviesWithTags to find matching movies
        // store the list of searched movies along with the user information
        if(user != null && findUser(user.toString()) != null){
            List<Movie> search = findMoviesWithTags(tags);
            if (!searchHistory.containsKey(user))
                searchHistory.put(user, new HashSet<>());

            for (Movie m : search){
                searchHistory.get(user).add(m);
                user.search(m);
            }
            return search;
        }
        return new LinkedList<>();
    }

    public List<Movie> recommend(User user) {
        // TODO sub-problem 4
        // if user is null or not registered return the empty list
        // if the movie is not rated before, its median rating is 0
        // if there are less than 3 movies to recommend, then the returned list may contain less than 3 movies. If there is no movie to recommend, return the empty list.
        if (user == null || findUser(user.toString()) == null || searchHistory.get(user) == null)  return new LinkedList<>();

        List<Movie> candidateMovies = new ArrayList<>(searchHistory.get(user));

        candidateMovies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (user.getFrequency(o1).compareTo(user.getFrequency(o2)) != 0)
                    return -user.getFrequency(o1).compareTo(user.getFrequency(o2));
//                System.out.println(o1.getTitle() + " " + o1.getMedianRating());
//                System.out.println(o2.getTitle() + " " + o2.getMedianRating() + " " + o1.getMedianRating().compareTo(o2.getMedianRating()));
                if(o1.getMedianRating().compareTo(o2.getMedianRating()) != 0)
                    return -o1.getMedianRating().compareTo(o2.getMedianRating());
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        return candidateMovies.size() < 3 ? candidateMovies : candidateMovies.subList(0, 3);
    }
}
