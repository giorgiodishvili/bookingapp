package com.booking.book.service;

import com.booking.book.entity.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RoomService {

    public List<Room> getAllRooms();

    public Room getRoomById(Long id);

    public void saveRoom(Room room);

    public void deleteById(Long theId);

    public List<Room> searchBy(LocalDate startDate, LocalDate endDate);

    public List<Room> searchBy(Long categoryId);
    public List<Room> searchBy(Long categoryId,LocalDate startDate, LocalDate endDate);
}
