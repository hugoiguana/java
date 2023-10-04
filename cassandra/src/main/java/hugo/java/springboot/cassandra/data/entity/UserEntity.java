package hugo.java.springboot.cassandra.data.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("user")
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @PrimaryKey
    UUID id;

    @Column("name")
    String name;

    @Column("created_date")
    LocalDateTime createdDate;
}
