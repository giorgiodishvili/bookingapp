package com.booking.book.dao;

import com.booking.book.entity.Order;
import com.booking.book.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    //if nothing return then time is available
    @Query("from Order a where a.active = true and a.roomId = :roomId and  :startDate <= a.endDateTime and :endDate >= a.startDateTime")
    List<Order> isTimeAvailableByRoomId(@Param("roomId") Room id
            , @Param("startDate") LocalDate startDate
            , @Param("endDate") LocalDate endDate
                                        //        , @Param("curr_date") LocalDate curr_date
    );

    //returns rooms which arent available
    @Query("select a.roomId from Order a where a.active = true and  :startDate <= a.endDateTime and :endDate >= a.startDateTime")
    List<Room> roomIdNotAvailable(
            @Param("startDate") LocalDate startDate
            , @Param("endDate") LocalDate endDate
    );

    //finds order by tran id (for future USE)
    List<Order> findByTransactionId(@Param("transactionId") String id);
}