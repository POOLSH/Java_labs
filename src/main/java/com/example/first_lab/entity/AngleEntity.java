package com.example.first_lab.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AngleEntity {
    @Id
    private Double degree;

    private Double rads;

    public AngleEntity() {
    }

    public AngleEntity(Double degree, Double rads) {
        this.degree = degree;
        this.rads = rads;
    }

    public Double getDegree() {
        return degree;
    }

    public void setDegree(Double degree) {
        this.degree = degree;
    }

    public Double getRads() {
        return rads;
    }

    public void setRads(Double rads) {
        this.rads = rads;
    }
}