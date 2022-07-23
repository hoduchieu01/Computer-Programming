
import java.util.List;

public class Test {
    public static void main(String[] args) {
        testSubproblem1();
        testSubproblem2();
        testSubproblem3();
        testSubproblem4();
    }

    static MovieApp initializeMovieApp() {
        MovieApp movieApp = new MovieApp();
        movieApp.addMovie("Toy Story", new String[] {"love", "touching"});
        movieApp.addMovie("La La Land", new String[] {"touching", "love"});
        movieApp.addMovie("The Jocker", new String[] {"dark", "touching"});
        movieApp.addMovie("Avengers", new String[] {});
        movieApp.addUser("Olivia");
        movieApp.addUser("Jack");
        movieApp.addUser("Blue");
        return movieApp;
    }

    static void testSubproblem1() {
        System.out.println("testSubproblem1");
        MovieApp movieApp = initializeMovieApp();
        Movie jocker = movieApp.findMovie("The Jocker");
        Movie avengers = movieApp.findMovie("Avengers");
        User olivia = movieApp.findUser("Olivia");
        System.out.println("\tShould be The Jocker : " + jocker);
        System.out.println("\tShould be null : " + avengers);
        System.out.println("\tShould be Olivia : " + olivia);
        System.out.println("\tShould be false : " +
                movieApp.addUser("Olivia"));
    }

    static void testSubproblem2() {
        System.out.println("testSubproblem2");
        MovieApp movieApp = initializeMovieApp();
        List<Movie> foundMovies = movieApp.findMoviesWithTags(new String[] {"touching", "love"});
        System.out.println("\tShould be [Toy Story, La La Land] : " + foundMovies);
    }

    static void testSubproblem3() {
        System.out.println("testSubproblem3");
        MovieApp movieApp = initializeMovieApp();
        User olivia = movieApp.findUser("Olivia");
        movieApp.rateMovie(olivia, "The Jocker", 3);
        System.out.println("\tShould be 3 : " + movieApp.getUserRating(olivia, "The Jocker"));
    }

    static void testSubproblem4() {
        System.out.println("testSubproblem4");
        MovieApp movieApp = initializeMovieApp();
        User olivia = movieApp.findUser("Olivia"),
                jack = movieApp.findUser("Jack"),
                blue = movieApp.findUser("Blue");
        List<Movie> foundMovies1 = movieApp.findUserMoviesWithTags(olivia, new String[] {"touching", "love"});
        List<Movie> foundMovies2 = movieApp.findUserMoviesWithTags(olivia, new String[] {"dark"});
        System.out.println("\tShould be [Toy Story, La La Land] : " + foundMovies1);
        System.out.println("\tShould be [The Jocker] : " + foundMovies2);
        movieApp.rateMovie(olivia, "La La Land", 3);
        movieApp.rateMovie(olivia, "Toy Story", 1);
        movieApp.rateMovie(jack, "La La Land", 4);
        movieApp.rateMovie(jack, "The Jocker", 4);
        movieApp.rateMovie(blue, "Toy Story", 5);
        movieApp.rateMovie(blue, "The Jocker", 2);
        List<Movie> recommendedMovies = movieApp.recommend(olivia);
        System.out.println("\tShould be [La La Land, The Jocker, Toy Story] : " + recommendedMovies);
    }
}
