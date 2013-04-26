package thoughtworks.com.core.config;

import thoughtworks.com.properties.PropertyImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/5/13
 * Time: 11:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class BeanSetting {
    public ArrayList<PropertyImpl> getConProperties() {
        return conProperties;
    }

    private ArrayList<PropertyImpl> conProperties;

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

    public List<PropertyImpl> getSetterProperties() {
        return setterProperties;
    }

    private List<PropertyImpl> setterProperties;

    public void addSetterProperty(PropertyImpl setterProperty)
    {
        setterProperties.add(setterProperty);
    }

    public void addConProeprty(PropertyImpl conProperty) {
        conProperties.add(conProperty);
    }

    public BeanSetting(String name, String className) {
        this.name = name;
        this.className = className;
        setterProperties = new ArrayList<PropertyImpl>();
        this.conProperties = new ArrayList<PropertyImpl>();
    }
}
