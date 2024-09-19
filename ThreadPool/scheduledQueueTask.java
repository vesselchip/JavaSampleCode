import java.util.concurrent.*;

/**
 * @Author Colin
 * @Date 2024/9/19 14:57
 * @PackageName:PACKAGE_NAME
 * @ClassName: scheduledQueueTask
 * @Description: TODO
 * @Version 1.0
 */
public class scheduledQueueTask {

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
        // 用于存储任务结果的队列
        BlockingQueue<Object> resultQueue = new LinkedBlockingQueue<>();
        job.scheduleAtFixedRate(() -> {
            try {
                // 执行任务并获取结果
                Callable<Object> task = new ParameterizedCallableScheduledTask(1, "任务1");
                Object result = task.call();
                resultQueue.offer(result); // 将结果放入队列
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },0,2, TimeUnit.SECONDS);

        // 从队列中获取任务的返回值（模拟不断获取结果）
        new Thread(() -> {
            try {
                while (true) {
                    Object result = resultQueue.take();  // 阻塞获取任务结果
                    System.out.println("获取到的任务结果: " + result);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
