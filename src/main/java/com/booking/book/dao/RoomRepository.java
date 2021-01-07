package com.booking.book.dao;

import com.booking.book.entity.Room;
import com.booking.book.entity.RoomCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;


public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByTypeId(@Param("id") Long id);

    List<Room> findDistinctByIdNotIn(List<Long> ids);


}
