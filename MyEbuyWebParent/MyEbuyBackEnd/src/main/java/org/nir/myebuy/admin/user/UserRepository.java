package org.nir.myebuy.admin.user;

import org.nir.myebuy.common.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
