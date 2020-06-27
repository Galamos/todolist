package com.example.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Items")
public class Item implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ITEM_DESCRIPTION")
    private String description;
    @Column(name = "STATUS")
    private Boolean status;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (description == null){
            if (other.description != null)
                return false;
        }else if(!description.equals(other.description))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Item [id = " + id + "/nDescription: " + description + "/nStatus: " + status + "]";
    }
}
