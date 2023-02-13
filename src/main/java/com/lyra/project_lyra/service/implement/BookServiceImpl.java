package com.lyra.project_lyra.service.implement;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lyra.project_lyra.dto.BookDTO;
import com.lyra.project_lyra.dto.PageRequestDTO;
import com.lyra.project_lyra.dto.PageResultDTO;
import com.lyra.project_lyra.entity.book.BookInfo;
import com.lyra.project_lyra.entity.book.BookReview;
import com.lyra.project_lyra.repository.book.BookInfoRepository;
import com.lyra.project_lyra.repository.book.BookReviewRepository;
import com.lyra.project_lyra.service.interfaces.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookInfoRepository bookInfoRepository;
	private final BookReviewRepository bookReviewRepository;

	@Override
	public Long register1(BookDTO dto) {
		log.info("dto--------" + dto);

		BookInfo bookInfo = dtoToEntity1(dto);

		bookInfoRepository.save(bookInfo);

		return bookInfo.getBookNum();
	}

	@Override
	public Long register2(BookDTO dto) {
		log.info("dto--------" + dto);

		BookReview bookReview = dtoToEntity2(dto);

		bookReviewRepository.save(bookReview);

		return bookReview.getReviewnum();
	}

	// 책 랭킹
	@Override
	public PageResultDTO<BookDTO, Object[]> getBookRankingList(PageRequestDTO pageRequestDto) {
		log.info("pageRequestDTO : " + pageRequestDto);

		Pageable pageable = pageRequestDto.getPageable(Sort.by("bookLike").descending());

		Page<Object[]> result = bookInfoRepository.getBookRankingPage(pageable);

		result.getContent().forEach(arr -> {
			log.info(Arrays.toString(arr));
		});

		Function<Object[], BookDTO> fn = (en -> entityToDto1((BookInfo) en[0]));

		return new PageResultDTO<>(result, fn);

	}

	// 책 장르별
	@Override
	public PageResultDTO<BookDTO, Object[]> getBookGerneList(PageRequestDTO pageRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	// 리뷰 페이지
	@Override
	public List<BookDTO> getList(Long bookNum) {

		BookInfo bookInfo = BookInfo.builder().bookNum(bookNum).build();

		List<BookReview> result = bookReviewRepository.findByBookInfo(bookInfo);

		return result.stream().map(bookReview -> entityToDto2(bookReview)).collect(Collectors.toList());
	}

	// 책 리뷰 수정
	@Override
	public void modify(BookDTO BookDTO) {
		Optional<BookReview> result = bookReviewRepository.findById(BookDTO.getReviewNum());

		if (result.isPresent()) {
			BookReview review = result.get();
			review.changeGrade(BookDTO.getGrade());
			review.changeReview(BookDTO.getBookReview());

			bookReviewRepository.save(review);
		}

	}

	// 리뷰 삭제
	@Override
	public void remove(Long reviewNum) {
		bookReviewRepository.deleteById(reviewNum);
		
	}
}
