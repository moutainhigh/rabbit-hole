package in.hocg.rabbit.spider.agefans;

import com.google.common.collect.Lists;
import in.hocg.boot.webmagic.autoconfiguration.processor.AbsPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * Created by hocgin on 2021/5/22
 * email: hocgin@gmail.com
 * https://play.agefans.cc:8443/_getplay2?kp=bN2ehBPYv6OV8rm1XimQbPxZx11xUjAm4V/9Vau9ad1Xrxmvdw5zah1T0YQIXlhLaz7MhOsCRTchQolsrwC7gvLpJvyKXYaSEHp2eMvU+jT4QbwHEPTo5ArriJ6bzw48ecZuY3r0Vedt6tFu8fF0tfbEstaboouru/qziFM8XdV7ZOE5IVOD+pdTHsuaknNkBfaHgdxx4V2VQCZUXH/Rwoar78vQ6Brq
 *
 * @author hocgin
 */
public class AgefansPageProcessor extends AbsPageProcessor {
    public static final String RESULT = "result";

    @Override
    public void process(Page page) {
        Selectable url = page.getUrl();
        // 动漫视频播放页
        if (url.regex("https://www.agefans.cc/play/\\d+\\?playid=(.|_)*?").match()) {
            Html html = page.getHtml();
            String src = html.xpath("//*[@id=\"player1\"]/div[2]/video/@src").get();
            page.putField(RESULT, src);
        }
        // 动漫主页
        else if (url.regex("https://www.agefans.cc/play/\\d+").match()) {
            Html html = page.getHtml();
            List<String> urls = html.xpath("//*[@id=\"main0\"]/div[3]/ul/li/a/@abs:href").all();
            page.addTargetRequests(urls);
        }
    }


    public static void main(String[] args) {
        List<String> result = Lists.newArrayList();
        Spider.create(new AgefansPageProcessor())
            .addUrl("https://www.agefans.cc/play/20200294?playid=3_1")
            .addPipeline((resultItems, task) -> {
                String rankRows = resultItems.get(RESULT);
                result.add(rankRows);
            }).run();
        result.forEach(System.out::println);
    }
}
