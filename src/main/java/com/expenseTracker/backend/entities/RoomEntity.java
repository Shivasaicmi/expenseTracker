package com.expenseTracker.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="room")
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long roomId;

    @Column(name="title")
    private String title;

    @Column(name="totalPrice")
    private Double totalPrice;

    @Column(name="expenditure")
    private Double expenditure;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "roomId=" + roomId +
                ", title='" + title + '\'' +
                ", totalPrice=" + totalPrice +
                ", expenditure=" + expenditure +
                '}';
    }
}


// roomid   title   total_price    txnid

// gid   title   total_price     ownerId    txid

// userId    rid     gid


/*
*
* select * from relation where userid=3 and gid Not null
* select * from users where gid=3 and u.userid = rel.userId
*
*
* */


// fetch the rooms associated
// select * where b.userid = 3 AND roomId not null
// select * from transactions where roomid = 3 and r.txid=t.txid

// add a transaction under a room
/*
*
* insert into transactions
* insert into rooms
*
* */

// show the all transactions of a room
