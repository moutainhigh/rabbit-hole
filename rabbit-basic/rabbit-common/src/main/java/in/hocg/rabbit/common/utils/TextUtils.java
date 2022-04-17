package in.hocg.rabbit.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.dictionary.stopword.Filter;
import com.hankcs.hanlp.seg.common.Term;
import in.hocg.rabbit.common.utils.tokenizer.PostKeywordFilter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/4/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class TextUtils {

    public List<String> getKeyword(String text, int maxLength) {
        return HanLP.segment(text).stream().filter(PostKeywordFilter.FILTER::shouldInclude).map(term -> term.word)
            .distinct().limit(maxLength)
            .collect(Collectors.toList());
    }

    public String getSummary(String text, int maxLength) {
        String rContent = HtmlUtil.removeHtmlTag(text);
        return StrUtil.sub(rContent, 0, maxLength);
    }

}
