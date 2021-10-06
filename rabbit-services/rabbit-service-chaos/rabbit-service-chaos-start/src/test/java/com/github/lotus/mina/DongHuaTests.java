package com.github.lotus.mina;

import cn.hutool.core.io.FileUtil;
import com.github.lotus.common.utils.CommonUtils;
import in.hocg.boot.ffmpeg.autoconfiguration.core.Commands;
import in.hocg.boot.utils.LangUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Created by hocgin on 2021/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class DongHuaTests {

    /**
     * 爬动漫
     * https://www.agefans.cc/play/20200294?playid=3_2
     */
    public void get() {

    }

    /**
     * 修改动漫
     */
    public void handle() {
        File videoDir = Paths.get("/Users/hocgin/Downloads/动漫视频/一念永恒(第一季)").toFile();
        Path targetDir = Paths.get("/Users/hocgin/Downloads/动漫视频/一念永恒(第一季)_convert");
        FileUtil.mkdir(targetDir.toFile());
        for (File file : Objects.requireNonNull(videoDir.listFiles())) {
            File outFile = Commands.sub(file, LocalTime.of(0, 2, 15), LocalTime.of(0, 15, 0));
            CommonUtils.modifyMD5(outFile.toPath());
            FileUtil.move(outFile, targetDir.resolve(outFile.getName()).toFile(), true);
        }
    }
}
