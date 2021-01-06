package huhu.io.thinking.in.spring.aop.overview;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 六、CGLIB 动态代理示例
 *
 * @author huhu
 * @since 2020/1/6
 */
public class CglibDynamicProxyDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DefaultEchoService.class);
        enhancer.setInterfaces(new Class[]{EchoService.class});

        // 设置方法拦截
        enhancer.setCallback((MethodInterceptor) (object, method, objects, methodProxy) -> {
            long start = System.currentTimeMillis();

            // 原始的 method 可以用于判断是否执行，毕竟不需要把目标对象中的所有方法都进行代理拦截
            // 错误 Object invoke = method.invoke(object, objects);

            // 调用目标方法的正确方式
            Object invoke = methodProxy.invokeSuper(object, objects);

            long cost = System.currentTimeMillis() - start;
            System.out.printf("[cglib] 字节码提升 echo 方法执行时间: %d ms%n", cost);
            return invoke;
        });

        // 生成代理对象
        EchoService echoService = (EchoService) enhancer.create();
        System.out.println(echoService.echo("hello"));
    }

}
