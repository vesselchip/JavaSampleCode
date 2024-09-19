import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author Colin
 * @Date 2024/9/19 14:44
 * @PackageName:PACKAGE_NAME
 * @ClassName: scheduleParametersTask
 * @Description:  延迟任务执行 -- 阻塞等待所有任务执行完一起返回数据
 * @Version 1.0
 */
public class scheduleParametersTask {
    static class ParameterizedCallableScheduledTask implements Callable<Object> {
        private Integer taskId;
        private String taskName;
        public ParameterizedCallableScheduledTask(int taskId, String taskName) {
            this.taskId = taskId;
            this.taskName = taskName;
        }
        @Override
        public Object call() throws Exception {
            return "任务名称: " + taskName + taskId + " 已完成";
        }
    }


    public static void main(String[] args) {
        ScheduledThreadPoolExecutor job = scheduledThreadPoolConfig.getExecutor();
//        job.schedule(()-> System.out.println("延时任务执行"), 1, TimeUnit.SECONDS);
//        job.scheduleAtFixedRate(()-> System.out.println("定时任务执行_2"), 1, 1, TimeUnit.SECONDS);
//
//        ScheduledFuture<String> delayedTask = job.schedule(()->
//        {
//            System.out.println("延时任务执行");
//            return "延时任务执行完成";
//        }, 1, TimeUnit.SECONDS);
        //创建任务
        List<ScheduledFuture<Object>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Callable<Object> task = new ParameterizedCallableScheduledTask(i, "示例任务");
            //提交任务，延迟执行
            ScheduledFuture<Object> future = job.schedule(task, 2, TimeUnit.SECONDS);
            futures.add(future);
        }
        // 获取任务返回值
        try {
            for (Future<Object> future : futures)
                System.out.println(future.get());  // 阻塞等待任务结果
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        job.shutdown();
    }
}
