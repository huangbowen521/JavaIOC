package thoughtworks.com.core.context;

import sun.tools.tree.NewArrayExpression;
import thoughtworks.com.core.config.BeanConfig;
import thoughtworks.com.core.config.BeanProperty;
import thoughtworks.com.core.config.Configs;

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
}
