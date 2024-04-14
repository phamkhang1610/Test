package com.apitest.TestSping.database;

import lombok.Data;

@Data
public class DTOAccount {
    private int ac_id;
    private int staff_id;
    private String username;
    private  String pasword;
    private boolean role;
}
