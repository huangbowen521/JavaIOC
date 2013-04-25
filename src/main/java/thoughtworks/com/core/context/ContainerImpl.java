package thoughtworks.com.core.context;

import thoughtworks.com.core.config.BeanSetting;
import thoughtworks.com.core.config.Settings;
import thoughtworks.com.properties.SetterProperty;

import javax.inject.Singleton;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/6/13
 * Time: 12:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContainerImpl implements Container {

    private Settings settings;
    Map<String, Object> singletonBeans = new HashMap<String, java.lang.Object>();
    Map<String, Class> clazzs = new HashMap<String, Class>();

    public ContainerImpl(Settings settings) {
        this.settings = settings;
    }

    public <T> T getBean(String beanName) {
        if (singletonBeans.containsKey(beanName)) {
            return (T) singletonBeans.get(beanName);
        } else {
            for (BeanSetting beanSetting : settings.getBeanConfigs()) {
                if (beanName.equals(beanSetting.getName())) {
                    try {
                        T bean = getInstanceBy(beanSetting.getClassName());
                        if (Class.forName(beanSetting.getClassName()).isAnnotationPresent(Singleton.class)) {
                            singletonBeans.put(beanName, bean);
                        }

                        return bean;

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }

    private <T> T getInstanceBy(String name) {
        try {
            return (T) this.getClass().getClassLoader().loadClass(name).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void initBeans() {
        for (BeanSetting beanSetting : settings.getBeanConfigs()) {
            try {
                Object bean = this.getClass().getClassLoader().loadClass(beanSetting.getClassName()).newInstance();
                singletonBeans.put(beanSetting.getName(), bean);
                clazzs.put(beanSetting.getName(), Class.forName(beanSetting.getClassName()));
                initProperties(beanSetting);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void initProperties(BeanSetting beanSetting) throws IllegalAccessException, InstantiationException {
        Object bean = singletonBeans.get(beanSetting.getName());
        Method[] methods = bean.getClass().getMethods();

        Field[] fields = bean.getClass().getDeclaredFields();


        for (SetterProperty setterProperty : beanSetting.getSetterProperties()) {
            for (Field filed : fields) {
                if (filed.getName().equalsIgnoreCase(setterProperty.getName())) {
                    filed.setAccessible(true);
                    try {
                        filed.set(bean, setterProperty.getThisInstance(this));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
