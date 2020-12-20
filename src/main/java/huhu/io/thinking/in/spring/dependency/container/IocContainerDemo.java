package huhu.io.thinking.in.spring.dependency.container;

import huhu.io.thinking.in.spring.dependency.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * demo 3
 * <p>ioc container demo
 *
 * @author huhu
 * @see DefaultListableBeanFactory
 * @since 2020/12/20
 */
public class IocContainerDemo {

    public static void main(String[] args) {
        // create ioc container
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        // load configuration
        int beanDefinitionsCount
                = new XmlBeanDefinitionReader(defaultListableBeanFactory).loadBeanDefinitions("classpath:META-INF/dependency-lookup-demo.xml");
        System.out.printf("%d beans have be loaded%n", beanDefinitionsCount);

        // lookup by type into collection
        lookupByTypeIntoCollection(defaultListableBeanFactory);
    }

    private static void lookupByTypeIntoCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = listableBeanFactory.getBeansOfType(User.class);
            System.out.printf("#lookupByTypeIntoCollection: %s%n", beans);
        }
        else {
            System.out.println("lookupByTypeIntoCollection: null");
        }
    }

}
