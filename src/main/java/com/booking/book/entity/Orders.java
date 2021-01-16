package com.booking.book.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Orders {
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
    private LocalDate startDateTime;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDateTime;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "rrn")
    private String rrn;

    @Column(name = "result_code")
    private String resultCode;

    @Column(name = "masked_card")
    private String maskedCard;

    public Orders() {
    }

    public Orders(Long id, String customer, boolean active, Room roomId, LocalDate startDateTime, LocalDate endDateTime) {
        this.id = id;
        this.customer = customer;
        this.active = active;
        this.roomId = roomId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Orders(String customer, boolean active, Room roomId, LocalDate startDateTime, LocalDate endDateTime) {
        this.customer = customer;
        this.active = active;
        this.roomId = roomId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Orders(Long id, String customer, boolean active, Room roomId, LocalDate startDateTime, LocalDate endDateTime, Long amount, String transactionId) {
        this.id = id;
        this.customer = customer;
        this.active = active;
        this.roomId = roomId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.amount = amount;
        this.transactionId = transactionId;
    }

    public Orders(Long id, String customer, boolean active, Room roomId, LocalDate startDateTime, LocalDate endDateTime, Long amount, String transactionId, String rrn, String resultCode, String maskedCard) {
        this.id = id;
        this.customer = customer;
        this.active = active;
        this.roomId = roomId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.amount = amount;
        this.transactionId = transactionId;
        this.rrn = rrn;
        this.resultCode = resultCode;
        this.maskedCard = maskedCard;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMaskedCard() {
        return maskedCard;
    }

    public void setMaskedCard(String maskedCard) {
        this.maskedCard = maskedCard;
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
