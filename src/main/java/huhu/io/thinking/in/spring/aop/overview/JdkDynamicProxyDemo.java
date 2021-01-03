package huhu.io.thinking.in.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 三、jdk动态代理
 *
 * @author huhu
 * @see ClassLoader
 * @see java.lang.reflect.Proxy#newProxyInstance(ClassLoader, Class[], InvocationHandler)
 * @since 2021/1/3
 */
public class JdkDynamicProxyDemo {

    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<EchoService>[] interfaces = new Class[]{EchoService.class};
        InvocationHandler h = (proxy, method, args1) -> {
            if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                ProxyEchoService proxyEchoService = new ProxyEchoService(new DefaultEchoService());
                return proxyEchoService.echo(String.valueOf(args1[0]));
            }
            return null;
        };

        Object proxy = Proxy.newProxyInstance(loader, interfaces, h);
        EchoService echoService = (EchoService) proxy;
        String echo = echoService.echo("hello world");
        System.out.println(echo);
    }

}
