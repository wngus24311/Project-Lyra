package com.lyra.project_lyra.repository.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyra.project_lyra.entity.event.Event;
import com.lyra.project_lyra.entity.event.EventReview;

public interface EventReviewRepository extends JpaRepository<EventReview, Long> {
	
	// select * from tbl_evnetreview where evnum=? order by id desc;
	List<EventReview> findAllByEventOrderByEvrnumDesc(Event event);
	
}