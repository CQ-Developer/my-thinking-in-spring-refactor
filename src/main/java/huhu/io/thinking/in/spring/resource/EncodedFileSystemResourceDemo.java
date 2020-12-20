package huhu.io.thinking.in.spring.resource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * demo 1
 * <p>带有字符集的 {@link FileSystemResource} 示例工程
 *
 * @author huhu
 * @see EncodedResource
 * @see FileSystemResource
 * @since 2020/12/20
 */
public class EncodedFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String path = "C:/Dev/project/my-thinking-in-spring-refactor/pom.xml";

        EncodedResource encodedResource = new EncodedResource(new FileSystemResource(path), StandardCharsets.UTF_8);
        InputStream inputStream = encodedResource.getInputStream();

        String data = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println(data);
    }

}
