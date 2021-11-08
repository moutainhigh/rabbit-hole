package com.github.lotus.chaos.biz.support.frp.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/11/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "frp 插件响应")
public class FrpResult<T> {
    @ApiModelProperty("拒绝执行操作")
    private Boolean reject;
    @ApiModelProperty("允许且内容不需要变动")
    private Boolean unchange;
    @ApiModelProperty("允许且需要替换操作内容")
    private T content;

    /**
     * 发生调整
     *
     * @param content
     * @param <T>
     * @return
     */
    public static <T> FrpResult<T> change(T content) {
        return result(false, false, content);
    }

    /**
     * 拒绝
     *
     * @return
     */
    public static FrpResult<?> reject() {
        return result(true, true, null);
    }

    /**
     * 不调整
     *
     * @return
     */
    public static FrpResult<?> pass() {
        return result(false, true, null);
    }

    public static <T> FrpResult<T> result(Boolean reject, Boolean unchange, T content) {
        return new FrpResult<T>().setContent(content).setReject(reject).setUnchange(unchange);
    }
}
