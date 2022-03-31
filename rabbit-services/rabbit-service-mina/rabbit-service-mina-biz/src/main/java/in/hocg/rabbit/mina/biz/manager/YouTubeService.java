package in.hocg.rabbit.mina.biz.manager;

import in.hocg.rabbit.mina.biz.pojo.ro.BatchUploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeClientCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.pojo.vo.YouTubeClientVo;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface YouTubeService {

    /**
     * 授权应用
     *
     * @param clientId
     * @param scopes
     * @return
     */
    String authorize(String clientId, List<String> scopes);

    /**
     * 授权回调
     *
     * @param clientId
     * @param scopes
     * @param code
     * @return
     */
    void authorizeCallback(String clientId, List<String> scopes, String code);

    @Async
    void uploadVideo(UploadYouTubeVideoRo ro);

    @Async
    void uploadDir(BatchUploadYouTubeVideoRo ro);

    List<YouTubeClientVo> clientComplete(YouTubeClientCompleteRo ro);
}
