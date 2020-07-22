package com.backend.backend.repository;
import com.backend.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.backend.model.Charity_Ads;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Charity_AdsRepository extends JpaRepository<Charity_Ads, Long> {
    List<Charity_Ads>  findByZipcode(String zipcode);
   // Optional<Charity_Ads> findByAd_id(Long id);
    List<Charity_Ads>  findByCharityid(Long charityid);
    List<Charity_Ads>  findByDateContainingAndZipcodeAndOrderstatus(String date, String zipcode,String orderstatus );
    List<Charity_Ads>  findByDateContainingAndCharityid(String date, Long id);
    //@Query("select * From bounty.charity_ads where charity_ads.charity_id = ':charity_id'")
    //List<Charity_Ads>  findByCharity(@Param("charity_id")long  charity_id);

    //@Modifying
    //@Query("UPDATE charity_ads e SET e.order_status = :status  WHERE e.ad_id = :id")
    //updateDeptSalaries(@Param("dept") String status, @Param("byPercent") Long id);
    //@Query(value = "SELECT * FROM bounty.charity_ads where charity_ads.date_and_time \"%'+s+'%\" and charity_ads.zipcode = \"'+zipcode+'\";",nativeQuery = true)
    //List<Charity_Ads> findByZipcode(String zipcode);
    //List<Charity_Ads> findByCharity_ID(Long charity_id);
    //List<Charity_Ads>  findByCharityID(Long charity_id);

}
