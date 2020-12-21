package huhu.io.thinking.in.spring.injection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * demo 4
 * <p> 注入 {@link Resource} 示例工程
 *
 * @author huhu
 * @see Value
 * @see Resource
 * @since 2020/12/21
 */
public class ResourceInjectionDemo {

    @Value("classpath:META-INF/default.properties")
    private Resource resource;

    @Value("classpath*:META-INF/*.properties")
    private Resource[] resources;

    @Value("${user.dir}")
    private String userDir;

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(ResourceInjectionDemo.class);
        annotationConfigApplicationContext.refresh();

        ResourceInjectionDemo demo = annotationConfigApplicationContext.getBean(ResourceInjectionDemo.class);

        // resource
        String s1 = StreamUtils.copyToString(demo.resource.getInputStream(), StandardCharsets.UTF_8);
        System.out.println(s1);

        // resources
        for (Resource resource : demo.resources) {
            String s2 = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            System.out.println(s2);
        }

        // userDir
        System.out.println(demo.userDir);

        annotationConfigApplicationContext.close();
    }

}
