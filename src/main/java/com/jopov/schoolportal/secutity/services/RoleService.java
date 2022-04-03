package com.jopov.schoolportal.secutity.services;

import com.jopov.schoolportal.secutity.enums.ERole;
import com.jopov.schoolportal.secutity.models.Role;

import javax.management.relation.RoleNotFoundException;

public interface RoleService {
    Role findRoleByRoleName(ERole roleName) throws RoleNotFoundException;
    void saveRole(Role role);
}
