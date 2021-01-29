package com.jdbc_.role;

import com.jdbc_.user.User;
import com.jdbc_.user.UserRepository;

public class RoleAssignmentService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RoleAssignmentService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void assignRoleToUser(String username, String roleName) {
        User user = userRepository.getByUsername(username);
        Role role = roleRepository.getByRoleName(roleName);
        user.setRoleId(role.getId());
        userRepository.update(user);
    }
}
