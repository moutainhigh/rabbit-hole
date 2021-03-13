package com.github.lotus.mina.gathering;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.AbstractSpringBootTest;
import com.github.lotus.common.utils.CommonUtils;
import com.github.lotus.mina.biz.entity.MobileWallpaper;
import com.github.lotus.mina.biz.service.MobileWallpaperService;
import com.github.lotus.mina.gathering.youxie.Post;
import com.google.common.collect.Maps;
import in.hocg.boot.oss.autoconfigure.core.OssFileService;
import in.hocg.boot.utils.LangUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by hocgin on 2021/3/13
 * email: hocgin@gmail.com
 * 小圈壁纸
 *
 * @author hocgin
 */
public class YouXieGathering extends AbstractSpringBootTest {
    @Autowired
    MobileWallpaperService mobileWallpaperService;
    @Autowired
    OssFileService ossFileService;

    @Test
    public void youxie() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        Map<Integer, String> categoriesMap = getYouxieCategories();

        for (int i = 1; i < 100000; i++) {
            String url = "https://diy.youxie.ren/wp-json/wp/v2/posts?per_page=100&page=" + i;
            String body = HttpUtil.get(url);
            List<Post> posts = JSON.parseArray(body, Post.class);
            if (CollectionUtils.isEmpty(posts)) {
                break;
            }

            for (Post item : posts) {
                String title = LangUtils.callIfNotNull(item.getTitle(), Post.TitleDTO::getRendered).orElse("未命名");
                List<Integer> categories = LangUtils.getOrDefault(item.getCategories(), Collections.emptyList());
                String tags = categories.parallelStream()
                    .filter(Objects::nonNull)
                    .map(categoriesMap::get)
                    .filter(Objects::nonNull)
                    .reduce((s, s2) -> new StringJoiner(";").add(s).add(s2).toString()).orElse(null);

                for (Post.WallpaperDTO wallpaper : item.getWallpaper()) {
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
                        String dataSource = "youxie.ren";
                        String filename = (hash + "." + suffix);
                        String newImageUrl = ossFileService.upload(imageFile, GetFilename.getFilename(dataSource, filename));
                        MobileWallpaper entity = new MobileWallpaper();
                        entity.setFullUrl(newImageUrl);
                        entity.setTitle(title);
                        entity.setColor(null);
                        entity.setDataSource(dataSource);
                        entity.setTags(tags);
                        entity.setFileHash(hash);
                        entity.setCreatedAt(now);
                        mobileWallpaperService.validInsert(entity);
                    }
                }
            }

            Thread.sleep(1000);
        }

    }

    public Map<Integer, String> getYouxieCategories() {
        String url = "https://diy.youxie.ren/wp-json/wp/v2/categories?orderby=id&order=asc&per_page=100";
        String body = HttpUtil.get(url);
        JSONArray objects = JSON.parseArray(body);
        Map<Integer, String> result = Maps.newHashMap();

        for (Object item : objects) {
            JSONObject object = (JSONObject) item;
            Integer id = object.getInteger("id");
            String name = object.getString("name");
            result.put(id, name);
        }
        return result;
    }

}
