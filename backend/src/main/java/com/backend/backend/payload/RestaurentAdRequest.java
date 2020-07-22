package com.backend.backend.payload;

import javax.validation.constraints.NotBlank;
public class RestaurentAdRequest {
    @NotBlank
    private Long resid;

    @NotBlank
    private Long adsid;

    public Long getResid() {
        return resid;
    }

    public void setResid(Long resid) {
        this.resid = resid;
    }

    public Long getAdsid() {
        return adsid;
    }

    public void setAdid(Long adsid) {
        this.adsid = adsid;
    }
}
