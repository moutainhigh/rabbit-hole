package in.hocg.rabbit.mina.gathering;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import in.hocg.rabbit.chaos.BootApplication;
import in.hocg.rabbit.common.utils.CommonUtils;
import in.hocg.rabbit.mina.biz.entity.MobileWallpaper;
import in.hocg.rabbit.mina.biz.service.MobileWallpaperService;
import in.hocg.rabbit.mina.gathering.yuexiakeji.Post;
import in.hocg.boot.oss.autoconfigure.core.OssFileBervice;
import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import in.hocg.boot.utils.LangUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by hocgin on 2021/3/13
 * email: hocgin@gmail.com
 * 夜下图书馆
 *
 * @author hocgin
 */
@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class YuexiakejiGathering extends AbstractSpringBootTest {
    @Autowired
    MobileWallpaperService mobileWallpaperService;
    @Autowired
    OssFileBervice ossFileService;

    @Test
    public void gathering() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 1; i < 10000; i++) {
            String url = "https://www.yuexiakeji.com/wp-json/mp/v2/posts?per_page=100&page=" + i;
            String body = HttpUtil.get(url);
            List<Post> posts = JSON.parseArray(body, Post.class);
            if (CollectionUtils.isEmpty(posts)) {
                break;
            }

            for (Post post : posts) {
                String title = LangUtils.callIfNotNull(post.getTitle(), Post.TitleDTO::getRendered).orElse("未命名");
                List<Post.TagsDTO> tagsList = LangUtils.getOrDefault(post.getTags(), Collections.emptyList());
                String tags = tagsList.parallelStream()
                    .filter(Objects::nonNull)
                    .map(Post.TagsDTO::getName)
                    .filter(Objects::nonNull)
                    .reduce((s, s2) -> new StringJoiner(";").add(s).add(s2).toString()).orElse(null);

                for (Post.WallpaperDTO wallpaper : post.getWallpaper()) {
                    String imageUrl = wallpaper.getFull();
                    String fileName = CommonUtils.getFileName(imageUrl);
                    String suffix = FileUtil.getSuffix(fileName);
                    File imageFile;
                    try {
                        imageFile = CommonUtils.toFile(imageUrl);
                    } catch (Exception e) {
                        continue;
                    }
                    String hash = SecureUtil.md5(imageFile);
                    Boolean hasFile = mobileWallpaperService.hasByFileHash(hash);
                    if (!hasFile) {
                        String filename = (hash + "." + suffix);
                        String dataSource = "yuexiakeji.com";
                        String newImageUrl = ossFileService.upload(imageFile, GetFilename.getFilename(dataSource, filename));
                        MobileWallpaper entity = new MobileWallpaper();
                        entity.setFullUrl(newImageUrl);
                        entity.setTitle(title);
                        entity.setColor(null);
                        entity.setTags(tags);
                        entity.setDataSource(dataSource);
                        entity.setFileHash(hash);
                        entity.setCreatedAt(now);
                        mobileWallpaperService.validInsert(entity);
                    }
                }
            }

            Thread.sleep(1000);
        }
    }

}
