package thoughtworks.com.Util.config;

import thoughtworks.com.core.config.BeanSetting;
import thoughtworks.com.core.config.SetterProperty;
import thoughtworks.com.core.config.Configs;

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

    public List<BeanSetting> getBeanConfigs() {
        BeanSetting serviceSetting = new BeanSetting("moviesFinder", "thoughtworks.com.Util.model.MoviesFinder");
        BeanSetting clientSetting = new BeanSetting("movieLister", "thoughtworks.com.Util.model.MovieLister");
        SetterProperty setterProperty = new SetterProperty("movieFinder", "movieFinder");
        clientSetting.addBeanProperty(setterProperty);
        return of(serviceSetting, clientSetting);
    }
}
