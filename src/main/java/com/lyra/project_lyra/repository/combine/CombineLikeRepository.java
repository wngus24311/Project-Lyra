package com.lyra.project_lyra.repository.combine;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lyra.project_lyra.entity.combine.CombineLike;

public interface CombineLikeRepository extends JpaRepository<CombineLike, Long>{
	
}