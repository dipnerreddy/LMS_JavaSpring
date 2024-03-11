package com.dipner.software.repository;

import com.dipner.software.beans.LoginUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginUsers,Long> {
}
