package com.booking.book.service;

import com.booking.book.dao.OrderRepository;
import com.booking.book.dao.RoomCategoryRepository;
import com.booking.book.dao.RoomRepository;
import com.booking.book.entity.Room;
import com.booking.book.entity.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RoomCategoryRepository roomCategoryRepository;


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

    @Override
    public List<Room> searchBy(LocalDate startDate, LocalDate endDate) {

        List<Room> roomNotAvailable = orderRepository.roomIdNotAvailable(startDate, endDate);
        System.out.println(roomNotAvailable);

        List<Long> ids = roomNotAvailable.stream()
                .map(Room::getId).collect(Collectors.toList());

        return roomRepository.findDistinctByIdNotIn(ids);
    }

    @Override
    public List<Room> searchBy(Long categoryId,LocalDate startDate, LocalDate endDate) {

        List<Room> roomNotAvailable = orderRepository.roomIdNotAvailable(startDate, endDate);
        System.out.println(roomNotAvailable);
        Optional<RoomCategory> byId = roomCategoryRepository.findById(categoryId);
        if(byId.isPresent()){
            List<Long> ids = roomNotAvailable.stream()
                    .filter(peopleWithSameId -> peopleWithSameId.getType() == byId.get())
                    .map(Room::getId).collect(Collectors.toList());

            return roomRepository.findDistinctByIdNotIn(ids);
        }

        return null;
    }

    @Override
    public List<Room> searchBy(Long id) {
        return roomRepository.findByTypeId(id);
    }
}
