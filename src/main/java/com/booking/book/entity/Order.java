package com.booking.book.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="customer")
    private String customer;

    @Column(name="active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room roomId;

    @Column(name = "date_start")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDateTime startDateTime;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDateTime endDateTime;

    public Order() {
    }

    public Order(Long id, String customer, boolean active, Room roomId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.id = id;
        this.customer = customer;
        this.active = active;
        this.roomId = roomId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Order(String customer, boolean active, Room roomId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.customer = customer;
        this.active = active;
        this.roomId = roomId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
               // ", room= '" + roomId.getId() +
                ", active=" + active +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                '}';
    }
}
