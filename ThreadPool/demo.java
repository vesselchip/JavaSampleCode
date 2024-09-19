import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

public class demo {
    private static final ConcurrentHashMap<Integer, String> taskResults = new ConcurrentHashMap<>();
    static class ParameterizedCallableScheduledTask implements Callable<Object> {
        private Integer taskId;
        private String taskName;
        public ParameterizedCallableScheduledTask(int taskId, String taskName) {
            this.taskId = taskId;
            this.taskName = taskName;
        }
        @Override
        public Object call() throws Exception {
            taskResults.put(taskId, taskName);
            return "任务名称: " + taskName + taskId + " 已完成";
        }


    }

    public static void main(String[] args) {
        ThreadPoolExecutor job = ThreadPoolConfig.getExecutor();
        for (int i = 0; i < 10; i++) {
            Callable<Object> task = new ParameterizedCallableScheduledTask(i, "任务" + i);
            job.submit(task);
        }
        System.out.println(taskResults);

    }

}