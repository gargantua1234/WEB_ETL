package com.arek.models;

import javax.persistence.*;

/**
 * Created by Arek on 09.01.2017.
 */

@Entity
@Table
public class Remark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
