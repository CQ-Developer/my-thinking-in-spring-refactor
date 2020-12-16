package huhu.io.thinking.in.spring.dependency.annotation;

import java.lang.annotation.*;

/**
 * simple annotation for lookup demo
 *
 * @author huhu
 * @since 2020/12/16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Super {
}
