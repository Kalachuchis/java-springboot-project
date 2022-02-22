package com.example.discussion.repositories;

import com.example.discussion.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Object> {
  User findByUsername (String username);
}
