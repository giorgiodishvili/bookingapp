package com.booking.book.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room_category")
public class RoomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "room_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private Set<Room> rooms;

    public RoomCategory(Long id, String name, Set<Room> rooms) {
        this.id = id;
        this.name = name;
        this.rooms = rooms;
    }

    public RoomCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "RoomCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
