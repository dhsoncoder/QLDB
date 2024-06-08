package com.example.quanlydanhba;

public class donvi {
    private int madv,madvcha;
    private String tendv,email,website,logo,diachi,sdt;

    public donvi(int madv, String tendv, String email, String website, String logo, String diachi, String sdt, int madvcha) {
        this.madv = madv;
        this.tendv = tendv;
        this.email = email;
        this.website = website;
        this.logo = logo;
        this.diachi = diachi;
        this.sdt = sdt;
        this.madvcha = madvcha;
    }

    public int getMadv() {
        return madv;
    }

    public void setMadv(int madv) {
        this.madv = madv;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getMadvcha() {
        return madvcha;
    }

    public void setMadvcha(int madvcha) {
        this.madvcha = madvcha;
    }
}
