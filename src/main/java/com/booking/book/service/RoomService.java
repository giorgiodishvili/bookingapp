package com.booking.book.service;

import com.booking.book.dao.OrderRepository;
import com.booking.book.dao.RoomCategoryRepository;
import com.booking.book.dao.RoomRepository;
import com.booking.book.entity.Room;
import com.booking.book.entity.RoomCategory;
import com.booking.book.exception.RoomCategoryIdNotFoundException;
import com.booking.book.exception.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    private final OrderRepository orderRepository;

    private final RoomCategoryRepository roomCategoryRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, OrderRepository orderRepository, RoomCategoryRepository roomCategoryRepository) {
        this.roomRepository = roomRepository;
        this.orderRepository = orderRepository;
        this.roomCategoryRepository = roomCategoryRepository;
    }

    //საჭიროა ისე ექსეფშენების სროლა ? ფრონზე რო null-ზე შემოჭმდეს ხო არ ჯობია ?
    // და არ იქნება ამ დამატებითი მეთოდების დაწერა საჭირო
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(RoomNotFoundException::new);
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteById(Long theId) {
        roomRepository.deleteById(theId);
    }

    public List<Room> searchBy(LocalDate startDate, LocalDate endDate) {

        List<Room> roomNotAvailable = orderRepository.roomIdNotAvailable(startDate, endDate);

        List<Long> ids = roomNotAvailable.stream()
                .map(Room::getId).collect(Collectors.toList());

        return roomRepository.findDistinctByIdNotIn(ids);
    }

    public List<Room> searchBy(Long categoryId, LocalDate startDate, LocalDate endDate) {

        List<Room> roomNotAvailable = orderRepository.roomIdNotAvailable(startDate, endDate);

        RoomCategory roomCategory = roomCategoryRepository.findById(categoryId).orElseThrow(RoomCategoryIdNotFoundException::new);

        List<Long> ids = roomNotAvailable.stream()
                .filter(peopleWithSameId -> peopleWithSameId.getType() == roomCategory)
                .map(Room::getId).collect(Collectors.toList());

        return roomRepository.findDistinctByIdNotIn(ids);

    }

    public List<Room> searchBy(Long id) {
        return roomRepository.findByTypeId(id);
    }
}
