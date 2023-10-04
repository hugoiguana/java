package hugo.java.springboot.cassandra.data.repository;

import hugo.java.springboot.cassandra.data.entity.UserEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends ReactiveCassandraRepository<UserEntity, UUID>  {

}
