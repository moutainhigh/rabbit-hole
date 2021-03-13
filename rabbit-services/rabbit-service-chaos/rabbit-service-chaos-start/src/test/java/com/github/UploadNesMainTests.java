package com.github;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import in.hocg.boot.oss.autoconfigure.core.OssFileService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hocgin on 2021/3/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class UploadNesMainTests extends AbstractSpringBootTest {
    @Autowired
    protected OssFileService ossFileService;

    @Test
    public void xxxx() throws IOException {
        Path sqlPath = Paths.get("/Users/hocgin/Downloads", "game.sql");

        List<String> strings = Files.readAllLines(Paths.get("/Users/hocgin/Downloads", "game.json"));
        String allStr = String.join("", strings);
        JSONObject result = JSON.parseObject(allStr);
        JSONArray data = result.getJSONArray("data");
        List<String> lines = new ArrayList<>();
        for (Object object : data) {
            JSONObject item = (JSONObject) object;
            String imageUrl = item.getString("cover");
            String romUrl = item.getString("rom");
            String title = item.getString("name");

            File image = toFile(imageUrl);
            String newImageUrl = ossFileService.upload(image);
            File rom = toFile(romUrl);
            String newRomUrl = ossFileService.upload(rom);

            lines.add(String.format("INSERT INTO mina_game_card(title,logo_url,game_url,remark,tags,creator,created_at) " +
                    "VALUES('%s', '%s', '%s', '%s', '%s', %s, %s);",
                title, newImageUrl, newRomUrl, title, "NES", "1", "NOW()"));

        }
        Files.write(sqlPath, lines);
    }

    @SneakyThrows
    public static File toFile(String turl) {
        URL url = new URL(turl);
        String[] split = url.getFile().split("/");
        String filename = split[split.length - 1];
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();

        File dfile = new File(filename);
        FileOutputStream fos = new FileOutputStream(dfile);
        byte[] buf = new byte[512];
        while (true) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }
            fos.write(buf, 0, len);
        }
        in.close();
        fos.flush();
        fos.close();

        return dfile;
    }

    public static void main(String[] args) {
        File xxx = toFile("https://img.dkshop.com.cn/static-nes/rom/2029e6af90f987fa2ac7aa5d19edfdb4.nes");
        System.out.println(xxx);
    }

}
