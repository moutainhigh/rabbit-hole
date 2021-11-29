package in.hocg.rabbit.mina.biz.mapstruct;

import in.hocg.rabbit.mina.api.pojo.ro.UploadYouTubeRo;
import in.hocg.rabbit.mina.biz.pojo.dto.UploadYouTubeVideoDto;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface YouTubeMapping {
    UploadYouTubeVideoDto asUploadYouTubeVideoRo(UploadYouTubeRo ro);
}
