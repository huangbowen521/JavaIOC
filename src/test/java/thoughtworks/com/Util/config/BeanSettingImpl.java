package thoughtworks.com.util.config;

import thoughtworks.com.properties.SetterProperty;
import thoughtworks.com.core.config.Settings;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/6/13
 * Time: 12:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class BeanSettingImpl implements Settings {

    public List<thoughtworks.com.core.config.BeanSetting> getBeanConfigs() {
        thoughtworks.com.core.config.BeanSetting serviceSetting = new thoughtworks.com.core.config.BeanSetting("moviesFinder", "thoughtworks.com.util.model.MoviesFinderImpl");
        thoughtworks.com.core.config.BeanSetting clientSetting = new thoughtworks.com.core.config.BeanSetting("movieLister", "thoughtworks.com.util.model.MovieLister");
        SetterProperty setterProperty = new SetterProperty("moviesFinder", "moviesFinder");
        clientSetting.addBeanProperty(setterProperty);
        return of(serviceSetting, clientSetting);
    }
}
