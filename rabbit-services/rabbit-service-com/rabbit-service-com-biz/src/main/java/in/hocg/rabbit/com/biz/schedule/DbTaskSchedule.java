package in.hocg.rabbit.com.biz.schedule;

import com.alibaba.schedulerx.worker.domain.JobContext;
import com.alibaba.schedulerx.worker.processor.JavaProcessor;
import com.alibaba.schedulerx.worker.processor.ProcessResult;
import in.hocg.boot.mybatis.plus.extensions.task.TaskMpeService;
import in.hocg.rabbit.com.api.enums.TaskType;
import in.hocg.rabbit.mina.api.YouTubeServiceApi;
import in.hocg.rabbit.mina.api.pojo.ro.UploadYouTubeRo;
import in.hocg.boot.utils.enums.ICode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DbTaskSchedule extends JavaProcessor {
    private final TaskMpeService taskMpeService;
    private final YouTubeServiceApi youTubeServiceApi;

    @Override
    public ProcessResult process(JobContext context) throws Exception {
        taskMpeService.listByReady()
            .forEach(taskItem -> taskMpeService.runAsync(taskItem.getId(), this.getTaskFunction(taskItem.getType())));
        return new ProcessResult(true);
    }

    private Function<Object, Object> getTaskFunction(String typeCode) {
        Optional<TaskType> taskTypeOpt = ICode.of(typeCode, TaskType.class);
        if (taskTypeOpt.isEmpty()) {
            return s -> null;
        }

        TaskType taskType = taskTypeOpt.get();
        if (taskType.equals(TaskType.YouTubeUpload)) {
            return (s) -> youTubeServiceApi.upload((UploadYouTubeRo) s);
        }
        return s -> null;
    }
}
