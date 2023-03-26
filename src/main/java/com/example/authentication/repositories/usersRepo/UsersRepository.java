package com.example.authentication.repositories.usersRepo;

import com.example.authentication.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {


    Users findByPhoneAndAccountStatus(String phone,String accountStatus);
    Users findByPhone(String phone);
    Users findByUsernameAndAccountStatus(String username,String accountStatus);
    Users findById(int id);


    @Query(value = "SELECT u.photo  FROM users u  WHERE u.id=?1 ;", nativeQuery = true)
    String userPhoto(int userId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = ?2 WHERE username=?1 ", nativeQuery = true)
    void updateUserPasswordByUsername(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET photo = ?1 WHERE id=?2 ", nativeQuery = true)
    void updatePhoto(String photo, int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET name = ?1 WHERE id=?2 ", nativeQuery = true)
    void updateName(String name, int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET phone = ?1 , username = phone WHERE id=?2 ", nativeQuery = true)
    void updatePhone(String phone, int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET additional_phone = ?1 WHERE id=?2 ", nativeQuery = true)
    void updateAdditionalPhone(String phone2, int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET email = ?1 WHERE id=?2 ", nativeQuery = true)
    void updateEmail(String email, int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = ?2 WHERE id=?1 ", nativeQuery = true)
    void updateUserPasswordById(int userId, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET account_status = 'delete' " +
            " WHERE id=?1 ", nativeQuery = true)
    void deleteAccount(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET account_status = 'stop' " +
            " WHERE id=?1 ", nativeQuery = true)
    void stopAccount(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET account_status = 'active' " +
            " WHERE id=?1 ", nativeQuery = true)
    void activeAccount(int userId);

    @Query(value = "SELECT distinct u.id , u.name , u.phone , u.additional_phone as 'additionalPhone' " +
            ", u.email , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin'  " +
            " FROM users u where id = ?1  " , nativeQuery = true)
    Map<Object,Object> lightUser(int id);

    @Query(value = "SELECT distinct u.id , u.name , u.phone , u.additional_phone as 'additionalPhone' " +
            ", u.email , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin'  " +
            " FROM users u where account_status in (?3) order by date_of_join asc " +
            " limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> getAllUsersAsc(int limit,int offset , Object status);

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users where account_status in (?3) " +
            " order by date_of_join desc  limit ?1 offset ?2  ", nativeQuery = true)
    long getAllUsersAscCount(int limit,int offset , Object status);

    @Query(value = "SELECT distinct u.id , u.name , u.phone , u.additional_phone as 'additionalPhone' " +
            ", u.email , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin'  " +
            " FROM users u " +
            " WHERE (?3 is null or date_of_join >= ?3)  and account_status in (?4) limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> latestJoinedUsers(int limit,int offset , Object date , Object status);

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users " +
            " WHERE (?3 is null or date_of_join >= ?3) limit ?1 offset ?2  ", nativeQuery = true)
    long latestJoinedUsersCount(int limit,int offset , Object date);

    @Query(value = "SELECT distinct u.id , u.name , u.phone , u.additional_phone as 'additionalPhone' " +
            ", u.email , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin'  " +
            " FROM users u " +
            " WHERE date_of_join between  ?3 and ?4 and account_status in (?5)  limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> filterUsersByDate(int limit,int offset , Object sDate , Object eDate , Object status);

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users " +
            " WHERE date_of_join between  ?3 and ?4  and account_status in (?5) limit ?1 offset ?2  ", nativeQuery = true)
    long filterUsersByDateCount(int limit,int offset , Object sDate , Object eDate , Object status);


    @Query(value = "SELECT distinct u.id , u.name , u.phone , u.additional_phone as 'additionalPhone' " +
            ", u.email , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin' " +
            " FROM users u " +
            " WHERE name like  ?3 and account_status in (?4)   limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> filterUsersByNameLikeAndStatusIn(int limit,int offset , String name , Object status);

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users " +
            " WHERE name like  ?3 and account_status in (?4)  limit ?1 offset ?2  ", nativeQuery = true)
    long filterUsersByNameLikeCount(int limit,int offset , String name , Object status);

}
