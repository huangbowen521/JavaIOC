package thoughtworks.com.core.context;

import org.junit.Test;
import thoughtworks.com.core.config.BeanConfig;
import thoughtworks.com.core.config.Configs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;


/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/6/13
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationContextImplTest {
    @Test
    public void shouldGetCorrectBeanWithoutProperty() throws Exception {
        // given
        Configs configs = new Configs() {
            public List<BeanConfig> getBeanConfigs() {
                ArrayList<BeanConfig> beanConfigs = new ArrayList<BeanConfig>();
                beanConfigs.add(new BeanConfig("date", "java.util.Date"));
                return beanConfigs;
            }
        };

        // when
        ApplicationContextImpl applicationContext = new ApplicationContextImpl(configs);

        // then
        assertThat(applicationContext.getBean("date"), instanceOf(Date.class));
    }


    @Test
    public void shouldGetCorrectBeanWithProperty() {
        //given
        Configs configs = new Configs() {
            public List<BeanConfig> getBeanConfigs() {
                ArrayList<BeanConfig> beanConfigs = new ArrayList<BeanConfig>();
                beanConfigs.add(new BeanConfig("date", "java.util.Date"));
                return beanConfigs;
            }
        };

        //when

        //then
    }
}
