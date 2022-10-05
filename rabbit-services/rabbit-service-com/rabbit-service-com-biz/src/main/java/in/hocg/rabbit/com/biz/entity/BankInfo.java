package in.hocg.rabbit.com.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [通用模块] 银行信息表
 * </p>
 *
 * @author hocgin
 * @since 2022-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_bank_info")
public class BankInfo extends CommonEntity<BankInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("联行号/超级网银号")
    @TableField("code")
    private String code;
    @ApiModelProperty("类型: super_pay_bank_code => 超级网银号; bank_code => 联行号")
    @TableField("type")
    private String type;
    @ApiModelProperty("全称")
    @TableField("full_name")
    private String fullName;
    @ApiModelProperty("简称")
    @TableField("simple_name")
    private String simpleName;
    @ApiModelProperty("城市代码")
    @TableField("city_code")
    private String cityCode;
    @ApiModelProperty("行号状态")
    @TableField("bank_status")
    private String bankStatus;
    @ApiModelProperty("行别代码")
    @TableField("bank_code")
    private String bankCode;
    @ApiModelProperty("清算行号")
    @TableField("clearing_bank_code")
    private String clearingBankCode;

}
