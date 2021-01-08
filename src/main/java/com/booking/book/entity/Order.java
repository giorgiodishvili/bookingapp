package com.booking.book.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer")
    private String customer;

    @Column(name = "active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room roomId;

    @Column(name = "date_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @NotBlank(message="Arrival Date is Must")
//    @NotNull
    private LocalDate startDateTime;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @NotBlank(message="Departure Date is Must")
//    @NotNull
    private LocalDate endDateTime;

    public Order() {
    }

    public Order(Long id, String customer, boolean active, Room roomId, LocalDate startDateTime, LocalDate endDateTime) {
        this.id = id;
        this.customer = customer;
        this.active = active;
        this.roomId = roomId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Order(String customer, boolean active, Room roomId, LocalDate startDateTime, LocalDate endDateTime) {
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

    //    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    public LocalDate getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDate startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDate getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDate endDateTime) {
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
