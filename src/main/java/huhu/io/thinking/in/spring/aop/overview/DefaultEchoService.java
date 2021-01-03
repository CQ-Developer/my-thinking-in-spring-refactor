package huhu.io.thinking.in.spring.aop.overview;

public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String msg) {
        return "[ECHO]: " + msg;
    }

}
