package com.upstack.test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<UserRequest> users;

    public UserController() {
        users = new LinkedList<>() {{
            add(new UserRequest("Andrei", "andreitest"));
            add(new UserRequest("Mike", "miketest"));
        }};
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest request) {
        if (users.stream().anyMatch(usernameAndPasswordAreCorrect(request))) {
            return new UserResponse(true);
        }
        return new UserResponse(false);
    }

    private Predicate<UserRequest> usernameAndPasswordAreCorrect(UserRequest request) {
        return u -> u.getUsername().equalsIgnoreCase(request.getUsername()) && u.getPassword().equals(request.getPassword());
    }

    @PostMapping("/save")
    public UserResponse save(@RequestBody UserRequest request) {
        if (Strings.isBlank(request.getUsername()) || Strings.isBlank(request.getPassword())) {
            throw new BadRequestException("Both user and password are mandatory");
        }
        if (users.stream().anyMatch(usernameExisting(request))) {
            return new UserResponse(false);
        }
        users.add(request);
        return new UserResponse(true);
    }

    private Predicate<UserRequest> usernameExisting(UserRequest request) {
        return u -> u.getUsername().equalsIgnoreCase(request.getUsername());
    }

}
