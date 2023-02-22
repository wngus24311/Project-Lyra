package com.lyra.project_lyra.service.interfaces;

import java.util.List;

import com.lyra.project_lyra.dto.CombineDTO;
import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.combine.CombineKeep;
import com.lyra.project_lyra.entity.combine.CombineLike;
import com.lyra.project_lyra.entity.combine.CombinePage;
import com.lyra.project_lyra.entity.member.MemberInfo;

public interface CombineService {
	//찜한 책 저장
	void bookKeepSave(String username, Long bookNum);
	
	//찜한 책 정렬
	CombineDTO combineList(String username);
	
	//찜한 책 취소
	boolean bookKeepDelete(String username, Long bookNum);
	
	//좋아요 책 저장
	void bookLikeSave(String username, Long bookNum);
	
	//좋아요 책 취소
	boolean bookLikeDelete(String username, Long bookNum);
	
	//보던 책 저장
	void bookPageSave(String username, Long bookNum, Long bookPage);
		
	//보던 책 취소
	boolean bookPageUpdate(String username, Long bookNum, Long bookPage);
	
	//회원 별 읽었던 책 or 페이지 가져오기
	List<CombineDTO> getPageList(String username);
	
	//페이지 별도 가져오기
	String[] getPage(List<CombineDTO> combineDTO);
	
 	// 찜한 책 가져오기
 	List<CombineDTO> getKeepList(String username);
	
	//찜한 정보 DB저장
	default CombineKeep combineDtoToEntity(CombineDTO dto) {
		
		MemberInfo memberInfo = new MemberInfo();
		BookInfo bookInfo = new BookInfo();
		CombineKeep combineKeep = new CombineKeep();
		
		memberInfo = MemberInfo.builder()
				.username(dto.getUsername())
				.build();
		
		bookInfo = BookInfo.builder()
				.bookNum(dto.getBookNum())
				.build();
		
		combineKeep = CombineKeep.builder()
				.memberInfo(memberInfo)
				.bookInfo(bookInfo)
				.build();

		return combineKeep;
	}
	
	default CombineLike likeDtoToEntity(CombineDTO dto) {
		
		MemberInfo memberInfo = new MemberInfo();
		BookInfo bookInfo = new BookInfo();
		CombineLike combinelike = new CombineLike();
		
		memberInfo = MemberInfo.builder()
				.username(dto.getUsername())
				.build();
		
		bookInfo = BookInfo.builder()
				.bookNum(dto.getBookNum())
				.build();
		
		combinelike = CombineLike.builder()
				.memberInfo(memberInfo)
				.bookInfo(bookInfo)
				.build();

		return combinelike;
	}
	
	default CombinePage pageDtoToEntity(CombineDTO dto) {
		
		MemberInfo memberInfo = new MemberInfo();
		BookInfo bookInfo = new BookInfo();
		CombinePage combinepage = new CombinePage();
		
		memberInfo = MemberInfo.builder()
				.username(dto.getUsername())
				.build();
		
		bookInfo = BookInfo.builder()
				.bookNum(dto.getBookNum())
				.build();
		
		combinepage = CombinePage.builder()
				.memberInfo(memberInfo)
				.bookInfo(bookInfo)
				.bookPage(dto.getBookPage())
				.build();

		return combinepage;
	}	
}
