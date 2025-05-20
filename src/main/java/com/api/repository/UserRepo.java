<<<<<<< HEAD
package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{
      Optional<User> findByEmail(String email); 
}
=======
package com.api.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.entities.User;
public interface UserRepo extends JpaRepository<User,Integer>{
	Optional<User> findByEmail(String email); 
}
>>>>>>> 6565aec (Updated the file with latest changes)
