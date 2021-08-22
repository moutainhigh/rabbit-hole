package com.github.lotus.com;

import com.github.lotus.chaos.BootApplication;
import in.hocg.boot.oss.autoconfigure.core.OssFileBervice;
import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2021/3/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadGbaMainTests extends AbstractSpringBootTest {
    @Autowired
    protected OssFileBervice ossFileService;

    @Test
    public void xxxx() throws IOException {
        Path sqlPath = Paths.get("/Users/hocgin/Downloads", "game_gba.sql");

        List<String> lines = new ArrayList<>();
        List<Path> files = Files.list(Paths.get("/Users/hocgin/Downloads/GBA中文游戏")).collect(Collectors.toList());
        for (Path file : files) {
            Path title = file.getFileName();
            File romFile = file.toFile();
            String newRomUrl = ossFileService.upload(romFile);
            String newImageUrl = "http://cdn.hocgin.top/file/default_game.png";

            lines.add(String.format("INSERT INTO mina_game_card(title,logo_url,game_url,remark,tags,game_type,creator,created_at) " +
                    "VALUES('%s', '%s', '%s', '%s', '%s', '%s', %s, %s);",
                title, newImageUrl, newRomUrl, title, "中文版;GBA", "gba", "1", "NOW()"));


        }

        Files.write(sqlPath, lines);
    }

}
