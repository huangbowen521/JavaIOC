package thoughtworks.com.util.model;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/25/13
 * Time: 1:33 PM
 */
public class MovieListerCon {

    public MovieListerCon(MoviesFinder moviesFinder) {

        this.moviesFinder = moviesFinder;
    }

    private MoviesFinder moviesFinder;
}
