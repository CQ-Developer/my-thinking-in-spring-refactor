package huhu.io.thinking.in.spring.aop.overview;

/**
 * 二、静态代理
 *
 * @author huhu
 * @since 2021/1/3
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        String msg = echoService.echo("hello world");
        System.out.println(msg);
    }

}
