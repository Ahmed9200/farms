package com.example.authentication.repositories.usersRepo;

import com.example.authentication.models.AdminUsers;
import com.example.authentication.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminsUsersRepository extends JpaRepository<AdminUsers, Integer> {


    AdminUsers findByEmail(String email);
    AdminUsers findByEmailAndAccountStatus(String email,String accountStatus);
    AdminUsers findById(int id);



    @Modifying
    @Transactional
    @Query(value = "UPDATE admins SET password = ?2 WHERE email=?1 ", nativeQuery = true)
    void updateUserPasswordByEmail(String email, String password);


    @Modifying
    @Transactional
    @Query(value = "UPDATE admins SET email_status = ?1 WHERE id=?2 ", nativeQuery = true)
    void updateEmailStatus(String emailStatus, String userId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE admins SET email = ?1 WHERE id=?2 ", nativeQuery = true)
    void updateEmail(String email, int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE admins SET password = ?2 WHERE id=?1 ", nativeQuery = true)
    void updateUserPasswordById(int userId, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE admins SET account_status = 'delete' " +
            " WHERE id=?1 ", nativeQuery = true)
    void deleteAccount(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE admins SET account_status = 'stop' " +
            " WHERE id=?1 ", nativeQuery = true)
    void stopAccount(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE admins SET account_status = 'active' " +
            " WHERE id=?1 ", nativeQuery = true)
    void activeAccount(int userId);



    @Query(value = "SELECT distinct u.id    " +
            ", u.email , u.email_status as 'emailStatus', u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin'  " +
            " FROM admins u where account_status in (?3) order by date_of_join asc " +
            " limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> getAllAdminUsersAsc(int limit,int offset , Object status);

    @Query(value = "SELECT count(distinct id) " +
            " FROM admins  where account_status in (?3) " +
            " order by date_of_join desc  limit ?1 offset ?2  ", nativeQuery = true)
    long getAllAdminUsersAscCount(int limit,int offset , Object status);

    @Query(value = "SELECT distinct u.id  " +
            ", u.email , u.email_status as 'emailStatus' , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin'  " +
            " FROM users u " +
            " WHERE (?3 is null or date_of_join >= ?3)  and account_status in (?4) limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> latestJoinedAdminUsers(int limit,int offset , Object date , Object status);

    @Query(value = "SELECT count(distinct a.id) " +
            " FROM admins a " +
            " WHERE (?3 is null or date_of_join >= ?3) limit ?1 offset ?2  ", nativeQuery = true)
    long latestJoinedAdminUsersCount(int limit,int offset , Object date);

    @Query(value = "SELECT distinct u.id  " +
            ", u.email , u.email_status as 'emailStatus' , u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin'  " +
            " FROM admins u " +
            " WHERE date_of_join between  ?3 and ?4 and account_status in (?5)  limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> filterAdminUsersByDate(int limit,int offset , Object sDate , Object eDate , Object status);

    @Query(value = "SELECT count(distinct id) " +
            " FROM admins " +
            " WHERE date_of_join between  ?3 and ?4  and account_status in (?5) limit ?1 offset ?2  ", nativeQuery = true)
    long filterAdminUsersByDateCount(int limit,int offset , Object sDate , Object eDate , Object status);


    @Query(value = "SELECT distinct u.id  " +
            ", u.email , u.email_status as 'emailStatus', u.account_status as 'accountStatus' ,u.date_of_join as 'dateOfJoin' " +
            " FROM admins u " +
            " WHERE email like  ?3 and account_status in (?4)   limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> filterAdminUsersByEmailLikeAndStatusIn(int limit,int offset , String name , Object status);

    @Query(value = "SELECT count(distinct id) " +
            " FROM admins " +
            " WHERE email like  ?3 and account_status in (?4)  limit ?1 offset ?2  ", nativeQuery = true)
    long filterAdminUsersByEmailLikeCount(int limit,int offset , String name , Object status);

}
