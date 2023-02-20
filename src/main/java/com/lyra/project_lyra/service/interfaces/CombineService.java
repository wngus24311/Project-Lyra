package com.lyra.project_lyra.service.interfaces;

import com.lyra.project_lyra.dto.CombineDTO;
import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.combine.CombineKeep;
import com.lyra.project_lyra.entity.member.MemberInfo;

public interface CombineService {
	//찜한 책 저장
	void bookKeepSave(String username, Long bookNum);
	
	//찜한 책 정렬
	CombineDTO combineList(String username);
	
	boolean bookKeepDelete(String username, Long bookNum);
	
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
}
