import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Colin
 * @Date 2024/9/19 10:34
 * @PackageName:PACKAGE_NAME
 * @ClassName: inputParametersTask
 * @Description: 只有传入参数的任务
 * @Version 1.0
 */
public class inputParametersTask {

    static class ParameterizedTask implements Runnable {
        private int taskId;
        private String taskName;
        public ParameterizedTask(int taskId, String taskName) {
            this.taskId = taskId;
            this.taskName = taskName;
        }
        @Override
        public void run() {
            //Task code
            System.out.println("Task " + taskId + " " + taskName + " is running");
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor job = ThreadPoolConfig.getExecutor();
        for (int i = 0; i < 100; i++) {
            job.execute(new ParameterizedTask(i, "task"));
            System.out.println("执行异步任务 - " + Thread.currentThread().getName());
        }
        job.shutdown();
    }

}
