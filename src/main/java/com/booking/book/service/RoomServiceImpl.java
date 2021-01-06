package com.booking.book.service;

import com.booking.book.dao.RoomRepository;
import com.booking.book.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
    @Override
    public Room getRoomById(Long id){
        Optional<Room> byId = roomRepository.findById(id);
        if(byId.isEmpty()){
            throw new RuntimeException("Room not found");
        }else{
            return byId.get();
        }
    }
    @Override
    public void saveRoom(Room room){
        roomRepository.save(room);
    }

    @Override
    public void deleteById(Long theId) {
        roomRepository.deleteById(theId);
    }

}
