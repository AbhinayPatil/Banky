package com.abhinay.bankapp.bankapp.repository;

import com.abhinay.bankapp.bankapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
}
