package in.hocg.rabbit.chaos.dvideo;

import in.hocg.rabbit.mina.biz.support.down.Video;

/**
 * Created by hocgin on 2020/12/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String newUrl = Video.getVideoDecoder(Video.Type.DuoYin).aweme("https://v.douyin.com/NVENCyc").getUrl();
        System.out.println(newUrl);
    }
}
