package com.example.quanlydanhba;

import androidx.annotation.NonNull;

public class nhanvien {
    public nhanvien(int id, int madv, String name, String email, String chucvu, String sdt, String anhdd) {
        this.id = id;
        this.madv = madv;
        this.name = name;
        this.email = email;
        this.chucvu = chucvu;
        this.sdt = sdt;
        this.anhdd = anhdd;
    }

    private int id,madv;
    private String name,email,chucvu,sdt,anhdd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMadv() {
        return madv;
    }

    public void setMadv(int madv) {
        this.madv = madv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAnhdd() {
        return anhdd;
    }

    public void setAnhdd(String anhdd) {
        this.anhdd = anhdd;
    }

    @NonNull
    @Override
    public String toString() {
        return name+" - "+chucvu+ " - ";
    }
}
