package in.hocg.rabbit.common.utils.tokenizer;

import cn.hutool.core.util.StrUtil;
import com.hankcs.hanlp.dictionary.stopword.Filter;
import com.hankcs.hanlp.seg.common.Term;

/**
 * Created by hocgin on 2022/4/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class PostKeywordFilter implements Filter {
    public static final PostKeywordFilter FILTER = new PostKeywordFilter();

    @Override
    public boolean shouldInclude(Term term) {
        return StrUtil.containsAny(term.nature.toString(), "n");
    }
}
