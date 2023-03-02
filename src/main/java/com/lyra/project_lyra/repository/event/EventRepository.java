package com.lyra.project_lyra.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lyra.project_lyra.entity.event.Event;
public interface EventRepository extends JpaRepository<Event, Long> {

	@Modifying
	@Query(value = "update Event e set e.eventHits=e.eventHits+1 where e.evnum=:evnum")
	void updateHits(@Param("evnum") Long evnum);

}
