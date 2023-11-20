package com.example.valoranttournament.Models;

public class Match_Model {
    String _MatchNo,_date, _time, _prizepool1, _prizepool2, _prizepool3, _tourformat, matchid, gametype;

    public Match_Model() {
    }

    public Match_Model(String _MatchNo, String _date, String _time, String _prizepool1, String _prizepool2, String _prizepool3, String _tourformat, String matchid, String gametype) {
        this._MatchNo = _MatchNo;
        this._date = _date;
        this._time = _time;
        this._prizepool1 = _prizepool1;
        this._prizepool2 = _prizepool2;
        this._prizepool3 = _prizepool3;
        this._tourformat = _tourformat;
        this.matchid = matchid;
        this.gametype = gametype;
    }

    public String getGametype() {
        return gametype;
    }

    public void setGametype(String gametype) {
        this.gametype = gametype;
    }

    public String get_MatchNo() {
        return _MatchNo;
    }

    public void set_MatchNo(String _MatchNo) {
        this._MatchNo = _MatchNo;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public String get_prizepool1() {
        return _prizepool1;
    }

    public void set_prizepool1(String _prizepool1) {
        this._prizepool1 = _prizepool1;
    }

    public String get_prizepool2() {
        return _prizepool2;
    }

    public void set_prizepool2(String _prizepool2) {
        this._prizepool2 = _prizepool2;
    }

    public String get_prizepool3() {
        return _prizepool3;
    }

    public void set_prizepool3(String _prizepool3) {
        this._prizepool3 = _prizepool3;
    }

    public String get_tourformat() {
        return _tourformat;
    }

    public void set_tourformat(String _tourformat) {
        this._tourformat = _tourformat;
    }

    public String getMatchid() {
        return matchid;
    }

    public void setMatchid(String matchid) {
        this.matchid = matchid;
    }
}
