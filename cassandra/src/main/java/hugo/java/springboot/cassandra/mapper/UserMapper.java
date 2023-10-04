package hugo.java.springboot.cassandra.mapper;

import hugo.java.springboot.cassandra.data.entity.UserEntity;
import hugo.java.springboot.cassandra.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity dtoToEntity(UserDto dto);

    UserDto entityToDto(UserEntity entity);

}
