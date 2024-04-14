package com.apitest.TestSping.database;

import lombok.Data;

import java.sql.Date;

@Data
public class DTOStaff {
    private int id;
    private String nameStaff;
    private Date birth;
    private String cmnd;
    private String address;
    private String phone;
    private String image;
}
