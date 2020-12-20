package huhu.io.thinking.in.spring.resourceLoader;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * demo 2
 * <p> 带有字符编码的 {@link ResourceLoader} 示例工程
 *
 * @author huhu
 * @since 2020/12/20
 */
public class EncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args) {
        String path = "C:/Dev/project/my-thinking-in-spring-refactor/pom.xml";
        ResourceLoader resourceLoader = new FileSystemResourceLoader();

        Resource resource = resourceLoader.getResource(path);
        EncodedResource encodedResource = new EncodedResource(resource, StandardCharsets.UTF_8);

        try (InputStream inputStream = encodedResource.getInputStream()) {
            String data = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            System.out.println(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
