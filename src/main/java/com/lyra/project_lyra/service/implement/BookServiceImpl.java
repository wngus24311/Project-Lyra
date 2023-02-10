package com.lyra.project_lyra.service.implement;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.lyra.project_lyra.dto.BookDTO;
import com.lyra.project_lyra.dto.PageRequestDTO;
import com.lyra.project_lyra.dto.PageResultDTO;
import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.repository.book.BookInfoRepository;
import com.lyra.project_lyra.repository.book.BookReviewRepository;
import com.lyra.project_lyra.service.interfaces.BookService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BookServiceImpl implements BookService {

	private final BookInfoRepository bookInfoRepository;
	private final BookReviewRepository reviewInfoRepository;

	@Override
	public Long register(BookDTO dto) {
		log.info("dto--------" + dto);

		BookInfo bookInfo = dtoToEntity1(dto);

		bookInfoRepository.save(bookInfo);

		return bookInfo.getBookNum();
	}

	@Override
	public PageResultDTO<BookDTO, Object[]> getBookRankingList(PageRequestDTO pageRequestDto) {
		log.info("pageRequestDTO : " + pageRequestDto);

		Function<Object[], BookDTO> fn = (en -> entityToDto1((BookInfo) en[0]));

		Page<Object[]> result = BookInfoRepository.searchPage(pageRequestDto.getKeyword(),
				pageRequestDto.getPageable(Sort.by("bookLike").descending()));

		return new PageResultDTO<>(result, fn);

	}
	
	@Override
	public PageResultDTO<BookDTO, Object[]> getBookGerneList(PageRequestDTO pageRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResultDTO<BookDTO, Object[]> getBookReviewList(PageRequestDTO pageRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BookDTO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modify(BookDTO bookDTO) {
		// TODO Auto-generated method stub

	}
}
