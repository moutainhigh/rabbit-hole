package in.hocg.rabbit.com.biz.utils;

import cn.hutool.core.io.FileUtil;
import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.GitHubAvatar;
import com.talanlabs.avatargenerator.utils.AvatarUtils;
import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by hocgin on 2020/10/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class Avatars {
    public final Avatar AVATAR = GitHubAvatar.newAvatarBuilder()
        .build();

    /**
     * 获取头像
     *
     * @param code
     * @return
     */
    public BufferedImage getAvatar(long code) {
        return createAvatar(Avatars.AVATAR, code);
    }

    /**
     * 获取头像本地存储路径
     *
     * @param code
     * @return
     */
    public Path getAvatarAsPath(long code) {
        try {
            final Path filePath = Files.createTempFile("img", ".png");
            ImageIO.write(getAvatar(code), "png", filePath.toFile());
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取头像文件流
     *
     * @param code
     * @return
     */
    public InputStream getAvatarAsStream(long code) {
        return FileUtil.getInputStream(getAvatarAsPath(code).toFile());
    }

    /**
     * 创建头像
     *
     * @param avatar
     * @param code
     * @return
     */
    private BufferedImage createAvatar(Avatar avatar, long code) {
        int size = avatar.getWidth();
        BufferedImage dest = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        g2.drawImage(avatar.create(code + 100000), 0, 0, size, size, null);
        g2.dispose();
        return dest;
    }
}
