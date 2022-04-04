package in.hocg.rabbit.cv.biz.manager.impl;

import in.hocg.boot.javacv.autoconfiguration.support.FeatureHelper;
import in.hocg.rabbit.cv.biz.manager.CvService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/4/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class CvServiceImpl implements CvService {

    @Override
    public String mergeVideo(List<String> files, String outputFile) {
        FeatureHelper.mergeVideo(files.stream().map(File::new).collect(Collectors.toList()), new File(outputFile));
        return outputFile;
    }
}
