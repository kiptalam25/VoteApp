package com.sqlite.orderapp.models;

public class Votes {
    String VoteId;
    String StationId;
    String AgentId;
    String ContestantId;

    public Votes() {
    }

    public Votes(String voteId, String stationId, String agentId, String contestantId) {
        VoteId = voteId;
        StationId = stationId;
        AgentId = agentId;
        ContestantId = contestantId;
    }
    public Votes(String stationId, String agentId, String contestantId) {
        StationId = stationId;
        AgentId = agentId;
        ContestantId = contestantId;
    }

    public String getVoteId() {
        return VoteId;
    }

    public void setVoteId(String voteId) {
        VoteId = voteId;
    }

    public String getStationId() {
        return StationId;
    }

    public void setStationId(String stationId) {
        StationId = stationId;
    }

    public String getAgentId() {
        return AgentId;
    }

    public void setAgentId(String agentId) {
        AgentId = agentId;
    }

    public String getContestantId() {
        return ContestantId;
    }

    public void setContestantId(String contestantId) {
        ContestantId = contestantId;
    }
}
