package hugo.java.springboot.cassandra.model.service;

import hugo.java.springboot.cassandra.mapper.UserMapper;
import hugo.java.springboot.cassandra.data.repository.UserRepository;
import hugo.java.springboot.cassandra.dto.UserDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Mono<UserDto> getById(UUID id) {
        return userRepository.findById(id).map(userMapper::entityToDto);
    }

    @Override
    public Flux<UserDto> getAll() {
        return userRepository.findAll().map(userMapper::entityToDto);
    }

    @Override
    public Mono<UserDto> create(UserDto userDto) {
        return Mono.just(userMapper.dtoToEntity(userDto))
                .flatMap(userRepository::save)
                .map(userMapper::entityToDto);
    }

}
