import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Colin
 * @Date 2024/9/19 10:12
 * @PackageName:PACKAGE_NAME
 * @ClassName: ThreadPoolConfig
 * @Description: 配置线程池，一次配置多次调用。减少配置代码
 * @Version 1.0
 */
public class ThreadPoolConfig {
    private static ThreadPoolExecutor executor;
    static {
        int corePoolSize = 10;
        int maxPoolSize = 20;
        long keepAliveTime = 60;
        int queueCapacity = 100;

        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueCapacity),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static ThreadPoolExecutor getExecutor() {
        return executor;
    }
}
