package org.nir.myebuy.api.dao;

import org.nir.myebuy.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
//@CrossOrigin({"http://localhost:4200", "http://localhost:5501"})
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:5501"})
public interface UserRepository extends JpaRepository<User, Integer> {

}
