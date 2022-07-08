package in.hocg.rabbit.com.biz.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by hocgin on 2022/7/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Setter
@Getter
public class LinkDetailDto {
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("title")
    private String title;
    @JsonProperty("belong")
    private Object belong;
    @JsonProperty("belong_url")
    private Object belongUrl;
    @JsonProperty("desc")
    private Object desc;
    @JsonProperty("url")
    private String url;
    @JsonProperty("_serializer")
    private String serializer;
}
