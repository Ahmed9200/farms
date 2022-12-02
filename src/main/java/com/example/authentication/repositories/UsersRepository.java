package com.example.authentication.repositories;

import com.example.authentication.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByUsernameAndPassword(String username, String PW);
    Users findByPhone(String phone);
    Users findByUsername(String username);
    Users findById(int id);
    List<Users> findAllByIdIn(List<Integer> ids);

    @Query(value = "SELECT *  FROM users  WHERE id=?1 and password=?2 ", nativeQuery = true)
    Map<Object, Object> login(int userId, String PW);


    @Query(value = "SELECT id, username ,name ,email, photo , phone , additional_phone , date_of_join , account_status " +
            " FROM users  WHERE id=?1 ", nativeQuery = true)
    Map<Object, Object> lightUser(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = ?2 WHERE username=?1 ", nativeQuery = true)
    int updateUserPasswordByUsername(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET photo = ?1 WHERE id=?2 ", nativeQuery = true)
    int updatePhoto(String photo, int userId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET name = ?1 WHERE id=?2 ", nativeQuery = true)
    int updateName(String name, int userId);



    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET phone = ?1 and username = phone WHERE id=?2 ", nativeQuery = true)
    int updatePhone(String phone, int userId);



    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET additional_phone = ?1 WHERE id=?2 ", nativeQuery = true)
    int updateAdditionalPhone(String phone2, int userId);



    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET email = ?1 WHERE id=?2 ", nativeQuery = true)
    int updateEmail(String email, int userId);



    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = ?2 WHERE id=?1 ", nativeQuery = true)
    void updateUserPasswordById(int userId, String password);


    @Query(value = "SELECT distinct users.id ," +
            " users.username, users.name ,users.email," +
            " users.phone," +
            " users.additional_phone," +
            " users.date_of_join," +
            "users.photo ,account_status " +
            " FROM users order by date_of_join asc " +
            " limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object, Object>> getAllUsersAsc(int limit,int offset , String date);

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users " +
            " order by date_of_join asc limit ?1 offset ?2  ", nativeQuery = true)
    long getAllUsersAscCount(int limit,int offset , String date);



    @Query(value = "SELECT distinct users.id ," +
            " users.username, users.name ,users.email," +
            " users.phone," +
            " users.additional_phone," +
            " users.date_of_join," +
            "users.photo ,account_status " +
            " FROM users " +
            " WHERE (?3 is null or date_of_join >= ?3) limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object, Object>> latestJoinedUsers(int limit,int offset , String date);

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users " +
            " WHERE (?3 is null or date_of_join >= ?3) limit ?1 offset ?2  ", nativeQuery = true)
    long latestJoinedUsersCount(int limit,int offset , String date);


    @Query(value = "SELECT distinct users.id ," +
            " users.username, users.name , users.email," +
            " users.phone," +
            " users.additional_phone," +
            " users.date_of_join," +
            "users.photo ,account_status " +
            " FROM users " +
            " WHERE date_of_join between  ?3 , ?4  limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object, Object>> filterUsersByDate(int limit,int offset , String sDate , String eDate);

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users " +
            " WHERE date_of_join between  ?3 , ?4 limit ?1 offset ?2  ", nativeQuery = true)
    long filterUsersByDateCount(int limit,int offset , String sDate , String eDate);


}
