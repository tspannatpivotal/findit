package io.pivotal.nbcuview;

import com.pivotal.gemfirexd.hibernate.GemFireXDDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

@Configuration
@EnableJpaRepositories("io.pivotal.nbcuview.domain")
@Profile("gemfirexd")
public class GemfireXdRepositoryConfig extends AbstractJpaRepositoryConfig {

    protected String getHibernateDialect() {
        return com.pivotal.gemfirexd.hibernate.GemFireXDDialect.class.getName();
    }

    protected String getPackagesToScan() {
        return "io.pivotal.nbcuview.domain";
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.DERBY);
        jpaVendorAdapter.setDatabasePlatform(GemFireXDDialect.class.getName());
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(false);
        return jpaVendorAdapter;
    }

    protected Properties getAdditionalProperties() {
        Properties properties = new Properties();
        properties.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "validate");
        return properties;
    }
}