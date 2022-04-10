package in.hocg.rabbit.owp.biz.mapper;

import in.hocg.rabbit.owp.biz.entity.DeveloperApp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [开放平台] 开发者应用表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Mapper
public interface DeveloperAppMapper extends BaseMapper<DeveloperApp> {

    DeveloperApp getByEncoding(@Param("encoding") String encoding);
}
