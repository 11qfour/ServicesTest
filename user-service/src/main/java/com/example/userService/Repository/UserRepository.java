package com.example.userService.Repository;

import com.example.userService.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByCompanyId(Long companyId, Pageable pageable);
    Page<User> findAll(Pageable pageable);
}
