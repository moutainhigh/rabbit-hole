package in.hocg.rabbit.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileMode;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import in.hocg.rabbit.common.constant.GlobalConstant;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Created by hocgin on 2021/3/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class CommonUtils {

    public static String getOriginalUrl(String url) {
        if (Objects.nonNull(url) && url.contains("?")) {
            return url.substring(0, url.indexOf("?"));
        }
        return url;
    }

    public static String getFileName(String url) {
        if (Objects.isNull(url)) {
            return null;
        }
        url = getOriginalUrl(url);
        String[] split = url.split("/");
        return split[split.length - 1];
    }

    @SneakyThrows
    public static File toFile(String turl) {
        HttpResponse response = HttpUtil.createGet(turl).execute();
        String filename = getFileName(turl);
        InputStream in = response.bodyStream();
        File dfile = File.createTempFile("tmp", filename);
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

    @SneakyThrows
    public static Path downloadFile(String formUrl) {
        Path toPath = Files.createTempFile("_", "." + getFileName(formUrl));
        return downloadFile(formUrl, toPath).toPath();
    }

    @SneakyThrows
    public static File downloadFile(String formUrl, Path toPath) {
        InputStream inputStream = URI.create(formUrl).toURL().openStream();
        File toFile = toPath.toFile();
        FileUtil.writeFromStream(inputStream, toFile);
        return toFile;
    }

    @SneakyThrows
    public static File updateFileMd5(File file) {
        @Cleanup
        RandomAccessFile result = FileUtil.createRandomAccessFile(file, FileMode.rw);
        result.seek(file.length());
        result.writeBytes(GlobalConstant.AUTHOR);
        result.close();
        return file;
    }
}
