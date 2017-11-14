package org.dj.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.RoleMapper;
import org.dj.bms.model.Role;
import org.dj.bms.model.User;
import org.dj.bms.query.QueryBean;
import org.dj.bms.service.RoleService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author Created by jason on 17/11/3.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int saveOrUpdate(Role role){
        if(StringUtils.isBlank(role.getId())){
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
    public List<Role> findAllRoles() {
        return roleMapper.findAllRoles();
    }

    @Override
    public PageInfo<Role> selectRoles(QueryBean queryBean) {
        PageHelper.startPage(queryBean.getPageNum(), queryBean.getLimitNum());
        Map<String, String> paramsMap = null;
        try {
            paramsMap = BeanUtils.describe(queryBean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        List<Role> list = roleMapper.findAllRoles();
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        return pageInfo;
    }

    @Override
    public Role findByRoleName(String roleName) {
        return null;
    }
}
