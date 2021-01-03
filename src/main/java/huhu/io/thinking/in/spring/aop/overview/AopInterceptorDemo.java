package huhu.io.thinking.in.spring.aop.overview;

import huhu.io.thinking.in.spring.aop.overview.interceptor.AfterInterceptor;
import huhu.io.thinking.in.spring.aop.overview.interceptor.BeforeInterceptor;
import huhu.io.thinking.in.spring.aop.overview.interceptor.ExceptionInterceptor;
import huhu.io.thinking.in.spring.aop.overview.interceptor.FinallyInterceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 五、aop 拦截示例
 *
 * @author huhu
 * @since 2020/1/3
 */
public class AopInterceptorDemo {

    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        Object object = Proxy.newProxyInstance(loader, new Class[]{EchoService.class}, (proxy, method, params) -> {
            String echo = null;
            long endTime = 0L, startTime = 0L;

            if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                try {
                    // 前置拦截
                    BeforeInterceptor before = (proxy1, method1, args1) -> System.currentTimeMillis();
                    startTime = before.before(proxy, method, params);

                    // 目标执行
                    EchoService echoService = new DefaultEchoService();
                    echo = echoService.echo(String.valueOf(params[0]));

                    // 后置拦截
                    AfterInterceptor after = (proxy1, method1, args1, result) -> System.currentTimeMillis();
                    endTime = after.after(proxy, method, params, echo);
                }

                // 异常拦截
                catch (Exception e) {
                    ExceptionInterceptor interceptor = (proxy1, method1, args1, throwable) -> {};
                    interceptor.exception(proxy, method, params, e);
                }

                // 最终拦截
                finally {
                    TimeFinallyInterceptor interceptor = new TimeFinallyInterceptor(startTime, endTime);
                    long costTime = interceptor.finalize(proxy, method, params, echo);
                    System.out.printf("echo方法耗时: %d ms%n", costTime);
                }

                return echo;
            }
            return null;
        });
        EchoService echoService = (EchoService) object;
        String echo = echoService.echo("hello world");
        System.out.println(echo);
    }

    static class TimeFinallyInterceptor implements FinallyInterceptor {

        private final Long startTime;

        private final Long endTime;

        public TimeFinallyInterceptor(Long startTime, Long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public long finalize(Object proxy, Method method, Object[] args, Object result) {
            return endTime - startTime;
        }

    }

}
