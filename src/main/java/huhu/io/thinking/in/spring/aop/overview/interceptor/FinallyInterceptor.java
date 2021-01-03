package huhu.io.thinking.in.spring.aop.overview.interceptor;

import java.lang.reflect.Method;

public interface FinallyInterceptor {

    long finalize(Object proxy, Method method, Object[] args, Object result);

}
