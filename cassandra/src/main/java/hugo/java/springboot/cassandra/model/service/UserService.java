package hugo.java.springboot.cassandra.model.service;

import hugo.java.springboot.cassandra.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {

    Mono<UserDto> getById(UUID id);

    Flux<UserDto> getAll();

    Mono<UserDto> create(UserDto userDto);


}
