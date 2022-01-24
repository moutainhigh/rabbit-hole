package in.hocg.rabbit.wl.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.wl.biz.entity.LogisticsLine;
import in.hocg.rabbit.wl.biz.entity.StartingPointRef;
import in.hocg.rabbit.wl.biz.mapper.LogisticsLineMapper;
import in.hocg.rabbit.wl.biz.mapstruct.LogisticsLineMapping;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineBatchCreateRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineCompleteRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineCreateRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLinePagingRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineSearchRo;
import in.hocg.rabbit.wl.biz.pojo.ro.logisticsline.LogisticsLineUpdateRo;
import in.hocg.rabbit.wl.biz.pojo.vo.LogisticsLineComplexVo;
import in.hocg.rabbit.wl.biz.pojo.vo.LogisticsLineSearchVo;
import in.hocg.rabbit.wl.biz.service.LogisticsLineService;
import in.hocg.rabbit.wl.biz.service.StartingPointRefService;
import in.hocg.rabbit.wl.biz.service.WarehouseService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * [物流模块] 物流线路表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LogisticsLineServiceImpl extends AbstractServiceImpl<LogisticsLineMapper, LogisticsLine>
    implements LogisticsLineService {
    private final LogisticsLineMapping mapping;
    private final WarehouseService warehouseService;
    private final StartingPointRefService startingPointRefService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long logisticsLineId, LogisticsLineUpdateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long updater = ro.getUpdater();
        List<Long> warehouseId = ro.getWarehouseId();

        LogisticsLine entity = mapping.asLogisticsLine(ro);
        entity.setId(logisticsLineId);
        entity.setLastUpdatedAt(now);
        entity.setLastUpdater(updater);
        validInsertOrUpdate(entity);

        if (Objects.nonNull(warehouseId)) {
            startingPointRefService.validInsertOrUpdateByLogisticsLineId(logisticsLineId, warehouseId.parallelStream()
                .map(id -> new StartingPointRef().setWarehouseId(id).setLogisticsLineId(logisticsLineId))
                .collect(Collectors.toList()));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(LogisticsLineCreateRo ro) {
        LocalDateTime now = LocalDateTime.now();
        Long creator = ro.getCreator();
        List<Long> warehouseId = ro.getWarehouseId();

        LogisticsLine entity = mapping.asLogisticsLine(ro);
        entity.setCreator(creator);
        entity.setCreatedAt(now);
        validInsertOrUpdate(entity);
        Long logisticsLineId = entity.getId();

        if (Objects.nonNull(warehouseId)) {
            startingPointRefService.validInsertOrUpdateByLogisticsLineId(logisticsLineId, warehouseId.parallelStream()
                .map(id -> new StartingPointRef().setWarehouseId(id).setLogisticsLineId(logisticsLineId))
                .collect(Collectors.toList()));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LogisticsLineComplexVo getLogisticsLine(Long id) {
        LogisticsLine entity = getById(id);
        return convertComplex(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<LogisticsLineComplexVo> paging(LogisticsLinePagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage())
            .convert(this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<LogisticsLineComplexVo> complete(LogisticsLineCompleteRo ro) {
        List<LogisticsLine> records = baseMapper.complete(ro, ro.ofPage()).getRecords();
        return LangUtils.toList(records, this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean hasLogisticsLineByWarehouseId(Long warehouseId) {
        return startingPointRefService.hasLogisticsLineByWarehouseId(warehouseId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<LogisticsLineComplexVo> listLogisticsLineComplexByWarehouseId(Long warehouseId) {
        return LangUtils.toList(listLogisticsLineByWarehouseId(warehouseId), this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchCreate(LogisticsLineBatchCreateRo ro) {
        Long creator = ro.getCreator();
        List<Long> warehouseId = ro.getWarehouseId();
        String provinceAdcode = ro.getProvinceAdcode();
        String cityAdcode = ro.getCityAdcode();

        for (LogisticsLineBatchCreateRo.LogisticsLineCreateRo logisticsLine : ro.getLogisticsLines()) {
            LogisticsLineCreateRo logisticsLineCreateRo = mapping.aslogisticsLineCreateRo(logisticsLine,
                provinceAdcode, cityAdcode, warehouseId, creator);
            this.create(logisticsLineCreateRo);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<LogisticsLineSearchVo> search(LogisticsLineSearchRo ro) {
        LogisticsLineSearchRo.Point starPoint = ro.getStarPoint();
        LogisticsLineSearchRo.Point endPoint = ro.getEndPoint();
        List<LogisticsLineSearchRo.GoodsRo> goods = ro.getGoods();

        return baseMapper.search(starPoint, endPoint, ro.ofPage())
            .convert(item -> item.setGoods(goods.stream().map(ro1 -> new LogisticsLineSearchVo.GoodsVo()
                .setH(ro1.getH())
                .setW(ro1.getW())
                .setL(ro1.getL())
                .setWeigh(ro1.getWeigh())
                .setQuantity(ro1.getQuantity())
                .setTotalPrice(ro1.getTotalPrice(item.getUnitPrice())))
                .collect(Collectors.toList()))
                .setTotalPrice(item.getGoods().parallelStream()
                    .map(LogisticsLineSearchVo.GoodsVo::getTotalPrice)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal::add)
                    .orElse(null)));
    }

    private List<LogisticsLine> listLogisticsLineByWarehouseId(Long warehouseId) {
        return startingPointRefService.listLogisticsLineByWarehouseId(warehouseId);
    }

    private LogisticsLineComplexVo convertComplex(LogisticsLine entity) {
        LogisticsLineComplexVo result = mapping.asLogisticsLineComplexVo(entity);
        result.setWarehouses(warehouseService.listWarehouseComplexByLogisticsLineId(entity.getId()));
        return result;
    }

    @Override
    public void validEntity(LogisticsLine entity) {
        super.validEntity(entity);
    }
}
