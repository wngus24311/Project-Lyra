package com.lyra.project_lyra.repository.combine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.combine.CombineKeep;

public interface CombineKeepRepository extends JpaRepository<CombineKeep, Long>{

}