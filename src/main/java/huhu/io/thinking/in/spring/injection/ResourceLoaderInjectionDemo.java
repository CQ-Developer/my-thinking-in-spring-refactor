package huhu.io.thinking.in.spring.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * demo 4
 * <p> 注入 {@link ResourceLoader} 示例工程
 *
 * @author huhu
 * @see Value
 * @see ResourceLoader
 * @since 2020/12/21
 */
public class ResourceLoaderInjectionDemo implements ResourceLoaderAware {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private AbstractApplicationContext abstractApplicationContext;

    private ResourceLoader resourceLoaderAware;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        resourceLoaderAware = resourceLoader;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(ResourceLoaderInjectionDemo.class);
        annotationConfigApplicationContext.refresh();

        ResourceLoaderInjectionDemo demo = annotationConfigApplicationContext.getBean(ResourceLoaderInjectionDemo.class);

        System.out.println("resourceLoader == abstractApplicationContext ? " + (demo.resourceLoader == demo.abstractApplicationContext));
        System.out.println("resourceLoader == resourceLoaderAware ? " + (demo.resourceLoader == demo.resourceLoaderAware));

        annotationConfigApplicationContext.close();
    }

}
