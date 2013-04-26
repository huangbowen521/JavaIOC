package thoughtworks.com.util.config;

import thoughtworks.com.core.config.BeanSetting;
import thoughtworks.com.core.config.Settings;
import thoughtworks.com.properties.PropertyImpl;

import java.util.ArrayList;
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

    public BeanSettingImpl() {
        beanSettings = new ArrayList<BeanSetting>();
    }

    public void add(BeanSetting setting) {
        beanSettings.add(setting);
    }

    private List<BeanSetting> beanSettings;

    public List<thoughtworks.com.core.config.BeanSetting> getBeanConfigs() {
        if (beanSettings.size() <= 0) {
            return getDefaultSettings();
        } else {
            return beanSettings;
        }
    }

    private List<BeanSetting> getDefaultSettings() {
        BeanSetting serviceSetting = new BeanSetting("moviesFinder", "thoughtworks.com.util.model.MoviesFinderImpl");
        BeanSetting clientSetting = new BeanSetting("movieLister", "thoughtworks.com.util.model.MovieLister");
        PropertyImpl setterProperty = new PropertyImpl("moviesFinder", "moviesFinder");
        clientSetting.addSetterProperty(setterProperty);
        return of(serviceSetting, clientSetting);
    }
}
