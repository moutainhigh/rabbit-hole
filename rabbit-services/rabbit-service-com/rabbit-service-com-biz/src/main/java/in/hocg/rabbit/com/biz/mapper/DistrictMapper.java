package in.hocg.rabbit.com.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.com.biz.entity.District;
import in.hocg.rabbit.com.biz.pojo.ro.district.DistrictCompleteRo;
import in.hocg.rabbit.com.biz.pojo.vo.district.DistrictCompleteVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [基础模块] 城市规划表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Mapper
public interface DistrictMapper extends BaseMapper<District> {

    List<District> getChildrenDistrictByAdcode(@Param("adcode") String adcode);

    IPage<DistrictCompleteVo> complete(@Param("ro") DistrictCompleteRo ro, @Param("ofPage") Page ofPage);
}
