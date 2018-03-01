package com.kinsoft.springboot.repository;

import com.kinsoft.springboot.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    // bl_subscriber == Subscriber class
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Subscriber c WHERE c.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Subscriber c WHERE c.phone = :phone")
    boolean existsByPhone(@Param("phone") String phone);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Subscriber c WHERE c.email = :email")
    boolean existsByEmail(@Param("email") String email);
}
