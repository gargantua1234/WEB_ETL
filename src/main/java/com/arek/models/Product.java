package com.arek.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */

@Entity
@Table
public class Product {
    @Id
    private int id;
    private String type;
    private String model;
    private String brand;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product", nullable = false)
    private List<Remark> remarks;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product", nullable = false)
    private List<Comment> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remark> remarks) {
        this.remarks = remarks;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


}
