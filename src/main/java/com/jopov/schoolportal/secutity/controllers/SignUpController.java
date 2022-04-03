package com.jopov.schoolportal.secutity.controllers;

import com.jopov.schoolportal.secutity.request.SignupRequest;
import com.jopov.schoolportal.secutity.response.MessageResponse;
import com.jopov.schoolportal.secutity.services.RoleService;
import com.jopov.schoolportal.secutity.services.UserService;
import com.jopov.schoolportal.common.models.persons.Director;
import com.jopov.schoolportal.common.services.factory.PersonFactory;
import com.jopov.schoolportal.secutity.enums.ERole;
import com.jopov.schoolportal.secutity.models.Role;
import com.jopov.schoolportal.secutity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
public class SignUpController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private PersonFactory personFactory;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: Выбранное имя уже используется!"));
        }

        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> signUpRequestRole = signUpRequest.getRole();
        Set<Role> roleSet = new HashSet<>();

        Director director = (Director) personFactory.createPersonByPersonRole(signUpRequestRole);


        if (signUpRequestRole == null) {
            Role userRole = null;
            try {
                userRole = roleService.findRoleByRoleName(ERole.ROLE_PUPIL);
            } catch (RoleNotFoundException e) {
                e.printStackTrace();
            }
            roleSet.add(userRole);
        } else {
            signUpRequestRole.forEach(role -> {
                switch (role) {
                    case "ROLE_DIRECTOR":
                        Role directorRole = null;
                        try {
                            directorRole = roleService.findRoleByRoleName(ERole.ROLE_DIRECTOR);
                        } catch (RoleNotFoundException e) {
                            e.printStackTrace();
                        }
                        roleSet.add(directorRole);

                        break;
                    case "ROLE_HEAD_TEACHER":
                        Role headTeacherRole = null;
                        try {
                            headTeacherRole = roleService.findRoleByRoleName(ERole.ROLE_HEAD_TEACHER);
                        } catch (RoleNotFoundException e) {
                            e.printStackTrace();
                        }
                        roleSet.add(headTeacherRole);

                        break;
                    case "ROLE_TEACHER":
                        Role teacherRole = null;
                        try {
                            teacherRole = roleService.findRoleByRoleName(ERole.ROLE_TEACHER);
                        } catch (RoleNotFoundException e) {
                            e.printStackTrace();
                        }
                        roleSet.add(teacherRole);

                        break;
                    default:
                        Role pupilRole = null;
                        try {
                            pupilRole = roleService.findRoleByRoleName(ERole.ROLE_PUPIL);
                        } catch (RoleNotFoundException e) {
                            e.printStackTrace();
                        }
                        roleSet.add(pupilRole);
                }
            });
        }

        user.setRoles(roleSet);
        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("Пользователь успешно зарегистрирован!"));
    }
}
