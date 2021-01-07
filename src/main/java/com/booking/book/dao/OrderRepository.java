package com.booking.book.dao;

import com.booking.book.entity.Order;
import com.booking.book.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    //TODO startDate meti unda iyos dgevandel dgeze axla erors agdebs radgan current_date unda sheecvalos formati. Possible sollution = current_date parametrad  migeba

    @Query("from Order a where a.active = true and a.roomId = :roomId and  :startDate <= a.endDateTime and :endDate >= a.startDateTime")
    List<Order> isTimeAvailableByRoomId(@Param("roomId") Room id
            , @Param("startDate") LocalDate startDate
            , @Param("endDate") LocalDate endDate
                                        //        , @Param("curr_date") LocalDate curr_date
    );

    @Query("select a.roomId from Order a where a.active = true and  :startDate <= a.endDateTime and :endDate >= a.startDateTime")
    List<Room> roomIdNotAvailable(
                @Param("startDate") LocalDate startDate
            , @Param("endDate") LocalDate endDate
    );



}
//findByActiveIsTrueAndRoomIdAndStartDateTimeIsGreaterThanAnd