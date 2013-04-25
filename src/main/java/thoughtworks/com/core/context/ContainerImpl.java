package thoughtworks.com.core.context;

import thoughtworks.com.core.config.BeanSetting;
import thoughtworks.com.properties.SetterProperty;
import thoughtworks.com.core.config.Settings;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/6/13
 * Time: 12:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContainerImpl implements Container {

    private Settings settings;
    ConcurrentMap<String, Object> beans = new ConcurrentHashMap<String, Object>();
    ConcurrentMap<String, Class> clazzs = new ConcurrentHashMap<String, Class>();

    public ContainerImpl(Settings settings) {
        this.settings = settings;
        initBeans();
    }

    public <T> T getBean(String beanName)  {
        return (T)beans.get(beanName);
    }

    private void initBeans()
    {
        for(BeanSetting beanSetting : settings.getBeanConfigs())
        {
            try {
                Object bean = this.getClass().getClassLoader().loadClass(beanSetting.getClassName()).newInstance();
                beans.put(beanSetting.getName(), bean);
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
        Object bean = beans.get(beanSetting.getName());
        Method[] methods = bean.getClass().getMethods();

        Field[] fields = bean.getClass().getDeclaredFields();


        for (SetterProperty setterProperty : beanSetting.getSetterProperties())
        {
            for (Field filed : fields){
                if(filed.getName().equalsIgnoreCase(setterProperty.getName())){
                    filed.setAccessible(true);
                    filed.set(bean, setterProperty.getThisInstance(this));
                }
            }
        }
    }


}
