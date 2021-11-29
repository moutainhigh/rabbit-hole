package in.hocg.rabbit.mina.biz.pojo.dto;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class UploadYouTubeVideoDto {
    @ApiModelProperty("图片")
    private String thumbUrl = "http://cdn.hocgin.top/file/未命名图片.png";
    @ApiModelProperty("频道")
    private String channelId = "UCEAVb3QTuUD6kpvvDq6N_NQ";
    @ApiModelProperty("国际化")
    private String language = "ZH-CN";
    // 视频类别: https://developers-dot-devsite-v2-prod.appspot.com/youtube/v3/docs/videoCategories/list
    @ApiModelProperty("类别")
    private String categoryId = "1";
    @ApiModelProperty("播放列表")
    private String playlistId = "PLCEcFGOrM-f83PBJrLUbqtRz7m3WgDkOy";
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("描述")
    private String description = "国漫巨制沙雕修仙番｜《一念永恒》动画改编自网络文学作家耳根同名小说，由企鹅影视、视美影业联合出品，平凡少年白小纯为追求长生之法，一次次点燃仙香召唤仙人却屡遭雷劈。直到引路人李青候掌座的出现…，承包你这个夏天的笑点！";
    @ApiModelProperty("视频标签")
    private List<String> tags = Lists.newArrayList("中国", "动漫", "豆瓣高分", "玄幻", "China", "Animation");
    @ApiModelProperty("视频公开状态")
    private String privacyStatus = "public";
    @ApiModelProperty("是否修改md5")
    private Boolean isModifyMd5 = false;
}
