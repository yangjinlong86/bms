package org.nocoder.djms.service;

import org.nocoder.djms.model.Resource;

import java.util.List;
import java.util.Map;

/**
 * @author Created by jason on 17/11/3.
 */
public interface ResourceService {
    List<Resource> findResourcesByRoleId(String roleId);

    List<Resource> findResources();

    List<Resource> findUserResources(Map<String, Object> paramMap);
}
