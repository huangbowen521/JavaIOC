package thoughtworks.com.core.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/5/13
 * Time: 11:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class BeanConfig {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private String name;
    private String className;

    public List<BeanProperty> getBeanProperties() {
        return beanProperties;
    }

    private List<BeanProperty> beanProperties;

    public void addBeanProperty(BeanProperty beanProperty)
    {
        beanProperties.add(beanProperty);
    }

    public BeanConfig(String name, String className) {
        this.name = name;
        this.className = className;
        beanProperties = new ArrayList<BeanProperty>();
    }
}
