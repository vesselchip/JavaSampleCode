import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Author Colin
 * @Date 2024/9/19 14:28
 * @PackageName:PACKAGE_NAME
 * @ClassName: scheduledThreadPoolConfig
 * @Description: 定时、延迟线程池配置
 * @Version 1.0
 */
public class scheduledThreadPoolConfig {
    private static ScheduledThreadPoolExecutor sceduledThreadPool;
    static {
        int corePoolSize = 10;
        sceduledThreadPool = new ScheduledThreadPoolExecutor(corePoolSize);
    }
    public static ScheduledThreadPoolExecutor getExecutor(){
        return sceduledThreadPool;
    }
}
