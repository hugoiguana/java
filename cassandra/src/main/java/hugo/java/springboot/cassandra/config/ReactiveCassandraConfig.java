package hugo.java.springboot.cassandra.config;

import com.datastax.oss.driver.api.core.CqlSession;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.cognitor.cassandra.migration.spring.CassandraMigrationAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;

@Configuration
@Getter
@EnableReactiveCassandraRepositories(basePackages = { "hugo.java.springboot.cassandra.data" })
@Slf4j
public class ReactiveCassandraConfig extends AbstractReactiveCassandraConfiguration {

    @Value("${spring.data.cassandra.local-datacenter}")
    private String localDatacenter;

    @Value("${spring.data.cassandra.contact-points}")
    private String dbHost;

    @Value("${spring.data.cassandra.port}")
    private int dbPort;

    @Value("${spring.data.cassandra.username}")
    private String username;

    @Value("${spring.data.cassandra.password}")
    private String password;

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keySpace;

    @Value("${spring.data.cassandra.schema-action}")
    private SchemaAction schemaAction;

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return schemaAction;
    }
    @Override
    protected String getLocalDataCenter() {
        return localDatacenter;
    }

    /*
    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return List.of(CreateKeyspaceSpecification.createKeyspace(this.getKeyspaceName()));
    }
    */

    @Bean
    @Primary
    @Override
    @DependsOn(CassandraMigrationAutoConfiguration.MIGRATION_TASK_BEAN_NAME)
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean bean = new CqlSessionFactoryBean();
        bean.setContactPoints(this.getContactPoints());
        bean.setKeyspaceCreations(this.getKeyspaceCreations());
        bean.setKeyspaceDrops(this.getKeyspaceDrops());
        bean.setKeyspaceName(this.getKeyspaceName());
        bean.setLocalDatacenter(this.getLocalDataCenter());
        bean.setPort(this.getPort());
        bean.setUsername(this.username);
        bean.setPassword(this.password);
        return bean;
    }

    @Bean(CassandraMigrationAutoConfiguration.CQL_SESSION_BEAN_NAME)
    public CqlSession cassandraMigrationCqlSession() {
        return CqlSession.builder()
                .addContactPoint(new InetSocketAddress(dbHost, dbPort))
                .withKeyspace(this.getKeyspaceName())
                .withAuthCredentials(this.username, this.password)
                .withLocalDatacenter(this.getLocalDataCenter())
                .build();
    }

}
