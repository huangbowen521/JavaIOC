package thoughtworks.com.core.context;

import org.junit.Test;
import thoughtworks.com.util.config.BeanSettingImpl;
import thoughtworks.com.util.model.MovieLister;
import thoughtworks.com.util.model.MoviesFinderImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;


/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/6/13
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContainerImplTest {
    @Test
    public void shouldGetCorrectBeanWithoutProperty() throws Exception {
        // when
        ContainerImpl applicationContext = new ContainerImpl(new BeanSettingImpl());


        // then
        assertThat(applicationContext.getBean("moviesFinder"), instanceOf(MoviesFinderImpl.class));
    }


    @Test
    public void shouldGetCorrectBeanWithProperty() {
        //when
        ContainerImpl applicationContext = new ContainerImpl(new BeanSettingImpl());

        //then
        assertThat(((MovieLister) applicationContext.getBean("movieLister")).getMoviesFinder(), instanceOf(MoviesFinderImpl.class));
    }


}
