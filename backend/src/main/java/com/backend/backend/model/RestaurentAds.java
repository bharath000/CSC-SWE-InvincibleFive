package com.backend.backend.model;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name= "res_ads")
public class RestaurentAds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotBlank
    private Long resid;
    @NotBlank

    private Long adsid;

    public RestaurentAds(@NotBlank Long resid, @NotBlank Long adsid) {
        this.id = id;
        this.resid = resid;
        this.adsid = adsid;
    }


    public RestaurentAds(){

    }



    public Long getResid() {
        return resid;
    }

    public void setResid(Long resid) {
        this.resid = resid;
    }

    public Long getAdsid() {
        return adsid;
    }

    public void setAdsid(Long adsid) {
        this.adsid = adsid;
    }
}
