package huhu.io.thinking.in.spring.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 四、目标过滤
 *
 * @author huhu
 * @since 2021/1/3
 */
public class TargetFilterDemo {

    public static void main(String[] args) throws Exception {
        String targetName = "huhu.io.thinking.in.spring.aop.overview.EchoService";

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> targetCLass = classLoader.loadClass(targetName);

        // 找到指定类中的指定方法
        Method method = ReflectionUtils.findMethod(targetCLass, "echo", String.class);
        System.out.println(method);

        // 有条件的过滤
        ReflectionUtils.doWithMethods(targetCLass,
                mc -> System.out.printf("仅抛出运行时异常的方法: %s%n", mc), TargetFilterDemo::filter);
    }

    private static boolean filter(Method method) {
        Class<?>[] exceptionTypes = method.getExceptionTypes();
        int exceptionCount = exceptionTypes.length;

        Class<?>[] parameterTypes = method.getParameterTypes();
        int parameterCount = method.getParameterCount();

        return parameterCount == 1 && String.class.isAssignableFrom(parameterTypes[0]) &&
                exceptionCount == 1 && RuntimeException.class.isAssignableFrom(exceptionTypes[0]);
    }

}
