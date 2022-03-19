package in.hocg.rabbit.cv.biz.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

/**
 * Created by hocgin on 2022/1/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Controller
@RequestMapping("/stream")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class StreamMessageController {

    @SneakyThrows
    @RequestMapping("/{stream}")
    public void syncCmd(@PathVariable String stream, String message) {
        byte[] data = Base64.decode(message);

        int frameWidth = 480;
        int frameHeight = 270;

        FFmpegFrameRecorder recorder = FFmpegFrameRecorder.createDefault("rtmp://160107.livepush.myqcloud.com/live/sss?txSecret=c57c77436f7379d112680b6423a4e063&txTime=61DC304A", frameWidth, frameHeight);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setFormat("flv");

        BufferedImage image = null; //ImageUtils.bytesToImage(data);
        Java2DFrameConverter converter = new Java2DFrameConverter();
        recorder.start();
        recorder.record(converter.getFrame(image));
        recorder.stop();
        recorder.release();
    }


}
