package com.backend.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.backend.model.RestaurentAds;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
public interface RestaurentAdsRepository extends JpaRepository<RestaurentAds, Long> {


    boolean existsByAdsid(Long adid);
    List<RestaurentAds> findByResid(Long id);
    List<RestaurentAds> findByAdsid(Long id);
    boolean existsByResid(Long resid);
}
