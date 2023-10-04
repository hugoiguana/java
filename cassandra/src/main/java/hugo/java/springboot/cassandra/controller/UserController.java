package hugo.java.springboot.cassandra.controller;

import hugo.java.springboot.cassandra.dto.UserDto;
import hugo.java.springboot.cassandra.model.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Mono<UserDto> getById(@RequestParam UUID id) {
        return userService.getById(id);
    }

    @GetMapping
    public Flux<UserDto> getAll() {
        return userService.getAll();
    }


    @PostMapping
    public Mono<UserDto> create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

}
