import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Colin
 * @Date 2024/9/19 10:55
 * @PackageName:PACKAGE_NAME
 * @ClassName: callableParametersTask
 * @Description: 输入参数和输出参数的使用，callable返回是单个返回值
 * @Description: 适用于处理单个任务，并返回单个结果
 * @Version 1.0
 */
public class callableParametersTask {
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
        for (int i = 0; i < 100; i++) {
            Future<Object> result = job.submit(new ParameterizedCallableTask(i, "任务" + i));
            try {
                Object res = result.get();
                System.out.println(res);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("执行异步任务 - " + Thread.currentThread().getName());
        }
        job.shutdown();
    }


}
