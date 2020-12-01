package com.sefako.makgatho.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sefako.makgatho.demo.models.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	@Query("SELECT r FROM Role r WHERE r.name = :name")
	Role findByName(@Param("name") String name);
}
