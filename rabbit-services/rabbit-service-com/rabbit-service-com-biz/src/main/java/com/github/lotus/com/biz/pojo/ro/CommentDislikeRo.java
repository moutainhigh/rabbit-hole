package com.github.lotus.com.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/1/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class CommentDislikeRo {
    private Long id;
    private Long userId;
}
