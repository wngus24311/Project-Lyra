package com.lyra.project_lyra.repository.event;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lyra.project_lyra.entity.event.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	/*
		 //EventImage ei / Event e
	@Query("select e, ei, avg(coalesce(r.grade,0)), count(r) from Event e "
			+ "left outer join EventImage ei on ei.event = e "
			+ "left outer join EventReview r on r.event = r group by e")
	Page<Object[]> getListPage(Pageable pageable);
	
	@Query("select e, ei, avg(coalesce(r.grade,0)), count(r) from Event e "
			+ "left outer join EventReview r on r.event = e "
			+ "where e.eventnum = :eventnum group by ei")
	List<Object[]> getEventWithAll(Long eventnum);
*/
}
