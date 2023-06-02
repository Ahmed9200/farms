package com.example.farms.repositories;

import com.example.farms.models.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);
    Users findById(int id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = ?2 WHERE username=?1 ", nativeQuery = true)
    void updateUserPasswordByUsername(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET photo = ?1 ,content_type=?2 WHERE id=?3 ", nativeQuery = true)
    void updatePhoto(String photo,String contentType, int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET name = ?1 WHERE id=?2 ", nativeQuery = true)
    void updateName(String name, int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET phone = ?1 WHERE id=?2 ", nativeQuery = true)
    void updatePhone(String phone, int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = ?2 WHERE id=?1 ", nativeQuery = true)
    void updateUserPasswordById(int userId, String password);

}
