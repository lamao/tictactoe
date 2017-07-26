package org.vmis.task.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @author Vycheslav Mischeryakov (vmischeryakov@gmail.com)
 */
@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource configureHsqlDatasource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
        /*      */.setType(EmbeddedDatabaseType.HSQL)
        /*      */.addScript("sql/init-schema.sql")
        /*      */.addScript("sql/init-data.sql")
        /*      */.build();
    }

    @Bean
    public NamedParameterJdbcTemplate configureJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

//    @PostConstruct
//    public void getDbManager(){
//        System.setProperty("java.awt.headless", "false");
//        DatabaseManagerSwing.main(
//            new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
//    }
}
