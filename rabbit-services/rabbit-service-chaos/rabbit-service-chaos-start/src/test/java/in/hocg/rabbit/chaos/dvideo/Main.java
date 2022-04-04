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
        String newUrl = Video.item("https://v.douyin.com/NVENCyc", Video.Type.DuoYin).getUrl();
        System.out.println(newUrl);
    }
}
