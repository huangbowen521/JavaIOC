package thoughtworks.com.core.context;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.sun.istack.internal.Nullable;
import thoughtworks.com.core.config.BeanSetting;
import thoughtworks.com.core.config.Settings;
import thoughtworks.com.properties.Property;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
                        T bean = getInstanceBy(beanSetting);
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

    private <T> T getInstanceBy(BeanSetting beanSetting) {
        T bean = null;
        try {

            if (beanSetting.getConProperties().size() > 0) {
                Constructor<?>[] constructors = Class.forName(beanSetting.getClassName()).getConstructors();

                final Container that = this;
                Object[] objects = Lists.transform(beanSetting.getConProperties(), new Function<Property, Object>() {
                    public Object apply(@Nullable Property input) {
                        try {
                            return that.getBean(input.getRef());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    ;
                }).toArray();
                for (Constructor constructor : constructors) {
                    if (constructor.isAnnotationPresent(Inject.class)) {
                        try {
                            bean = (T) constructor.newInstance(objects);
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {

                bean = (T) this.getClass().getClassLoader().loadClass(beanSetting.getClassName()).newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        injectFields(bean, beanSetting);

        return bean;
    }

    private void injectFields(Object bean, BeanSetting beanSetting)  {

        Field[] fields = bean.getClass().getDeclaredFields();

        for (Property setterProperty : beanSetting.getSetterProperties()) {
            for (Field filed : fields) {
                if (filed.getName().equalsIgnoreCase(setterProperty.getName())) {
                    filed.setAccessible(true);
                    try {
                        filed.set(bean, this.getBean(setterProperty.getRef()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
