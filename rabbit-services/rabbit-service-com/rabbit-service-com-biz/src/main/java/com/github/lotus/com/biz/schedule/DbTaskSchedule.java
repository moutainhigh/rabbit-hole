package com.github.lotus.com.biz.schedule;

import com.alibaba.schedulerx.worker.domain.JobContext;
import com.alibaba.schedulerx.worker.processor.JavaProcessor;
import com.alibaba.schedulerx.worker.processor.ProcessResult;
import com.github.lotus.common.datadict.com.TaskType;
import com.github.lotus.mina.api.YouTubeServiceApi;
import com.github.lotus.mina.api.pojo.ro.UploadYouTubeRo;
import in.hocg.boot.task.autoconfiguration.core.TaskBervice;
import in.hocg.boot.task.autoconfiguration.core.TaskInfo;
import in.hocg.boot.task.autoconfiguration.core.TaskRepository;
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
    private final TaskRepository taskRepository;
    private final TaskBervice taskBervice;
    private final YouTubeServiceApi youTubeServiceApi;

    @Override
    public ProcessResult process(JobContext context) throws Exception {
        for (TaskInfo taskInfo : taskRepository.listByReady()) {
            String taskType = taskInfo.getType();
            String taskSn = taskInfo.getTaskSn();
            taskBervice.runAsync(taskSn, this.getTaskFunction(taskType));
        }
        return new ProcessResult(true);
    }

    private Function<Object, Object> getTaskFunction(String typeCode) {
        Optional<TaskType> taskType = ICode.of(typeCode, TaskType.class);
        if (taskType.isPresent()) {
            switch (taskType.get()) {
                // 视频上传
                case YouTubeUpload: {
                    return (s) -> youTubeServiceApi.upload((UploadYouTubeRo) s);
                }
            }
        }
        return s -> {
            log.info("任务名称: => {}", taskType.orElse(null));
            return null;
        };
    }
}
