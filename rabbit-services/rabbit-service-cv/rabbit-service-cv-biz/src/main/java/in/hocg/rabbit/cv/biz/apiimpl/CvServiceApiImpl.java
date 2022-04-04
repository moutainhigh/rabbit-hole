package in.hocg.rabbit.cv.biz.apiimpl;

import in.hocg.rabbit.cv.api.CvServiceApi;
import in.hocg.rabbit.cv.biz.manager.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
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
    public String mergeVideo(List<String> files, String outputFile) {
        return cvService.mergeVideo(files, outputFile);
    }
}
