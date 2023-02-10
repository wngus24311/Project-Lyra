package com.lyra.project_lyra.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyra.project_lyra.entity.event.EventReview;

public interface EventReviewRepository extends JpaRepository<EventReview, Long> {

/*
	@EntityGraph(attributePaths = {"event"})
	List<EventReview> findByEvent(Event event);
	
	@Modifying
	@Query("delete from Review mr where mr.")
	void deleteBymember(Member member);
	*/
}
