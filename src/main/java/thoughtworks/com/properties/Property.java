package thoughtworks.com.properties;

import thoughtworks.com.core.context.Container;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/25/13
 * Time: 11:04 AM
 */
public interface Property {

     Object getThisInstance(Container container) throws IllegalAccessException, InstantiationException;
}
