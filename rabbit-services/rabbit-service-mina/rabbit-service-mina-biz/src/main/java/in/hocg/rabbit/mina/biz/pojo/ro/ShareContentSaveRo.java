package in.hocg.rabbit.mina.biz.pojo.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2022/3/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ShareContentSaveRo {
    private String title;
    @NotBlank(message = "分享内容不能为空")
    private String content;
}
