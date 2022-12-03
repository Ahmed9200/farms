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

    Users findByUsernameAndPassword(String username, String PW);
    Users findByPhone(String phone);
    Users findByUsername(String username);
    Users findById(int id);
    List<Users> findAllByIdIn(List<Integer> ids);

    @Query(value = "SELECT *  FROM users  WHERE phone like ?1 and password like ?2 ", nativeQuery = true)
    Map<Object, Object> login(String phone, String PW);



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
    @Query(value = "UPDATE users SET account_status = 'delete' , username= concat('001_',phone) , phone = concat('001_',phone) " +
            " WHERE id=?1 ", nativeQuery = true)
    void deleteAccount(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET account_status = 'stop' , username= concat('002_',phone) , phone = concat('002_',phone) " +
            " WHERE id=?1 ", nativeQuery = true)
    void stopAccount(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET account_status = 'active' , username=SUBSTRING(phone , 5,length(phone)) " +
            ", phone = SUBSTRING(phone , 5,length (phone)) " +
            " WHERE id=?1 ", nativeQuery = true)
    void activeAccount(int userId);



    @Query(value = "SELECT distinct u.id , u.name , u.phone , u.additional_phone as 'additionalPhone' " +
            ", u.email , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin' , u.photo " +
            " FROM users u order by date_of_join asc " +
            " limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> getAllUsersAsc(int limit,int offset);

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users " +
            " order by date_of_join asc limit ?1 offset ?2  ", nativeQuery = true)
    long getAllUsersAscCount(int limit,int offset);

    @Query(value = "SELECT distinct u.id , u.name , u.phone , u.additional_phone as 'additionalPhone' " +
            ", u.email , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin' , u.photo " +
            " FROM users u " +
            " WHERE (?3 is null or date_of_join >= ?3) limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> latestJoinedUsers(int limit,int offset , Object date);

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users " +
            " WHERE (?3 is null or date_of_join >= ?3) limit ?1 offset ?2  ", nativeQuery = true)
    long latestJoinedUsersCount(int limit,int offset , Object date);

    @Query(value = "SELECT distinct u.id , u.name , u.phone , u.additional_phone as 'additionalPhone' " +
            ", u.email , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin' , u.photo " +
            " FROM users u " +
            " WHERE date_of_join between  ?3 and ?4  limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> filterUsersByDate(int limit,int offset , Object sDate , Object eDate);

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users " +
            " WHERE date_of_join between  ?3 and ?4 limit ?1 offset ?2  ", nativeQuery = true)
    long filterUsersByDateCount(int limit,int offset , Object sDate , Object eDate);


    @Query(value = "SELECT distinct u.id , u.name , u.phone , u.additional_phone as 'additionalPhone' " +
            ", u.email , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin' , u.photo " +
            " FROM users u " +
            " WHERE name like  ?3   limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> filterUsersByNameLike(int limit,int offset , String name );

    @Query(value = "SELECT count(distinct users.id) " +
            " FROM users " +
            " WHERE name like  ?3  limit ?1 offset ?2  ", nativeQuery = true)
    long filterUsersByNameLikeCount(int limit,int offset , String name );

}
