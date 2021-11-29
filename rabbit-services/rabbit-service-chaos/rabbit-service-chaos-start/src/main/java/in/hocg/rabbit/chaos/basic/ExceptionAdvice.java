package in.hocg.rabbit.chaos.basic;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import in.hocg.boot.web.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by hocgin on 2021/7/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BlockException.class)
    public Result<Void> handleBlockException(BlockException e) {
        return Result.fail("访问过于频繁, 您已被限流");
    }
}
