package thoughtworks.com.Util.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/25/13
 * Time: 10:55 AM
 */
public class MoviesFinderImpl implements MoviesFinder {
    public List<Movie> getMovies() {
        return new ArrayList<Movie>(){{
            add(new Movie("movie1", "director1"));
            add(new Movie("movie2", "director2"));
        }};
    }
}
