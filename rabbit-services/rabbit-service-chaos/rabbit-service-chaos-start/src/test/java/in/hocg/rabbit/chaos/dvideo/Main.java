package in.hocg.rabbit.chaos.dvideo;

/**
 * Created by hocgin on 2020/12/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String newUrl = Video.decode("https://v.douyin.com/NVENCyc", Video.Type.DuoYin);
        System.out.println(newUrl);
    }
}
