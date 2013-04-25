package thoughtworks.com.properties;

import thoughtworks.com.core.context.Container;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/5/13
 * Time: 11:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyImpl implements Property {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    private String name;
    private String ref;

    public PropertyImpl(String name, String ref) {
        this.name = name;
        this.ref = ref;
    }

    public Object getThisInstance(Container container) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return container.getBean(ref);
    }
}
