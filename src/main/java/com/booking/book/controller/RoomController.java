package com.booking.book.controller;

import com.booking.book.dao.RoomRepository;
import com.booking.book.entity.Room;
import com.booking.book.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/list")
    public String listRooms(Model theModel) {

        // get employees from db
        List<Room> theEmployees = roomService.getAllRooms();

        // add to the spring model
        theModel.addAttribute("rooms", theEmployees);

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
}
