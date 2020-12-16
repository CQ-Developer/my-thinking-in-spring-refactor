package huhu.io.thinking.in.spring.dependency.injection;

import huhu.io.thinking.in.spring.dependency.model.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * demo 2
 * <p>dependency injection demo class
 *
 * @author huhu
 * @since 2020/12/16
 */
public class DependencyInjectionDemo {

    private static final String XML_FILE_PATH = "classpath:META-INF/dependency-injection-demo.xml";

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(XML_FILE_PATH);
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getUsers());

        // BeanFactory injection successfully
        BeanFactory injectedBeanFactory = userRepository.getBeanFactory();
        System.out.println(injectedBeanFactory);

        // NoSuchBeanDefinitionException
        // this means this beanFactory can be injection but not lookup, why ?
        // BeanFactory LookupBeanFactory = beanFactory.getBean(BeanFactory.class);

        // true
        // this means spring inject current ApplicationContext into a bean
        // and this ApplicationContext is a BeanFactory
        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        ApplicationContext applicationContext = objectFactory.getObject();
        System.out.println(applicationContext == beanFactory);
    }

}
