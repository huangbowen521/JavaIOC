package thoughtworks.com.core.config;

import org.junit.Test;
import thoughtworks.com.util.model.MoviesFinderImpl;
import thoughtworks.com.core.context.Container;
import thoughtworks.com.properties.SetterProperty;

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

//        // given
//        SetterProperty setterProperty = new SetterProperty("finder", "moviesFinder");
//        Container container = mock(Container.class);
//        given(container.getBean("moviesFinder")).willReturn(new MoviesFinderImpl());
//
//        // when
//        Object thisInstance = setterProperty.getThisInstance(container);
//
//        // then
//        assertThat(thisInstance, instanceOf(MoviesFinderImpl.class));
    }
}
