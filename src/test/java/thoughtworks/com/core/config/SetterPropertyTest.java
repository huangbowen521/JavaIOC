package thoughtworks.com.core.config;

import org.junit.Test;
import thoughtworks.com.Util.model.MoviesFinderImpl;
import thoughtworks.com.core.context.ApplicationContext;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/25/13
 * Time: 10:45 AM
 */
public class SetterPropertyTest {

    @Test
    public void shouldGetCorrectObject() throws IllegalAccessException, InstantiationException {

        // given
        SetterProperty setterProperty = new SetterProperty("finder", "moviesFinder");
        ApplicationContext applicationContext = mock(ApplicationContext.class);
        given(applicationContext.getBean("moviesFinder")).willReturn(new MoviesFinderImpl());

        // when
        Object thisInstance = setterProperty.getThisInstance(applicationContext);

        // then
        assertThat(thisInstance, instanceOf(MoviesFinderImpl.class));
    }
}
