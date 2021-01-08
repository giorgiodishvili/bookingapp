package com.booking.book.dao;

import com.booking.book.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByTypeId(@Param("id") Long id);

    List<Room> findDistinctByIdNotIn(List<Long> ids);


}
