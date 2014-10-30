package io.pivotal.findit.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;

@RepositoryRestResource
public interface NameValueRepository extends CrudRepository<NameValue, Long> {

    List<NameValue> findByName(String name);
    
    @Query("SELECT p FROM NameValue p WHERE lower(p.value) LIKE lower(concat(:query,'%'))")
    List<NameValue> findByQuery(@Param("query") String query);
}