package com.expenseTracker.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_rooms")
public class UserRoomsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_room_id")
    private Long id;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name="user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",updatable = false,insertable = false)
    private RoomEntity room;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

//    public UserEntity getUser() {
//        return user;
//    }
//
//    public void setUser(UserEntity user) {
//        this.user = user;
//    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "UserRoomsEntity{" +
                "roomId=" + roomId +
                ", userId=" + userId +
                ", user=" + user +
                ", room=" + room +
                '}';
    }
}
