package com.booking.book.service;

import com.booking.book.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<Room> getAllRooms();

    Room getRoomById(Long id);

    void saveRoom(Room room);

    void deleteById(Long theId);

    List<Room> searchBy(LocalDate startDate, LocalDate endDate);

    List<Room> searchBy(Long categoryId);

    List<Room> searchBy(Long categoryId, LocalDate startDate, LocalDate endDate);
}
