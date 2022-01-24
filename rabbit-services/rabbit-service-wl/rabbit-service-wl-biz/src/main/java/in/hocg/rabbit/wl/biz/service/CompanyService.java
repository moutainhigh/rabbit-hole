package in.hocg.rabbit.wl.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.wl.biz.entity.Company;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyCompleteRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyCreateRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyDeleteRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyPagingRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyUpdateRo;
import in.hocg.rabbit.wl.biz.pojo.vo.CompanyComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [物流模块] 公司表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
public interface CompanyService extends AbstractService<Company> {

    void create(CompanyCreateRo ro);

    void update(Long id, CompanyUpdateRo ro);

    CompanyComplexVo getCompany(Long id);

    void delete(Long id);

    IPage<CompanyComplexVo> paging(CompanyPagingRo ro);

    List<CompanyComplexVo> complete(CompanyCompleteRo ro);

    List<Company> listCompanyByCompanyId(List<Long> values);

    void delete(CompanyDeleteRo ro);
}
