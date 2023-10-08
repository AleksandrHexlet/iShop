package com.edu.ishop.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_categories")
public class Category {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false,length = 3456)
    private String name;
    @Column(unique = true,nullable = false,columnDefinition = "TEXT NOT NULL")
    private String url;
    @ManyToOne
    private Category parent;


    public Category(String name, String url, Category parentOUT) {

        this.name = name;
        this.url = url;
        this.parent = parentOUT;
    }

    public Category() {
    }

    //Геттеры необходимы, чтобы приватные поля попали в JSON и в последствии в Базу данных.
    // Если свойства у entity класса приватные и нет геттеров, они не попадут в json.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Category getParent() {
        return parent;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", parent=" + parent +
                '}';
    }
}
