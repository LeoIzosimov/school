package com.jopov.schoolportal.secutity.services.impl;

import com.jopov.schoolportal.secutity.repository.RoleRepository;
import com.jopov.schoolportal.secutity.services.RoleService;
import com.jopov.schoolportal.secutity.enums.ERole;
import com.jopov.schoolportal.secutity.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role findRoleByRoleName(ERole roleName) throws RoleNotFoundException {
        return roleRepository.findRoleByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException
                ("Заданной роли: " + roleName + " не существует"));
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
