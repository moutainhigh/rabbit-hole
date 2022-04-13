package in.hocg.rabbit.y2b;

import cn.hutool.core.convert.Convert;
import com.google.common.collect.Maps;
import in.hocg.rabbit.chaos.BootApplication;
import in.hocg.rabbit.common.utils.CommonUtils;
import in.hocg.rabbit.mina.biz.pojo.dto.UploadY2bDto;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2022/4/3
 * email: hocgin@gmail.com
 * - BCP-47
 *
 * @author hocgin
 */
@Slf4j
@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HandleTests extends AbsY2bUpload {

    @Test
    @ApiOperation("合集(我独自升级|2.5min|周一)上传")
    public void upload11() {
        // https://www.gaoding.com/design?mode=user&id=19599685918795813
        String title = "《我独自升级》{ep}10年前，与次元连接的“门”被打开，魔物不断出现，能力各异的猎魔者随之出现，被称为“猎人”! #猎人 #异界";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NW4LTVH/";
        List<String> addTags = List.of("猎人", "新世界", "异界");
        String thumbFile = "http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg";

        Pair<Integer, Integer> pair = buildPage(2, 15, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }

    @Test
    @ApiOperation("合集(末世盗贼行|2min|周一)上传")
    public void upload112() {
        // https://www.gaoding.com/design?mode=user&id=19614112742522889
        String title = "《末世盗贼行》{ep}一场宇宙风暴带来了物种的变异。城市中的毒尸巨怪，挥动着战斧咆哮! #末世 #异能";
        Long channelId = 1L;
        String url = "https://v.douyin.com/N7JeWoc/";
        List<String> addTags = List.of("末世", "新世界", "异能");
        String thumbFile = "http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg";

        Pair<Integer, Integer> pair = buildPage(2, 20, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(),
            "PLCEcFGOrM-f98gSNypaNgtiQJfw0h0f13", Maps.newHashMap(),
            AbsY2bUpload.DEFAULT_SKIP_TIMESTAMP, false);
    }

    @Test
    @ApiOperation("合集(全球诡异时代|2.5min|周二)上传")
    public void upload12() {
        // https://www.gaoding.com/design?id=19564240767035404&simple=1&mode=user
        String title = "《全球诡异时代》{ep}穿越者，在这个诡异的世界，正在追求着超凡的力量! #穿越 #异界";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NqFKLcM/";
        List<String> addTags = List.of("穿越", "异界");
        String thumbFile = "http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg";

        Map<String, UploadY2bDto.LocalTitle> locals = Maps.newHashMap();
        locals.put("zh-Hant", new UploadY2bDto.LocalTitle()
            .setTitle("《全球詭異時代》{ep}穿越者，在這個詭異的世界，正在追求著超凡的力量! #穿越 #異界"));
        locals.put("zh-Hans", new UploadY2bDto.LocalTitle()
            .setTitle(title));

        Pair<Integer, Integer> pair = buildPage(1, 13, 75);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(),
            "PLCEcFGOrM-f9R2BSfh6_QN9s93KXS1DaC", locals, DEFAULT_SKIP_TIMESTAMP, true);
    }

    @Test
    @ApiOperation("合集(修仙大反派|1.5min|周二)上传")
    public void upload121() {
        // https://www.gaoding.com/design?mode=user&id=19620551659896897
        String title = "《修仙大反派》{ep}高端玩家悲惨猝死，穿越成游戏里受尽唾骂惨死在万人刀下的反派大少爷，幽暗的棺材里，美女系统开启新人礼包，赠送万点灵力! #玄幻 #轻松";
        Long channelId = 1L;
        String url = "https://v.douyin.com/N7mow5e/";
        List<String> addTags = List.of("玄幻", "轻松", "冒险");
        String thumbFile = "http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg";

        Map<String, UploadY2bDto.LocalTitle> locals = Maps.newHashMap();
        locals.put("zh-Hant", new UploadY2bDto.LocalTitle()
            .setTitle("《修仙大反派》{ep}高端玩家悲慘猝死，穿越成遊戲裡受盡唾罵慘死在萬人刀下的反派大少爺，幽暗的棺材裡，美女系統開啟新人禮包，贈送萬點靈力! #玄幻 #輕鬆"));
        locals.put("zh-Hans", new UploadY2bDto.LocalTitle()
            .setTitle(title));

        Pair<Integer, Integer> pair = buildPage(3, 20, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(),
            "PLCEcFGOrM-f_V0GKDBKkAYyQiDqTX4Euc", locals, DEFAULT_SKIP_TIMESTAMP, true);
    }

    @Test
    @ApiOperation("合集(观棋烂柯|3min|周三)上传")
    public void upload13() {
        // https://www.gaoding.com/design?id=19564228136026199&simple=1&mode=user
        String title = "《观棋烂柯》{ep}烂柯旁棋局落叶，老树间对弈无人。传说中的故事居然是真的! #古风 #玄幻";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NpqgRFx";
        List<String> addTags = List.of("古风", "玄幻");
        String thumbFile = "http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg";

        Map<String, UploadY2bDto.LocalTitle> locals = Maps.newHashMap();
        locals.put("zh-Hant", new UploadY2bDto.LocalTitle()
            .setTitle("《觀棋爛柯》{ep}爛柯旁棋局落葉，老樹間對弈無人。傳說中的故事居然是真的! #古風 #玄幻"));
        locals.put("zh-Hans", new UploadY2bDto.LocalTitle()
            .setTitle(title));
        Pair<Integer, Integer> pair = buildPage(1, 10, 80);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(),
            "PLCEcFGOrM-f_5JO5R07DduMq-a4HHvMGp", locals, DEFAULT_SKIP_TIMESTAMP, true);
    }

    @Test
    @ApiOperation("合集(我能看到成功率|3.5min|周三)上传")
    public void upload132() {
        // https://www.gaoding.com/design?id=19564228136026199&simple=1&mode=user
        String title = "《我能看到成功率》{ep}现实世界生活失意的白武突然获得了看到事件成功率的异能？从此摇身一变百万富翁？美女对他一见钟情？利用成功率走向人生巅峰！ #异能 #都市";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NvHfpQ6/";
        List<String> addTags = List.of("异能", "都市");
        String thumbFile = "http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg";

        Pair<Integer, Integer> pair = buildPage(1, 10, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(),
            null);
    }

    @Test
    @ApiOperation("合集(我的成就有点多|2min|周四)上传")
    public void upload14() {
        String title = "《我的成就有点多》{ep}为了奖励孟凡重生前义举，获得一个系统奖励，并将他送到三个月前! #都市 #异能 #系统";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NsDEdpX/";
        List<String> addTags = List.of("都市", "异能", "系统");
        String thumbFile = "http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg";

        Pair<Integer, Integer> pair = buildPage(1, 15, 50);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }

    @Test
    @ApiOperation("合集(我有999种异能|2.5min|周五)上传")
    public void upload15() {
        // https://www.gaoding.com/design?mode=user&id=19575703892148247
        String title = "《我有999种异能》{ep}在这个全民异能的世界，为了拯救心爱的妹妹，杨希发誓要让伤害她的人血债血偿! #都市 #异能 #系统";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NGkcuvb/";
        List<String> addTags = List.of("都市", "异能", "系统");
        String thumbFile = "http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg";

        Pair<Integer, Integer> pair = buildPage(2, 15, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }

    @Test
    @ApiOperation("合集(末日重启|1.5min|周五)上传")
    public void upload16() {
        // https://www.gaoding.com/design?mode=user&id=19581889897185313
        String title = "《末日重启》{ep}末日爆发后奋战了三年，因为队友连累被丧尸围攻致死。再次醒来，却重生末日开始之前! #末日 #重生";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NtenCEv";
        List<String> addTags = List.of("末日", "重生");
        String thumbFile = "http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg";

        Pair<Integer, Integer> pair = buildPage(1, 15, 50);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }

    @Test
    @ApiOperation("合集(深渊副本已刷新|1.5min|周六.上午)上传")
    public void upload17() {
        // https://www.gaoding.com/design?id=19587528967596099&simple=1&mode=user
        String title = "《深渊副本已刷新》{ep}十年前神秘建筑出现在城市上空，被选中的玩家能够进入天空城中，不断地积累声望和财富! #副本 #都市";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NtPhKsK";
        List<String> addTags = List.of("游戏", "副本", "升级");
        String thumbFile = "http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg";

        Pair<Integer, Integer> pair = buildPage(2, 20, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }


    @Test
    @ApiOperation("合集(九个女徒弟称霸后宫|2min|周六.下午)上传")
    public void upload18() {
        // https://www.gaoding.com/design?mode=user&id=19588056655865891
        String title = "《九个女徒弟称霸后宫》{ep}敢在老祖头上动土？打脸打不死你！女人？我有九个！！ #后宫 #重生";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NtmohKM/";
        List<String> addTags = List.of("热血", "后宫", "重生");
        String thumbFile = "http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg";

        Pair<Integer, Integer> pair = buildPage(2, 20, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(),
            "PLCEcFGOrM-f8iRJ_DRQlwhKjKDF6h9uJE");
    }


    @Test
    @ApiOperation("合集(我什么时候无敌了|2min|周日.上午)上传")
    public void upload19() {
        // https://www.gaoding.com/design?mode=user&id=19588056655865891
        String title = "《我什么时候无敌了》{ep}他一直以为自己是凡人，却不知院子里堆满了神器，养的鸡更是凤凰！ #玄幻 #轻松";
        Long channelId = 1L;
        String url = "https://v.douyin.com/Nna4Udw/";
        List<String> addTags = List.of("玄幻", "轻松", "无敌");
        String thumbFile = "http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg";

        Pair<Integer, Integer> pair = buildPage(2, 20, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(),
            "PLCEcFGOrM-f-Yr2l2mblBYInjgL1dv98X");
    }


    @Test
    @ApiOperation("合集(这一世我要当至尊|2.3min|周日.下午)上传")
    public void upload111() {
        // https://www.gaoding.com/design?mode=user&id=19599685918795813
        String title = "《这一世我要当至尊》{ep}十大封号武帝之一，绝世武帝古飞扬在天荡山脉陨落，于十五年后转世重生！ #玄幻 #武道";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NnXAnJK/";
        List<String> addTags = List.of("玄幻", "重生", "修仙");
        String thumbFile = "http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg";

        Pair<Integer, Integer> pair = buildPage(1, 20, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), "PLCEcFGOrM-f_IcDgfaOCQPtE3AAk8rCnR", Maps.newHashMap(),
            AbsY2bUpload.DEFAULT_SKIP_TIMESTAMP, true);
    }

    @Test
    @Deprecated
    @ApiOperation("手动合集(抖音短视频拼接)上传")
    public void upload2() {
        String collectionName = "猫猫的日常(20220501)";
        String title = "猫猫的日常";
        String desc = title;
        Long channelId = 1L;
        List<String> urls = List.of(
            "https://v.douyin.com/NVENCyc",
            "https://v.douyin.com/NVENCyc"
        );
        List<String> addTags = List.of("猫咪");
        String thumbFile = "http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg";
        uploadDetail(collectionName, channelId, urls, title, desc, addTags, thumbFile);
    }

    @Test
    @Deprecated
    @ApiOperation("单个视频(本地已有文件)上传")
    public void uploadFile() {
        String title = "《全球诡异时代》{ep}穿越者，在这个诡异的世界，正在追求着超凡的力量! #穿越 #异界";
        String desc = title;
        Long channelId = 1L;
        List<String> addTags = List.of("穿越", "异界");
        String thumbFile = "http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg";
        File videoFile = new File("/Users/Share/k8s_nfs/basic_video/全球诡异时代(0~74)");

        UploadY2bDto options = new UploadY2bDto();
        options.setTitle(title);
        options.setThumbnailUrl(thumbFile);
        options.setTags(addTags);
        options.setDescription(desc);
        upload(channelId, videoFile, options);
    }
}
