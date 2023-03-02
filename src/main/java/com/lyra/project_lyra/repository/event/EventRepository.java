package com.lyra.project_lyra.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lyra.project_lyra.entity.event.Event;
public interface EventRepository extends JpaRepository<Event, Long> {

	/* mySQL���� ������, ���� ��ȸ�ϰ��� �ϴ� �Խñ��� ������� 
	   ���� �������ִ� ��ȸ������ �ϳ��� �������Ѽ� ��ȸ�� ������ �ٲٴ°�.
	update board_table set board_hits=board_hits+1 where id=?
	*/
	@Modifying
	@Query(value = "update Event e set e.eventHits=e.eventHits+1 where e.evnum=:evnum")
	void updateHits(@Param("evnum") Long evnum);

	/*
	 * @Query(
	 * value="select e, count(r) from Event e left join EventReview r on r.Event = e group by e "
	 * , countQuery = "select count(e) from Event e") Page<Object[]>
	 * getEventWithEventReviewCount(Pageable pageable);
	 */
}
