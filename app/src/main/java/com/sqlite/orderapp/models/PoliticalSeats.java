package com.sqlite.orderapp.models;

public class PoliticalSeats {

    String SeatId;
    String SeatName;
    String SeatInfo;

    public PoliticalSeats() {
    }

    public PoliticalSeats(String seatId, String seatName, String seatInfo) {
        SeatId = seatId;
        SeatName = seatName;
        SeatInfo = seatInfo;
    }

    public PoliticalSeats(String seatName, String seatInfo) {
        SeatName = seatName;
        SeatInfo = seatInfo;
    }

    public String getSeatId() {
        return SeatId;
    }

    public void setSeatId(String seatId) {
        SeatId = seatId;
    }

    public String getSeatName() {
        return SeatName;
    }

    public void setSeatName(String seatName) {
        SeatName = seatName;
    }

    public String getSeatInfo() {
        return SeatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        SeatInfo = seatInfo;
    }
}
