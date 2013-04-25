package thoughtworks.com.core.context;

import thoughtworks.com.core.config.BeanSetting;
import thoughtworks.com.core.config.SetterProperty;
import thoughtworks.com.core.config.Configs;

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
public class ApplicationContextImpl implements ApplicationContext {

    private Configs configs;
    ConcurrentMap<String, Object> beans = new ConcurrentHashMap<String, Object>();
    ConcurrentMap<String, Class> clazzs = new ConcurrentHashMap<String, Class>();

    public ApplicationContextImpl(Configs configs) {
        this.configs = configs;
        initBeans();
    }

    public <T> T getBean(String beanName)  {
        return (T)beans.get(beanName);
    }

    private void initBeans()
    {
        for(BeanSetting beanSetting : configs.getBeanConfigs())
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

    private void initProperties(BeanSetting beanSetting)
    {
        Object bean = beans.get(beanSetting.getName());
        Method[] methods = bean.getClass().getMethods();
        for (SetterProperty setterProperty : beanSetting.getSetterProperties())
        {
            for(Method method : methods)
            {
                if (method.getName().startsWith("set") && method.getName().substring(3).equalsIgnoreCase(setterProperty.getName())){
                    try {
                        method.invoke(bean,clazzs.get(setterProperty.getRef()).newInstance());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (InstantiationException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }
    }


}
