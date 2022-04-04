package in.hocg.rabbit.cv.biz.apiimpl;

import in.hocg.rabbit.cv.api.CvServiceApi;
import in.hocg.rabbit.cv.api.pojo.ro.MergeVideoRo;
import in.hocg.rabbit.cv.biz.manager.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2022/4/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class CvServiceApiImpl implements CvServiceApi {
    private final CvService cvService;

    @Override
    public String mergeVideo(@RequestBody MergeVideoRo ro) {
        return cvService.mergeVideo(ro.getFiles(), ro.getOutput());
    }
}
