package in.hocg.rabbit.com.biz.mapper;

import in.hocg.rabbit.com.biz.entity.FileInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * [基础模块] 文件引用表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    List<FileInfo> listByRefTypeAndRefIdOrderBySortDescAndCreatedAtDesc(@Param("refType") Serializable code, @Param("refId") Long relId);
}
