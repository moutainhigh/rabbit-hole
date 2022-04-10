package in.hocg.rabbit.openway.constants;

import org.springframework.core.Ordered;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class OrderedConstants {
    public static final int CONTEXT_ORDERED = Ordered.HIGHEST_PRECEDENCE;
    public static final int PRE_CHECK_ORDERED = Ordered.HIGHEST_PRECEDENCE + 1;
    public static final int VALID_REQUEST_ORDERED = Ordered.HIGHEST_PRECEDENCE + 2;
    public static final int REWRITE_FORWARD_ORDERED = Ordered.HIGHEST_PRECEDENCE + 3;
}
