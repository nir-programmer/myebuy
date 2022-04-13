package org.nir.myebuy.api.dao;

import org.nir.myebuy.common.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
