package thoughtworks.com.core.Factories;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/6/13
 * Time: 1:37 AM
 * To change this template use File | Settings | File Templates.
 */
public interface FactoryBean<T> {
    T getBean();
}
