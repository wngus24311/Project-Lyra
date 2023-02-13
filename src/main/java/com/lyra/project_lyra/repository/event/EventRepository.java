package com.lyra.project_lyra.repository.event;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lyra.project_lyra.entity.event.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	/* mySQL기준 쿼리문, 내가 조회하고자 하는 게시글의 조희수를 
	   현재 가지고있는 조회수에서 하나를 증가시켜서 조회수 값으로 바꾸는것.
	update board_table set board_hits=board_hits+1 where id=?
	*/
	@Modifying
	@Query(value = "update Event e set e.eventHits=e.eventHits+1 where e.evnum=:evnum")
	void updateHits(@Param("evnum") Long evnum);
}
