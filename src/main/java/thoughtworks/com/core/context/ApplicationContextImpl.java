package thoughtworks.com.core.context;

import sun.awt.windows.ThemeReader;
import sun.tools.tree.NewArrayExpression;
import thoughtworks.com.core.config.BeanConfig;
import thoughtworks.com.core.config.BeanProperty;
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
    }

    public <T> T getBean(String beanName)  {
        return null;
    }

    private void initBeans()
    {
        for(BeanConfig beanConfig : configs.getBeanConfigs())
        {
            try {
                Object bean = this.getClass().getClassLoader().loadClass(beanConfig.getClassName()).newInstance();
                beans.put(beanConfig.getName(), bean);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void initProperties(BeanConfig beanConfig)
    {
        Object bean = beans.get(beanConfig.getName());
        Method[] methods = bean.getClass().getMethods();
        for (BeanProperty beanProperty : beanConfig.getBeanProperties())
        {
            for(Method method : methods)
            {
                if (method.getName().startsWith("set") && method.getName().substring(3).equalsIgnoreCase(beanProperty.getName())){
                    try {
                        method.invoke(bean,this.getClass().getClassLoader().loadClass(beanProperty.getRef()).newInstance());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (InstantiationException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }
    }
}
