package com.lanzong.repository;

import com.lanzong.pojo.BookJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJPARepository extends JpaRepository<BookJPA,Integer> {
}
