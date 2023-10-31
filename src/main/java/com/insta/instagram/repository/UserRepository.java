package com.insta.instagram.repository;

import com.insta.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);

    @Query("select u from User u where u.id in :users")
    public List<User> findAllUsersByUserIds(@Param("users")List<Integer> userIds);

    @Query("select distinct U from User U where U.username like %:query% or U.email like %:query%")
    public List<User> findByQuery(@Param("query") String query);
}
