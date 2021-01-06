package huhu.io.thinking.in.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Class<?>[] classes = new Class[]{EchoService.class};

        InvocationHandler invocationHandler = (proxy, method, args) -> {
            if (EchoService.class.isAssignableFrom(proxy.getClass())) {
                if ("echo".equalsIgnoreCase(method.getName())) {
                    DefaultEchoService defaultEchoService = new DefaultEchoService();
                    return defaultEchoService.echo((String) args[0]);
                }
            }
            return null;
        };

        Object proxy = Proxy.newProxyInstance(classLoader, classes, invocationHandler);
        EchoService echoService = (EchoService) proxy;
        String world = echoService.echo("hello world");
        System.out.println(world);

        System.out.println("proxy对象是否继承自java.lang.reflect.Proxy: " + (proxy instanceof Proxy));
        System.out.println("proxy对象是否实现了huhu.io.thinking.in.spring.aop.overview.EchoService接口: " + (proxy instanceof EchoService));
    }

}
