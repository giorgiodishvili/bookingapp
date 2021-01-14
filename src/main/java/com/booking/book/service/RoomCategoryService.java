package com.booking.book.service;

import com.booking.book.dao.RoomCategoryRepository;
import com.booking.book.entity.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomCategoryService {


    private final RoomCategoryRepository roomCategoryRepository;

    @Autowired
    public RoomCategoryService(RoomCategoryRepository roomCategoryRepository) {
        this.roomCategoryRepository = roomCategoryRepository;
    }

    public List<RoomCategory> roomCategoryList() {
        return roomCategoryRepository.findAll();
    }

}
