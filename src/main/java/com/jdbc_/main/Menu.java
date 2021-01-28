package com.jdbc_.main;

import com.jdbc_.role.Role;
import com.jdbc_.role.RoleAssignmentService;
import com.jdbc_.role.RoleRepository;
import com.jdbc_.user.User;
import com.jdbc_.user.UserRepository;

import java.util.Scanner;

public class Menu {
    private Scanner in = new Scanner(System.in);
    UserRepository userRepository = new UserRepository();
    RoleRepository roleRepository = new RoleRepository();
    RoleAssignmentService roleAssignmentService = new RoleAssignmentService(userRepository, roleRepository);

    void printMainMenu() {
        System.out.println("Press cu to create user");
        System.out.println("Press cr to create role");
        System.out.println("Press ar to assign role to user");
        System.out.println("Press du to delete user by username");
        System.out.println("Press dr to delete role by roleName");
        System.out.println("Press uu to update user");
        System.out.println("Press ur to update role");
        System.out.println("Press gu to get user");
        System.out.println("Press gr to get role");
        System.out.println("Press q to quit");
    }

    void readUserOption() {
        String input = in.next();
        executeCRUD(input);
    }

    private void executeCRUD(String input) {
        switch (input) {
            case "cu":
                executeCreateUser();
                break;
            case "cr":
                executeCreateRole();
                break;
            case "ar":
                executeRoleAssignment();
                break;
            case "du":
                executeDeleteUser();
                break;
            case "dr":
                executeDeleteRole();
                break;
            case "uu":
                executeUpdateUser();
                break;
            case "ur":
                executeUpdateRole();
                break;
            case "gu":
                executeGetUser();
                break;
            case "gr":
                executeGetRole();
                break;
            case "q":
                System.out.println("Good Bye");
                System.exit(0);
            default:
                throw new IllegalArgumentException("This command not found");
        }
    }

    private void executeCreateUser() {
        System.out.println("Create User->");
        User user = readUser();
        userRepository.save(user);
    }

    private User readUser() {
        System.out.print("username:");
        String username = in.next();
        System.out.print("password:");
        String password = in.next();
        return new User(username, password);
    }

    private void executeCreateRole() {
        System.out.println("Create Role->");
        Role role = readRole();
        roleRepository.save(role);
    }

    private Role readRole() {
        System.out.print("roleName:");
        String roleName = in.next();
        return new Role(roleName);
    }

    private void executeDeleteUser() {
        System.out.println("Delete User->");
        System.out.print("Input username for delete -> ");
        String username = in.next();
        userRepository.delete(username);
    }

    private void executeDeleteRole() {
        System.out.println("Delete Role->");
        System.out.print("Input roleName for delete -> ");
        String roleName = in.next();
        roleRepository.delete(roleName);
    }

    private void executeUpdateUser() {
        System.out.println("Delete User->");
        System.out.print("Input username for update -> ");
        String oldUsername = in.next();
        User updatedUser = userRepository.get(oldUsername);
        System.out.print("Input NEW username for update -> ");
        String updatedUsername = in.next();
        System.out.print("Input NEW password for update -> ");
        String updatedPassword = in.next();
        updatedUser.setUsername(updatedUsername);
        updatedUser.setPassword(updatedPassword);
        userRepository.update(updatedUser);
    }

    private void executeUpdateRole() {
        System.out.println("Update Role->");
        System.out.print("Input roleName for update -> ");
        String oldRoleName = in.next();
        System.out.print("Input NEW roleName for update " + oldRoleName + " -> ");
        String updatedRoleName = in.next();
        roleRepository.update(oldRoleName, updatedRoleName);
    }

    private void executeGetUser() {
        System.out.println("Get User->");
        System.out.print("Input username for getting -> ");
        String username = in.next();
        System.out.println(userRepository.get(username).toString());
        System.out.println();
    }

    private void executeGetRole() {
        System.out.println("Get Role->");
        System.out.print("Input roleName for getting -> ");
        String roleName = in.next();
        System.out.println(roleRepository.get(roleName).toString());
        System.out.println();
    }

    private void executeRoleAssignment() {
        System.out.print("username:");
        String username = in.next();
        System.out.print("roleName:");
        String roleName = in.next();
        roleAssignmentService.assignRoleToUser(username, roleName);
    }
}
