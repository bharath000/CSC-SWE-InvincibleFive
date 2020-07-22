package com.backend.backend.repository;

import com.backend.backend.model.User_Profile;
import com.backend.backend.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface User_ProfileRepository extends JpaRepository<User_Profile, Long> {


    Optional<User_Profile> findByuserid(long userid);
}
