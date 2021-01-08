package com.booking.book.controller;

import com.booking.book.entity.Room;
import com.booking.book.entity.RoomCategory;
import com.booking.book.service.RoomCategoryService;
import com.booking.book.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomCategoryService roomCategoryService;

    @GetMapping("/list")
    public String listRooms(Model theModel) {

        // get employees from db
        List<Room> theRooms = roomService.getAllRooms();
        List<RoomCategory> roomCategories = roomCategoryService.roomCategoryList();
        theModel.addAttribute("categories", roomCategories);
        // add to the spring model
        theModel.addAttribute("rooms", theRooms);

        return "rooms/room-list";
    }


//    @PostMapping("/save")
//    public String save(Room room,Model theModel) {
//        List<Room> rooms = roomRepository.canBeAdded(room.getType(), room.getStartDate(), room.getEndDate());
//
//        if(rooms.size() == 0){
//            theModel.addAttribute("room", room);
//            return "result";
//        }
//        else{
//            throw new RuntimeException("Cant be added");
//        }
//    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "category", required = false) Long categoryId,
                         @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                         @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                         Model theModel) {

        if (startDate != null && endDate != null && categoryId != null) {
            List<Room> o = roomService.searchBy(categoryId, startDate, endDate);
            List<RoomCategory> roomCategories = roomCategoryService.roomCategoryList();
            theModel.addAttribute("categories", roomCategories);
            theModel.addAttribute("rooms", o);

            // send to list-employees
            return "rooms/room-list";
        } else if (categoryId != null) {


            // add to the spring model
            List<Room> o = roomService.searchBy(categoryId);
            List<RoomCategory> roomCategories = roomCategoryService.roomCategoryList();
            theModel.addAttribute("categories", roomCategories);
            theModel.addAttribute("rooms", o);

            // send to list-employees
            return "rooms/room-list";

        } else if (startDate != null && endDate != null) {
            // else, search by first name and last name
            List<Room> theRooms =
                    roomService.searchBy(startDate, endDate);
            List<RoomCategory> roomCategories = roomCategoryService.roomCategoryList();

            theModel.addAttribute("categories", roomCategories);
            theModel.addAttribute("rooms", theRooms);

            // send to list-employees
            return "rooms/room-list";
        } else {
            return "redirect:/rooms/list";
        }

    }
}
