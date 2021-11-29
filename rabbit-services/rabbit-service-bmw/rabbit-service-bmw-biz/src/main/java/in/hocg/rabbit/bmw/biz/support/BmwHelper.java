package in.hocg.rabbit.bmw.biz.support;

import cn.hutool.extra.qrcode.QrCodeUtil;
import in.hocg.rabbit.bmw.biz.cache.BmwCacheService;
import in.hocg.boot.oss.autoconfigure.core.OssFileBervice;
import in.hocg.boot.web.autoconfiguration.SpringContext;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;

/**
 * Created by hocgin on 2021/8/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class BmwHelper {

    @SneakyThrows
    public String uploadQrcode(String qrcode) {
        OssFileBervice fileBervice = SpringContext.getBean(OssFileBervice.class);
        BufferedImage qrCodeFile = QrCodeUtil.generate(qrcode, 400, 400);
        File file = Files.createTempFile("img", ".png").toFile();
        ImageIO.write(qrCodeFile, "png", file);
        return fileBervice.upload(file);
    }

    public String setFormPage(String content) {
        BmwCacheService cacheService = SpringContext.getBean(BmwCacheService.class);
        return cacheService.setFormPage(content);
    }
}
