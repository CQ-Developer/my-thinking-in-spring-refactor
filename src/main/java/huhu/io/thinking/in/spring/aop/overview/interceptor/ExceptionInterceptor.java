package huhu.io.thinking.in.spring.aop.overview.interceptor;

import java.lang.reflect.Method;

public interface ExceptionInterceptor {

    void exception(Object proxy, Method method, Object[] args, Throwable throwable);

}
