package com.github.lotus.chaos.basic;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/11/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel("响应结构体")
public class Result<T> implements Serializable {

    public static <T> Result<T> result() {
        return new Result<>();
    }

    public static <T> Result<T> success() {
        return Result.result();
    }
}
