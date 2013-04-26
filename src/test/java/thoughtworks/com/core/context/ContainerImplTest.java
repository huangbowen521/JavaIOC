package thoughtworks.com.core.context;

import org.junit.Test;
import thoughtworks.com.core.config.BeanSetting;
import thoughtworks.com.properties.Property;
import thoughtworks.com.util.config.BeanSettingImpl;
import thoughtworks.com.util.model.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNot.not;


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
    public void shouldGetSingletonBeanIfAnnotatedSingleton() {
        // given
        BeanSettingImpl beanSetting = new BeanSettingImpl();
        beanSetting.add(new BeanSetting("person", "thoughtworks.com.util.model.Person"));

        // when
        ContainerImpl container = new ContainerImpl(beanSetting);

        //then
        Person person = container.getBean("person");
        Person person1 = container.getBean("person");
        assertThat(person, is(person1));
    }

    @Test
    public void shouldGetDifferentBeanIfNotAnnotatedSingleton() {
        //given
        BeanSettingImpl beanSetting = new BeanSettingImpl();
        beanSetting.add(new BeanSetting("moviesFinder", "thoughtworks.com.util.model.MoviesFinderImpl"));

        // when
        ContainerImpl container = new ContainerImpl(beanSetting);

        // then
        MoviesFinderImpl moviesFinder1 = container.getBean("moviesFinder");
        MoviesFinderImpl moviesFinder2 = container.getBean("moviesFinder");
        assertThat(moviesFinder1, is(not(moviesFinder2)));
    }

    @Test
    public void shouldGetBeanWithConstorctorParameters() {
        //given
        BeanSettingImpl beanSetting = new BeanSettingImpl();
        BeanSetting warriorSetting = new BeanSetting("warrior", "thoughtworks.com.util.model.Warrior");
        warriorSetting.addConProeprty(new Property("weapon", "gun"));
        beanSetting.add(warriorSetting);
        beanSetting.add(new BeanSetting("gun", "thoughtworks.com.util.model.Gun"));

        // when
        ContainerImpl container = new ContainerImpl(beanSetting);

        // then
        Warrior warrior = container.getBean("warrior");
        assertThat(warrior.getWeapon(), instanceOf(Gun.class));

    }

    @Test
    public void shouldGetCorrectBeanWithProperty() {
        // given
        BeanSettingImpl beanSetting = new BeanSettingImpl();
        BeanSetting serviceSetting = new BeanSetting("moviesFinder", "thoughtworks.com.util.model.MoviesFinderImpl");
        BeanSetting clientSetting = new BeanSetting("movieLister", "thoughtworks.com.util.model.MovieLister");
        Property setterProperty = new Property("moviesFinder", "moviesFinder");
        clientSetting.addSetterProperty(setterProperty);
        beanSetting.add(serviceSetting);
        beanSetting.add(clientSetting);


        //when
        ContainerImpl applicationContext = new ContainerImpl(beanSetting);

        //then
        assertThat(((MovieLister) applicationContext.getBean("movieLister")).getMoviesFinder(), instanceOf(MoviesFinderImpl.class));
    }


}
