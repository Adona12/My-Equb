package com.example.myequb;

public class history_holder {
    private String Date;
    private String Winner;
    private String Cycle;

    public String getDate() {
        return Date;
    }



    public String getWinner() {
        return Winner;
    }
    public String getCycle() {
        return Cycle;
    }



    public history_holder(String date, String winner) {
        Date = date;
        Winner = winner;
    }
    public history_holder(String cycle) {
       Cycle = cycle;

    }
}
