package com.backend.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "charity_ads")
public class Charity_Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long ad_id;
    @NotBlank
    private Long charityid;
    @NotBlank

    private String date;

    @NotBlank
    private Long number_meals;

    @NotBlank
    private String ad_desc;

    @NotBlank
    private  String orderstatus;
    @NotBlank
    private String zipcode;
    public Charity_Ads(){

    }
    public Charity_Ads(@NotBlank Long charityid, @NotBlank String  date, @NotBlank Long number_meals, @NotBlank String ad_desc, @NotBlank String orderstatus,@NotBlank String zipcode) {
        this.ad_id = ad_id;
        this.charityid = charityid;
        this.date = date;
        this.number_meals = number_meals;
        this.ad_desc = ad_desc;
        this.orderstatus = orderstatus;
        this.zipcode = zipcode;
    }

    public Long getCharityid() {
        return charityid;
    }

    public Long getAd_id() {
        return ad_id;
    }

    public void setAd_id(Long ad_id) {
        this.ad_id = ad_id;
    }

    public void setCharity_id(Long charityid) {
        this.charityid = charityid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getNumber_meals() {
        return number_meals;
    }

    public void setNumber_meals(Long number_meals) {
        this.number_meals = number_meals;
    }

    public String getAd_desc() {
        return ad_desc;
    }

    public void setAd_desc(String ad_desc) {
        this.ad_desc = ad_desc;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }
}
