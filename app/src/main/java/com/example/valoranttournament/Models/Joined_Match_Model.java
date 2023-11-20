package com.example.valoranttournament.Models;
public class Joined_Match_Model {
    String joinedname, joinid, userid, joinstatus;

    public Joined_Match_Model() {
    }

    public Joined_Match_Model(String joinedname, String joinid, String userid, String joinstatus) {
        this.joinedname = joinedname;
        this.joinid = joinid;
        this.userid = userid;
        this.joinstatus = joinstatus;
    }

    public String getJoinedname() {
        return joinedname;
    }

    public void setJoinedname(String joinedname) {
        this.joinedname = joinedname;
    }

    public String getJoinid() {
        return joinid;
    }

    public void setJoinid(String joinid) {
        this.joinid = joinid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getJoinstatus() {
        return joinstatus;
    }

    public void setJoinstatus(String joinstatus) {
        this.joinstatus = joinstatus;
    }
}
