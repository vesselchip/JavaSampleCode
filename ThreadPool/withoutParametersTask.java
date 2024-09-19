import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Colin
 * @Date 2024/9/19 10:26
 * @PackageName:PACKAGE_NAME
 * @ClassName: withoutParametersTask
 * @Description: 没有参数的任务
 * @Version 1.0
 */
public class withoutParametersTask {
    public static void main(String[] args) {
        ThreadPoolExecutor job = ThreadPoolConfig.getExecutor();
        for (int i = 0; i < 100; i++) {
            job.execute(()-> {
                System.out.println("执行异步任务 - " + Thread.currentThread().getName());
            });
        }
        job.shutdown();
    }
}
