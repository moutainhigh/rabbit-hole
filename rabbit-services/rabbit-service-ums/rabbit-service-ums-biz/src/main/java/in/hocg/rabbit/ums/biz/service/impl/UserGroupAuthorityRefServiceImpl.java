package in.hocg.rabbit.ums.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.ums.biz.entity.Authority;
import in.hocg.rabbit.ums.biz.entity.User;
import in.hocg.rabbit.ums.biz.entity.UserGroup;
import in.hocg.rabbit.ums.biz.entity.UserGroupAuthorityRef;
import in.hocg.rabbit.ums.biz.mapper.UserGroupAuthorityRefMapper;
import in.hocg.rabbit.ums.biz.pojo.ro.UserGroupRefUserPagingRo;
import in.hocg.rabbit.ums.biz.service.AuthorityService;
import in.hocg.rabbit.ums.biz.service.UserGroupAuthorityRefService;
import in.hocg.rabbit.ums.biz.service.UserGroupService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * <p>
 * [权限模块] 用户组x权限表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserGroupAuthorityRefServiceImpl extends AbstractServiceImpl<UserGroupAuthorityRefMapper, UserGroupAuthorityRef> implements UserGroupAuthorityRefService {
    private final UserGroupService userGroupService;
    private final AuthorityService authorityService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantAuthority(Long userGroupId, Long authorityId) {
        final UserGroup userGroup = userGroupService.getById(userGroupId);
        ValidUtils.notNull(userGroup, "授权失败");
        Authority authority = authorityService.getById(authorityId);
        ValidUtils.notNull(authority, "授权失败");

        // 已经具备权限
        if (hasByUserGroupIdAndAuthorityId(userGroupId, authorityId)) {
            return;
        }

        UserGroupAuthorityRef entity = new UserGroupAuthorityRef()
            .setAuthorityId(authorityId).setUserGroupId(userGroupId);
        validInsert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean hasUserGroupByAuthorityId(Long authorityId) {
        return has(UserGroupAuthorityRef::getAuthorityId, authorityId, UserGroupAuthorityRef::getId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantAuthorities(Long userGroupId, List<Long> authorities) {
        List<UserGroupAuthorityRef> entities = authorities.parallelStream()
            .map(authorityId -> new UserGroupAuthorityRef().setAuthorityId(authorityId).setUserGroupId(userGroupId))
            .collect(Collectors.toList());
        List<UserGroupAuthorityRef> allData = this.listByUserGroupId(userGroupId);

        final BiFunction<UserGroupAuthorityRef, UserGroupAuthorityRef, Boolean> isSame =
            (t1, t2) -> LangUtils.equals(t1.getAuthorityId(), t2.getAuthorityId());
        final List<UserGroupAuthorityRef> mixedList = LangUtils.getMixed(allData, entities, isSame);
        List<UserGroupAuthorityRef> deleteList = LangUtils.removeIfExits(allData, mixedList, isSame);
        List<UserGroupAuthorityRef> addList = LangUtils.removeIfExits(entities, mixedList, isSame);

        // 删除
        this.removeByIds(deleteList.parallelStream()
            .map(UserGroupAuthorityRef::getId)
            .collect(Collectors.toList()));

        // 新增
        addList.forEach(this::validInsertOrUpdate);

        // 更新
        mixedList.forEach(this::validInsertOrUpdate);
    }

    @Override
    public IPage<User> pagingUser(UserGroupRefUserPagingRo ro) {
        return baseMapper.pagingUser(ro, ro.ofPage());
    }

    private List<UserGroupAuthorityRef> listByUserGroupId(Long userGroupId) {
        return lambdaQuery().eq(UserGroupAuthorityRef::getUserGroupId, userGroupId).list();
    }

    /**
     * 判定用户组是否已有指定权限
     *
     * @param userGroupId 目标用户组
     * @param authorityId 指定权限
     * @return 是否具有
     */
    private boolean hasByUserGroupIdAndAuthorityId(Long userGroupId, Long authorityId) {
        return !lambdaQuery().eq(UserGroupAuthorityRef::getUserGroupId, userGroupId)
            .eq(UserGroupAuthorityRef::getAuthorityId, authorityId)
            .page(new Page<>(1, 1, false)).getRecords().isEmpty();
    }
}
