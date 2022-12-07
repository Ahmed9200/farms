package com.example.authentication.repositories.complaintsRepo;


import com.example.authentication.models.complaintsModels.ComplaintsResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintsResponsesRepository extends JpaRepository<ComplaintsResponses, Integer> {

    List<ComplaintsResponses> findAllByComplainId(int complainId);
    List<ComplaintsResponses> findAllByComplainIdAndComplaintMessageId(int cId , int cMId);



    @Query(value = "SELECT * from complaints_responses where complain_id =?1  " +
            " limit ?2 offset ?3  ", nativeQuery = true)
    List<ComplaintsResponses> findAllByComplaintIdWithPagination(int cId  , int limit,int offset);


    @Query(value = "SELECT * from complaints_responses where complain_id =?1 and complaint_message_id = ?2   " +
            " limit ?3 offset ?4  ", nativeQuery = true)
    List<ComplaintsResponses> findAllByComplaintIdAndComplaintMessageIdWithPagination(int cId , int cmId , int limit,int offset);



    @Query(value = "SELECT distinct c.*  " +
            " FROM complaints_responses c order by c.created_date asc " +
            " limit ?1 offset ?2  ", nativeQuery = true)
    List<ComplaintsResponses> getAllComplainsByCreatedAsc(int limit,int offset);

    @Query(value = "SELECT count(distinct c.id) " +
            " FROM complaints_responses c " +
            " order by c.created_date asc limit ?1 offset ?2  ", nativeQuery = true)
    long getAllComplainsByCreatedAscAscCount(int limit,int offset);

}
