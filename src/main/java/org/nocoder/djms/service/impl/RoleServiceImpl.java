package org.nocoder.djms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.nocoder.djms.model.Role;
import org.nocoder.djms.model.RoleResource;
import org.nocoder.djms.query.RoleQueryBean;
import org.nocoder.djms.repository.RoleMapper;
import org.nocoder.djms.repository.RoleResourceMapper;
import org.nocoder.djms.service.BaseService;
import org.nocoder.djms.service.RoleService;
import org.nocoder.djms.utils.BeanUtils;
import org.nocoder.djms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Created by jason on 17/11/3.
 */
@Service
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public int saveOrUpdate(Role role) {
        if (StringUtils.isBlank(role.getId())) {
            role.setId(IdGenerator.generateUUID());
            return roleMapper.insertSelective(role);
        }
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public List<Role> findRoleListByUserId(String userId) {
        return roleMapper.findRoleListByUserId(userId);
    }


    @Override
    public PageInfo<Role> selectRoles(RoleQueryBean queryBean) {
        PageHelper.startPage(queryBean.getPageNum(), queryBean.getLimitNum()).setOrderBy("ROLE_NAME ASC");
        List<Role> list = roleMapper.findRoles(BeanUtils.convertBean2Map(queryBean));
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        return pageInfo;
    }

    @Override
    public Role findByRoleName(String roleName) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoleByIds(String ids) {
        int resCount = 0;
        String[] idArr;
        String separator = ",";
        if (ids.contains(separator)) {
            idArr = ids.split(separator);
        } else {
            idArr = new String[]{ids};
        }
        resCount += roleMapper.deleteByIds(idArr);
        resCount += roleResourceMapper.deleteRoleResource(idArr);
        return resCount;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRoleResource(RoleResource roleResource) {
        int count = this.deleteRoleResource(new String[]{roleResource.getRoleId()});
        List<RoleResource> roleResourceList = parseRoleResource(roleResource);
        return (roleResourceList.size() > 0) ? roleResourceMapper.insert(roleResourceList) : count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoleResource(String[] roleIdArr) {
        return roleResourceMapper.deleteRoleResource(roleIdArr);
    }

    /**
     * 拆分多个resourceId,组成List<RoleResource>
     *
     * @param roleResource
     * @return
     */
    private List<RoleResource> parseRoleResource(RoleResource roleResource) {
        if (StringUtils.isNotBlank(roleResource.getResourceId())) {
            List<RoleResource> roleResourceList = new ArrayList<>();
            RoleResource rr = null;
            if (roleResource.getResourceId().contains(",")) {
                String resourceIds = roleResource.getResourceId();
                String[] resourceIdList = resourceIds.substring(0, resourceIds.length() - 1).split(",");
                for (String resourcesId : resourceIdList) {
                    rr = new RoleResource();
                    rr.setRoleId(roleResource.getRoleId());
                    rr.setResourceId(resourcesId);
                    roleResourceList.add(rr);
                }
                return roleResourceList;
            } else {
                roleResourceList.add(roleResource);
                return roleResourceList;
            }
        }
        return Collections.emptyList();
    }
}
