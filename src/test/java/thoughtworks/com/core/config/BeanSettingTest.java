package thoughtworks.com.core.config;

import org.junit.Test;
import thoughtworks.com.properties.SetterProperty;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/25/13
 * Time: 11:21 AM
 */
public class BeanSettingTest {

    @Test
    public void shouldInitPropertiesCorrect() {
        BeanSetting beanSetting = new BeanSetting("movieList", "thoughtworks.com.util.model.MovieLister");

        beanSetting.addBeanProperty(new SetterProperty("moiveFinder", "thoughtworks.com.util.model.MoviesFinderImpl"));



    }
}
