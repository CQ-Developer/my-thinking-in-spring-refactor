package huhu.io.thinking.in.spring.dependency.lookup;

import huhu.io.thinking.in.spring.dependency.annotation.Super;
import huhu.io.thinking.in.spring.dependency.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * test 1
 * <p>dependency lookup demo class
 *
 * @author huhu
 * @see BeanFactory#getBean(String)
 * @see BeanFactory#getBean(String, Class)
 * @see ListableBeanFactory#getBeansOfType(Class)
 * @see ListableBeanFactory#getBeansWithAnnotation(Class)
 * @since 2020/12/16
 */
public class DependencyLookupDemo {

    private static final String XML_FILE_PATH = "classpath:META-INF/dependency-lookup-demo.xml";

    public static void main(String[] args) {
        BeanFactory beanFactory
                = new ClassPathXmlApplicationContext(XML_FILE_PATH);

        // realtime lookup
        lookupRealtime(beanFactory);

        // delay lookup
        lookupDelay(beanFactory);

        // lookup by type
        // lookupByType(beanFactory);

        // lookup by type into collection
        lookupByTypeIntoCollection(beanFactory);

        // lookup by name and type
        lookupByNameAndType(beanFactory);

        // lookup by annotation
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory
                    = (ListableBeanFactory) beanFactory;
            Map<String, Object> beans
                    = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.printf("#lookupByAnnotation: %s%n", beans);
        }
        else {
            System.out.println("lookupByAnnotation: null");
        }
    }

    private static void lookupByNameAndType(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.printf("#lookupByNameAndType: %s%n", user);
    }

    private static void lookupByTypeIntoCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory
                    = (ListableBeanFactory) beanFactory;
            Map<String, User> beans
                    = listableBeanFactory.getBeansOfType(User.class);
            System.out.printf("#lookupByTypeIntoCollection: %s%n", beans);
        }
        else {
            System.out.println("lookupByTypeIntoCollection: null");
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.printf("#lookupByType: %s%n", user);
    }

    private static void lookupRealtime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.printf("#lookupRealtime: %s%n", user);
    }

    private static void lookupDelay(BeanFactory beanFactory) {
        ObjectFactory<?> objectFactory
                = (ObjectFactory<?>) beanFactory.getBean("userObjectFactory");
        User user = (User) objectFactory.getObject();
        System.out.printf("#lookupDelay: %s%n", user);
    }

}
