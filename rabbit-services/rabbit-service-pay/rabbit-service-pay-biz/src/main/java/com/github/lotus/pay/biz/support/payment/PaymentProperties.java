package com.github.lotus.pay.biz.support.payment;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ToString
@ConfigurationProperties(prefix = PaymentProperties.PREFIX)
public class PaymentProperties {
    public static final String PREFIX = "rabbit.payment";
    private String urlPrefix;
}
