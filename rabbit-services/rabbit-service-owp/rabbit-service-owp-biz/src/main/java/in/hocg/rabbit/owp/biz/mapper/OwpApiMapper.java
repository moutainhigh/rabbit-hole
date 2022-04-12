package in.hocg.rabbit.owp.biz.mapper;

import in.hocg.rabbit.owp.biz.entity.OwpApi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [开放平台] 接口表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Mapper
public interface OwpApiMapper extends BaseMapper<OwpApi> {

    Integer hasAuthority(@Param("appid") String appid, @Param("method") String method);
}
