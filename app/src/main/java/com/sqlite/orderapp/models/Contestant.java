package com.sqlite.orderapp.models;

public class Contestant {

    String ContestantId;
    String ContestantName;
    String ContestantIdNo;
    String ContestantPhone;
    String ContestantEmail;
    String PoliticalSeatId;
    String Region;
    String ContestantDescription;

    public Contestant() {
    }



    public Contestant(String contestantName, String contestantIdNo, String contestantPhone, String contestantEmail, String politicalSeatId, String region, String contestantDescription) {
        ContestantName = contestantName;
        ContestantIdNo = contestantIdNo;
        ContestantPhone = contestantPhone;
        ContestantEmail = contestantEmail;
        PoliticalSeatId = politicalSeatId;
        Region = region;
        ContestantDescription = contestantDescription;
    }

    public String getContestantDescription() {
        return ContestantDescription;
    }

    public void setContestantDescription(String contestantDescription) {
        ContestantDescription = contestantDescription;
    }

    public String getContestantId() {
        return ContestantId;
    }

    public void setContestantId(String contestantId) {
        ContestantId = contestantId;
    }

    public String getContestantName() {
        return ContestantName;
    }

    public void setContestantName(String contestantName) {
        ContestantName = contestantName;
    }

    public String getContestantIdNo() {
        return ContestantIdNo;
    }

    public void setContestantIdNo(String contestantIdNo) {
        ContestantIdNo = contestantIdNo;
    }

    public String getContestantPhone() {
        return ContestantPhone;
    }

    public void setContestantPhone(String contestantPhone) {
        ContestantPhone = contestantPhone;
    }

    public String getContestantEmail() {
        return ContestantEmail;
    }

    public void setContestantEmail(String contestantEmail) {
        ContestantEmail = contestantEmail;
    }

    public String getPoliticalSeatId() {
        return PoliticalSeatId;
    }

    public void setPoliticalSeatId(String politicalSeatId) {
        PoliticalSeatId = politicalSeatId;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }
}
