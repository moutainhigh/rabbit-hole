package com.github.lotus.mina.biz.support.frp.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("reject_reason")
    private String rejectReason;
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
        return reject(null);
    }

    /**
     * 拒绝
     *
     * @return
     */
    public static FrpResult<?> reject(String rejectReason) {
        return result(true, true, null).setRejectReason(rejectReason);
    }

    /**
     * 不调整
     *
     * @return
     */
    public static FrpResult<?> pass() {
        return result(false, true, null);
    }


    /**
     * 不调整
     *
     * @return
     */
    public static <T> FrpResult<T> pass(T content) {
        return result(false, true, content);
    }

    public static <T> FrpResult<T> result(Boolean reject, Boolean unchange, T content) {
        return new FrpResult<T>().setContent(content).setReject(reject).setUnchange(unchange);
    }
}
