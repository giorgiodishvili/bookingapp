package com.booking.book.dao;

import com.booking.book.entity.Room;
import com.booking.book.entity.RoomCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {

    Page<Room> findByTypeId(@Param("id") Long id, Pageable pageable);

    //if this select returns data then room cant be added
//    @Query("select a from Room a where a.type = :typeId and :startDate <= a.endDate and :endDate >= a.startDate")
//    List<Room> canBeAdded(@Param("typeId") RoomCategory id
//                          , @Param("startDate") String startDate
//                          , @Param("endDate") String endDate
//                          );
}
