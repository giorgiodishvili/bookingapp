package com.booking.book.dao;

import com.booking.book.entity.Order;
import com.booking.book.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DATE;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //TODO startDate meti unda iyos dgevandel dgeze axla erors agdebs radgan current_date unda sheecvalos formati. Possible sollution = current_date parametrad  migeba
//:startDate > :curr_date and
    @Query("from Order a where a.active = true and a.roomId = :typeId and  :startDate <= a.endDateTime and :endDate >= a.startDateTime")
    List<Order> canBeAdded(@Param("typeId") Room id
            , @Param("startDate") LocalDateTime startDate
            , @Param("endDate") LocalDateTime endDate
    //        , @Param("curr_date") LocalDate curr_date
    );
}
//findByActiveIsTrueAndRoomIdAndStartDateTimeIsGreaterThanAnd