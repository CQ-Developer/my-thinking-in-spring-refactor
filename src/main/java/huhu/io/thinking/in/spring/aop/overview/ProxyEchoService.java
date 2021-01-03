package huhu.io.thinking.in.spring.aop.overview;

public class ProxyEchoService implements EchoService {

    private final EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String msg) {
        long startTime = System.currentTimeMillis();
        String echo = echoService.echo(msg);
        long costTime = System.currentTimeMillis() - startTime;
        System.out.printf("echo方法耗时: %d ms%n", costTime);
        return echo;
    }

}
