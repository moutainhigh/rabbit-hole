package com.github.lotus.chaos.biz.schedule;

import com.alibaba.schedulerx.worker.domain.JobContext;
import com.alibaba.schedulerx.worker.processor.JavaProcessor;
import com.alibaba.schedulerx.worker.processor.ProcessResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2021/3/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
public class SimpleTaskSchedule extends JavaProcessor {

    @Override
    public ProcessResult process(JobContext context) throws Exception {
        log.info("Hi");
        return new ProcessResult(true);
    }
}
