package com.backend.backend.payload;

import javax.validation.constraints.NotBlank;

public class OrderConfirmRequest {
    @NotBlank
    private Long adsid;

    public Long getAdsid() {
        return adsid;
    }

    public void setAdsid(Long adsid) {
        this.adsid = adsid;
    }
}
