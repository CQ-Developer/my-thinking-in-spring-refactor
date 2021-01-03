package huhu.io.thinking.in.spring.aop.overview.interceptor;

import java.lang.reflect.Method;

public interface AfterInterceptor {

    long after(Object proxy, Method method, Object[] args, Object result);

}
