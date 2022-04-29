package in.hocg.rabbit.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.dictionary.stopword.Filter;
import com.hankcs.hanlp.seg.common.Term;
import in.hocg.rabbit.common.utils.tokenizer.PostKeywordFilter;
import lombok.experimental.UtilityClass;

import java.util.Collections;
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

    /**
     * 提取关键词
     *
     * @param text
     * @param maxLength
     * @return
     */
    public List<String> getKeyword(String text, int maxLength) {
        if (StrUtil.isBlank(text)) {
            return Collections.emptyList();
        }
        return HanLP.segment(text).stream().filter(PostKeywordFilter.FILTER::shouldInclude).map(term -> term.word)
            .filter(s -> StrUtil.nullToEmpty(s).length() <= 6)
            .distinct().limit(maxLength)
            .collect(Collectors.toList());
    }

    /**
     * 提取概述信息
     *
     * @param text
     * @param maxLength
     * @return
     */
    public String getSummary(String text, int maxLength) {
        if (StrUtil.isBlank(text)) {
            return null;
        }

        String rContent = HtmlUtil.removeHtmlTag(text);
        return StrUtil.sub(rContent, 0, maxLength);
    }

    /**
     * 敏感词检查
     *
     * @return
     */
    public Boolean checkSensitiveWord(String text) {
        SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
            .ignoreCase(true)
            .ignoreWidth(true)
            .ignoreNumStyle(true)
            .ignoreChineseStyle(true)
            .ignoreEnglishStyle(true)
            .ignoreRepeat(true)
            .enableNumCheck(true)
            .enableEmailCheck(true)
            .enableUrlCheck(true)
            .init();
        return wordBs.contains(text);
    }

    public static void main(String[] args) {
        SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
            .ignoreCase(true)
            .ignoreWidth(true)
            .ignoreNumStyle(true)
            .ignoreChineseStyle(true)
            .ignoreEnglishStyle(true)
            .ignoreRepeat(true)
            .enableNumCheck(true)
            .enableEmailCheck(true)
            .enableUrlCheck(true)
            .init();
        String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
        Boolean aBoolean = TextUtils.checkSensitiveWord(text);
        List<String> all = wordBs.findAll(text);
        System.out.println(all);
    }

}
