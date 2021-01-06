package com.booking.book.service;

import com.booking.book.entity.Room;

import java.util.List;

public interface RoomService {

    public List<Room> getAllRooms();

    public Room getRoomById(Long id);

    public void saveRoom(Room room);

    public void deleteById(Long theId);


}
