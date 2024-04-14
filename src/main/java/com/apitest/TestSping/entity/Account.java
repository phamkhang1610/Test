package com.apitest.TestSping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")

public class Account {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private int ac_id;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private  String pasword;

    @Column(name="role")
    private boolean role;


}
