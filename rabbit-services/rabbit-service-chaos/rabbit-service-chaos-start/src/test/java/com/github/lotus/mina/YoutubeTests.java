package com.github.lotus.mina;

import cn.hutool.crypto.SecureUtil;
import com.github.lotus.chaos.BootApplication;
import com.github.lotus.common.utils.CommonUtils;
import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.nio.file.Path;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class YoutubeTests extends AbstractSpringBootTest {

    @Test
    public void testFile() {
        String str = "http://cdn.hocgin.top/file/sample_video.mp4";
        Path path = YoutubeTests.downloadFile(str);
        log.info("=>  {}", path);
    }

    @SneakyThrows
    public static Path downloadFile(String formUrl) {
        Path toPath = CommonUtils.toFile2(formUrl);
        log.info("from file md5=[{}]", SecureUtil.md5(toPath.toFile()));

        CommonUtils.modifyMD5(toPath);

        log.info("to file md5=[{}]", SecureUtil.md5(toPath.toFile()));
        return toPath;
    }
}
