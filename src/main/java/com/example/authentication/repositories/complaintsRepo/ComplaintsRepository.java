package com.example.authentication.repositories.complaintsRepo;

import com.example.authentication.models.complaintsModels.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface ComplaintsRepository extends JpaRepository<Complaints, Integer> {

    List<Complaints> findAllByUserId(String phone);
    Complaints findById(int id);



    @Modifying
    @Transactional
    @Query(value = "UPDATE Complaints SET status = 'close' , close_date = current_timestamp WHERE id=?1 ", nativeQuery = true)
    void updateStatusToClose(int complainId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE Complaints SET last_update_date = current_timestamp WHERE id=?1 ", nativeQuery = true)
    void updateComplainLastUpdateDateByComplainId(int complainId);


    @Query(value = "SELECT distinct c.*  " +
            " FROM Complaints c order by c.created_date asc " +
            " limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> getAllComplainsByCreatedAsc(int limit,int offset);

    @Query(value = "SELECT count(distinct c.id) " +
            " FROM Complaints c " +
            " order by c.created_date asc limit ?1 offset ?2  ", nativeQuery = true)
    long getAllComplainsByCreatedAscAscCount(int limit,int offset);



    @Query(value = "SELECT distinct c.*  " +
            " FROM Complaints c order by c.last_update_date asc " +
            " limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> getAllComplainsByLastUpdateDateAsc(int limit,int offset);

    @Query(value = "SELECT count(distinct c.id) " +
            " FROM Complaints c " +
            " order by c.last_update_date asc limit ?1 offset ?2  ", nativeQuery = true)
    long getAllComplainsByLastUpdateDateAscCount(int limit,int offset);



    @Query(value = "SELECT distinct c.*  " +
            " FROM Complaints c  " +
            "where c.email like ?3" +
            " order by c.last_update_date asc limit ?1 offset ?2  ", nativeQuery = true)
    List<Map<Object,Object>> getAllComplainsByEmailLike(int limit,int offset , String email);

    @Query(value = "SELECT count(distinct c.id) " +
            " FROM Complaints c " +
            "where c.email like ?3 " +
            " order by c.last_update_date asc limit ?1 offset ?2  ", nativeQuery = true)
    long getAllComplainsByEmailLikeCount(int limit,int offset , String email);


}
