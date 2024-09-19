import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Colin
 * @Date 2024/9/19 11:08
 * @PackageName:PACKAGE_NAME
 * @ClassName: invokeAllParametersTask
 * @Description: 发布多线程任务，同时将阻塞，等待所有任务完成，最终获取到所有的任务结果
 * @Description: 适用于批量处理一些任务，并返回一组结果
 * @Version 1.0
 */
public class invokeAllParametersTask {
    static class ParameterizedCallableTask implements Callable<Object> {
        private Integer taskId;
        private String taskName;
        public ParameterizedCallableTask(int taskId, String taskName) {
            this.taskId = taskId;
            this.taskName = taskName;
        }

        @Override
        public Object call() throws Exception {
            return "任务名称: " + taskName + taskId + " 已完成";
        }
    }


    public static void main(String[] args) {
        ThreadPoolExecutor job = ThreadPoolConfig.getExecutor();
        List<Callable<Object>> taskList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            taskList.add(new ParameterizedCallableTask(i, "task"));
            System.out.println("发布异步任务 - " + i);
        }
        try {
            //将会阻塞，等待所有任务完成
            List<Future<Object>> futures = job.invokeAll(taskList);
            for (Future<Object> future : futures)
                System.out.println(future.get());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            job.shutdown();
        }
    }
}
