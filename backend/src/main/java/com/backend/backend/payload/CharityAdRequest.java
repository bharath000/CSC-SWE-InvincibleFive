package com.backend.backend.payload;
import javax.validation.constraints.NotBlank;
public class CharityAdRequest {

    @NotBlank
    private Long charityid;

    @NotBlank
    private Long number_meals;

    @NotBlank
    private String ad_desc;

    @NotBlank
    private  String order_status;

    @NotBlank
    private String zipcode;

    public Long getCharityid() {
        return charityid;
    }

    public void setCharity_id(Long charityid) {
        this.charityid = charityid;
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

    public String getOrder_status() {
        return order_status;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
