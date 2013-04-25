package thoughtworks.com.util.model;

import com.google.common.base.Predicate;
import com.sun.istack.internal.Nullable;

import java.util.List;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/6/13
 * Time: 12:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class MovieLister {

    public MoviesFinder getMoviesFinder() {
        return moviesFinder;
    }

    private MoviesFinder moviesFinder;

    public List<Movie> findMoviesByDirector(final String director) {

        List<Movie> movies = moviesFinder.getMovies();

        return newArrayList(filter(movies, new Predicate<Movie>() {
            public boolean apply(@Nullable Movie input) {
                return input.getDirector().equals(director);
            }
        }));
    }
}
