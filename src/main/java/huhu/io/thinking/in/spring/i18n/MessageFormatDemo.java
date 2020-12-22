package huhu.io.thinking.in.spring.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * demo 1
 *
 * <p> {@link MessageFormat} 示例工程
 *
 * @author huhu
 * @see MessageFormat
 * @since 2020/12/22
 */
public class MessageFormatDemo {

    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";

        String f1 = "At {0,time} on {0,date}, there was {1} on planet {2,number}";
        String f2 = "At {0,time,long} on {0,date,full}, there was {1} on planet {2,number}";
        MessageFormat messageFormat = new MessageFormat(f2);
        String format = messageFormat.format(new Object[]{new Date(), event, planet});
        System.out.println(format);

        // 重置 MessageFormatPattern -> applyPattern
        f1 = "this is txt: {0}, {1}";
        messageFormat.applyPattern(f1);
        format = messageFormat.format(new Object[]{"HelloWorld", "666"});
        System.out.println(format);

        // 重置 Locale -> setLocale
        messageFormat.setLocale(Locale.US);
        messageFormat.applyPattern(f2);
        format = messageFormat.format(new Object[]{new Date(), event, planet});
        System.out.println(format);

        // 重置 format 根据参数索引来设置 pattern -> setFormat
        messageFormat.setFormat(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        format = messageFormat.format(new Object[]{new Date(), event, planet});
        System.out.println(format);
    }

}
