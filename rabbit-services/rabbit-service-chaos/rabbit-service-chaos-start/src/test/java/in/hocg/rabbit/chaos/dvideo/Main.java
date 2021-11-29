package in.hocg.rabbit.chaos.dvideo;

/**
 * Created by hocgin on 2020/12/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String newUrl = Video.decode("https://v.douyin.com/JjHGuMc/", Video.Type.DuoYin);
        System.out.println(newUrl);
        newUrl = Video.decode("http://v.kuaishou.com/s/mhn5haAq", Video.Type.KuaiShou);
        System.out.println(newUrl);
    }
}
