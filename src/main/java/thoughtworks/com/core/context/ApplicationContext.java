package thoughtworks.com.core.context;

import thoughtworks.com.core.config.BeanProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/5/13
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationContext {
    <T> T getBean(String beanName) throws InstantiationException, IllegalAccessException;

}
