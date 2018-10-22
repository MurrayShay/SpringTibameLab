package com.tibame.domain;

public class MySQLConnetionInfo {

    private  String DRIVER_CLASS_NAME;
    private  String URL;
    private  String DATABASE_NAME;
    private  String USER;
    private  String PASSWORD;

//    static {
//        DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
//        URL = "jdbc:mysql://localhost:3306/";
//        DATABASE_NAME = "Hibernate";
//        USER = "root";
//        PASSWORD = "xxxxxxxx";
//    }

    public String getDRIVER_CLASS_NAME() {
        return DRIVER_CLASS_NAME;
    }

    public void setDRIVER_CLASS_NAME(String DRIVER_CLASS_NAME) {
        this.DRIVER_CLASS_NAME = DRIVER_CLASS_NAME;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDATABASE_NAME() {
        return DATABASE_NAME;
    }

    public void setDATABASE_NAME(String DATABASE_NAME) {
        this.DATABASE_NAME = DATABASE_NAME;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}