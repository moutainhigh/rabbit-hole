package in.hocg.rabbit.mall.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.web.datastruct.tree.AbstractTreeNode;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/3/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
@Accessors(chain = true)
public class ProductCategoryTreeVo extends AbstractTreeNode<ProductCategoryTreeVo> {

}
