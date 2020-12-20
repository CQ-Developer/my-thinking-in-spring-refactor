package huhu.io.thinking.in.spring.pattern;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * demo 3
 * <p> 自定义路径通配资源示例工程
 *
 * @author huhu
 * @see ResourcePatternResolver
 * @since 2020/12/20
 */
public class CustomizedResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        String pattern = "C:/Dev/project/my-thinking-in-spring-refactor/src/main/java/huhu/io/thinking/in/spring/**/*.java";
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        Resource[] resources = resourcePatternResolver.getResources(pattern);
        for (Resource resource : resources) {
            InputStream inputStream = resource.getInputStream();
            String value = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            System.out.println(value);
        }
    }

}
