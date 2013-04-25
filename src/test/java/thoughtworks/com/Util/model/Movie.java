package thoughtworks.com.util.model;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/6/13
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class Movie {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    private String name;
    private String director;

    public Movie(String name, String director) {
        this.name = name;
        this.director = director;
    }
}
