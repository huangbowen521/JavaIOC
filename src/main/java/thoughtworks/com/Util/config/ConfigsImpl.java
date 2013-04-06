package thoughtworks.com.Util.config;

import thoughtworks.com.core.config.BeanProperty;
import thoughtworks.com.core.config.Configs;
import thoughtworks.com.core.context.ApplicationContext;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/6/13
 * Time: 12:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigsImpl implements Configs {

    public List<ApplicationContext.BeanConfig1> getBeanConfigs() {
        ApplicationContext.BeanConfig1 serviceConfig = new ApplicationContext.BeanConfig1("moviesFinder", "thoughtworks.com.Util.model.MoviesFinder");
        ApplicationContext.BeanConfig1 clientConfig = new ApplicationContext.BeanConfig1("movieLister", "thoughtworks.com.Util.model.MovieLister");
        BeanProperty beanProperty = new BeanProperty("movieFinder", "movieFinder");
        clientConfig.addBeanProperty(beanProperty);
        return of(serviceConfig, clientConfig);
    }
}
