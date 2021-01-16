package com.booking.book.controller;

import com.booking.book.entity.Room;
import com.booking.book.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/rooms")
public class RoomController {


    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //@RequestParam for URL AND @Param for @Query
    @GetMapping("/search")
    public List<Room> search(@RequestParam(value = "category", required = false) Long categoryId,
                             @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                             @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {

        //search by all params
        if (startDate != null && endDate != null && categoryId != null) {

            return roomService.searchBy(categoryId, startDate, endDate);

        }
        //search by roomCategoryId
        else if (categoryId != null && startDate == null && endDate == null) {

            return roomService.searchBy(categoryId);

        }
        //search by startDate And EndDate
        else if (startDate != null && endDate != null) {
            // else, search by start Date and end Date
            return roomService.searchBy(startDate, endDate);

        } else {
            return null;
        }

    }
}
