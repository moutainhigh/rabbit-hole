package in.hocg.rabbit.com.biz.service;

import in.hocg.rabbit.com.biz.entity.DataDictItem;
import in.hocg.rabbit.com.biz.pojo.ro.DataDictItemBatchInsertRo;
import in.hocg.rabbit.com.biz.pojo.ro.DataDictItemInsertRo;
import in.hocg.rabbit.com.biz.pojo.ro.DataDictItemUpdateRo;
import in.hocg.rabbit.com.biz.pojo.vo.DataDictItemComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.DataDictItemOrdinaryVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [基础模块] 数据字典项表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
public interface DataDictItemService extends AbstractService<DataDictItem> {

    List<DataDictItemOrdinaryVo> listOrdinaryByDataDictId(Long id);

    void deleteByDataDictId(Long id);

    void insertOne(Long dataDictId, Long userId, DataDictItemInsertRo item);

    void insertOne(Long dictId, DataDictItemBatchInsertRo ro);

    DataDictItemComplexVo getComplex(Long id);

    void updateOne(Long id, DataDictItemUpdateRo ro);

    void deleteOne(Long id);
}
