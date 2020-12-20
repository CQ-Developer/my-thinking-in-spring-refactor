package huhu.io.thinking.in.spring.dependency.container;

import huhu.io.thinking.in.spring.dependency.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * demo 4
 * <p>ioc container with application context
 *
 * @author huhu
 * @since 2020/12/20
 */
public class IocContainerWithApplicationContextDemo {

    public static void main(String[] args) {
        // create application context
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        // register configuration class
        annotationConfigApplicationContext.register(IocContainerWithApplicationContextDemo.class);

        // refresh application context
        annotationConfigApplicationContext.refresh();

        // lookup by type into collection
        lookupByTypeIntoCollection(annotationConfigApplicationContext);

        // close application context
        annotationConfigApplicationContext.close();
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

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("huhu");
        return user;
    }

}
