package com.lyra.project_lyra.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lyra.project_lyra.dto.CombineDTO;
import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.combine.CombineKeep;
import com.lyra.project_lyra.entity.combine.CombineLike;
import com.lyra.project_lyra.entity.combine.CombinePage;
import com.lyra.project_lyra.repository.book.BookInfoRepository;
import com.lyra.project_lyra.repository.combine.CombineKeepRepository;
import com.lyra.project_lyra.repository.combine.CombineLikeRepository;
import com.lyra.project_lyra.repository.combine.CombinePageRepository;
import com.lyra.project_lyra.service.interfaces.CombineService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CombineServiceImpl implements CombineService {

	private final CombineKeepRepository combineKeepRepository;
	private final CombineLikeRepository combineLikeRepository;
	private final CombinePageRepository combinePageRepository;
	private final BookInfoRepository bookInfoRepository;
	
	
	@Override
	public void bookKeepSave(String username, Long bookNum) {
		CombineKeep combineKeep = new CombineKeep();
		CombineDTO combineDTO = new CombineDTO();
		
		combineDTO.setUsername(username);
		combineDTO.setBookNum(bookNum);
		
		combineKeep = combineDtoToEntity(combineDTO);
		
		combineKeepRepository.save(combineKeep);
	}

	@Override
	public CombineDTO combineList(String username) {
		return null;
	}

	@Override
	public boolean bookKeepDelete(String username, Long bookNum) {
		boolean bResult = false;
		String strBookNum = Long.toString(bookNum);
		String strListBookNum;

		List<CombineKeep> list = new ArrayList<>();
		
		list = combineKeepRepository.findAll();
		
		for (int i = 0; i < list.size(); i++) {
			
			strListBookNum = Long.toString(list.get(i).getBookInfo().getBookNum());
			
			if (list.get(i).getMemberInfo().getUsername().equals(username) 
					&& strBookNum.equals(strListBookNum)) {

				combineKeepRepository.deleteById(list.get(i).getKeepNum());
				
				bResult = true;
			}
		}

		return bResult;
	}

	@Override
	public List<CombineDTO> getKeepList(String username) {
		
		List<CombineDTO> combineDTOs = new ArrayList<>();
		List<CombineKeep> combines = new ArrayList<>();
		
		combines = combineKeepRepository.findAll();
		
		for (int i = 0; i < combines.size(); i++) {
			if (combines.get(i).getMemberInfo().getUsername().equals(username)) {
				CombineDTO combineDTO = CombineDTO.builder()
						.bookNum(combines.get(i).getBookInfo().getBookNum())
						.keepNum(combines.get(i).getKeepNum())
						.username(combines.get(i).getMemberInfo().getUsername())
						.build();
				
				combineDTOs.add(combineDTO);
			}
		}
		
		return combineDTOs;
	}

	@Override
	public void bookLikeSave(String username, Long bookNum) {
		CombineLike combineLike = new CombineLike();
		CombineDTO combineDTO = new CombineDTO();
		
		combineDTO.setUsername(username);
		combineDTO.setBookNum(bookNum);
		
		combineLike = likeDtoToEntity(combineDTO);
		
		combineLikeRepository.save(combineLike);
	}

	@Override
	public boolean bookLikeDelete(String username, Long bookNum) {
		boolean bResult = false;
		String strBookNum = Long.toString(bookNum);
		String strListBookNum;

		List<CombineLike> list = new ArrayList<>();
		
		
		list = combineLikeRepository.findAll();
		
		for (int i = 0; i < list.size(); i++) {
			
			strListBookNum = Long.toString(list.get(i).getBookInfo().getBookNum());
			
			if (list.get(i).getMemberInfo().getUsername().equals(username) 
					&& strBookNum.equals(strListBookNum)) {

				combineLikeRepository.deleteById(list.get(i).getLikeNum());
				
				Optional<BookInfo> bookInfos = bookInfoRepository.findById(bookNum);

				bookInfoRepository.updateLikeCount(bookNum, bookInfos.get().getBookLike() - 1);
				
				bResult = true;
			}
		}
		
		if (bResult == false) {
			Optional<BookInfo> bookInfos = bookInfoRepository.findById(bookNum);

			bookInfoRepository.updateLikeCount(bookNum, bookInfos.get().getBookLike() + 1);
		}
		
		return bResult;
	}

	@Override
	public void bookPageSave(String username, Long bookNum, Long bookPage) {
		CombinePage combinePage = new CombinePage();
		CombineDTO combineDTO = new CombineDTO();
		
		combineDTO.setUsername(username);
		combineDTO.setBookNum(bookNum);
		combineDTO.setBookPage(bookPage);
		
		combinePage = pageDtoToEntity(combineDTO);
		
		combinePageRepository.save(combinePage);
	}

	@Override
	public boolean bookPageUpdate(String username, Long bookNum, Long bookPage) {
		boolean bResult = false;
		String strBookNum = Long.toString(bookNum);
		String strListBookNum;

		List<CombinePage> list = new ArrayList<>();
		
		
		list = combinePageRepository.findAll();
		
		for (int i = 0; i < list.size(); i++) {
			
			strListBookNum = Long.toString(list.get(i).getBookInfo().getBookNum());
			
			if (list.get(i).getMemberInfo().getUsername().equals(username) 
					&& strBookNum.equals(strListBookNum)) {
				combinePageRepository.updateBookPage(bookNum, bookPage);

				bResult = true;
			}
		}
		
		return bResult;
	}

	@Override
	public List<CombineDTO> getPageList(String username) {
		List<CombineDTO> combineDTOs = new ArrayList<>();
		List<CombinePage> combines = new ArrayList<>();
		
		combines = combinePageRepository.findAll();
		
		for (int i = 0; i < combines.size(); i++) {
			if (combines.get(i).getMemberInfo().getUsername().equals(username)) {
				CombineDTO combineDTO = CombineDTO.builder()
						.bookNum(combines.get(i).getBookInfo().getBookNum())
						.pageNum(combines.get(i).getPageNum())
						.username(combines.get(i).getMemberInfo().getUsername())
						.bookPage(combines.get(i).getBookPage())
						.build();
				
				combineDTOs.add(combineDTO);
			}
		}
		
		return combineDTOs;
	}

	@Override
	public String[] getPage(List<CombineDTO> combineDTO) {
		String[] pages = new String[combineDTO.size()];
		
		for (int i = 0; i < pages.length; i++) {
			pages[i] = Long.toString(combineDTO.get(i).getBookPage() * 10) + "%";
		}		
		
		return pages;
	}
	
}
