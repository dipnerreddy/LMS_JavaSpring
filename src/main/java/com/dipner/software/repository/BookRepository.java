package com.dipner.software.repository;

import com.dipner.software.beans.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Books,Long> {
}
