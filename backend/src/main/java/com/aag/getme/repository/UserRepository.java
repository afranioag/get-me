package com.aag.getme.repository;

import com.aag.getme.model.User;
import com.aag.getme.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(nativeQuery = true, value = """
			SELECT user.email AS username, user.password, role.id AS roleId, role.authority
			FROM user
			INNER JOIN user_role ON user.id = user_role.user_id
			INNER JOIN role ON role.id = user_role.role_id
			WHERE user.email = :email
		""")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

}
