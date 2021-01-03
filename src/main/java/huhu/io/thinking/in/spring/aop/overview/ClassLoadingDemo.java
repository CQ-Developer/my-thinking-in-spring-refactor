package huhu.io.thinking.in.spring.aop.overview;

/**
 * 一、类加载示例工程
 *
 * @author huhu
 * @see ClassLoader
 * @since 2021/1/3
 */
public class ClassLoadingDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        ClassLoader parentClassLoader = classLoader.getParent();
        while (parentClassLoader != null) {
            System.out.println(parentClassLoader);
            parentClassLoader = parentClassLoader.getParent();
        }
    }

}
