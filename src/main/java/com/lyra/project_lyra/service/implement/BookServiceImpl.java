package com.lyra.project_lyra.service.implement;

import java.util.ArrayList;
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
import com.lyra.project_lyra.dto.CombineDTO;
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

		BookInfo bookInfo = bookInfoDtoToEntity(dto);

		bookInfoRepository.save(bookInfo);

		return bookInfo.getBookNum();
	}

	@Override
	public Long register2(BookDTO dto) {

		BookReview bookReview = reviewDtoToEntity(dto);

		bookReviewRepository.save(bookReview);

		return bookReview.getReviewnum();
	}

	// 책 랭킹
	@Override
	public PageResultDTO<BookDTO, Object[]> getBookRankingList(PageRequestDTO pageRequestDto) {

		Pageable pageable = pageRequestDto.getPageable(Sort.by("bookLike").descending());

		Page<Object[]> result = bookInfoRepository.getBookRankingPage(pageable);

		result.getContent().forEach(arr -> {
		});

		Function<Object[], BookDTO> fn = (en -> bookInfoEntityToDto((BookInfo) en[0]));

		return new PageResultDTO<>(result, fn);

	}

	// 리뷰 페이지
	@Override
	public List<BookDTO> getList(List<Long> likeBookNum, List<Long> keepBookNum) {

		List<BookInfo> books = bookInfoRepository.findAll();
		List<BookDTO> bookDTOList = new ArrayList<>();
		
		for (BookInfo book : books) {
			String likeFlag = "0";
			String keepFlag = "0";
			
			for(int i = 0; i < likeBookNum.size(); i++) {
				if (likeBookNum.get(i) == book.getBookNum()) {
					likeFlag = "1";
				}
			}
			
			for(int i = 0; i < keepBookNum.size(); i++) {
				if (keepBookNum.get(i) == book.getBookNum()) {
					keepFlag = "1";
				}
			}
			
			if (entityNullCheck(book)) {
				BookDTO bookDTO = BookDTO.builder()
						.bookNum(book.getBookNum())
						.bookTitle(book.getBookTitle())
						.bookGerne(book.getBookGerne())
						.bookThumbnail(book.getBookThumbnail())
						.bookLike(book.getBookLike())
						.bookPage(book.getBookPage())
						.likeCheck(likeFlag)
						.keepCheck(keepFlag)
						.build();
				
				bookDTOList.add(bookDTO);
			}
		}
		
		return bookDTOList;
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

	@Override
	public void insert(BookDTO dto) {
		
		BookInfo bookInfo = bookInfoDtoToEntity(dto);
		
		bookInfoRepository.save(bookInfo);
	}

	@Override
	public List<BookDTO> getCategoryList(String username, List<Long> likeBookNum, List<Long> keepBookNum) {
		List<BookInfo> books = bookInfoRepository.findAllByCategoryQuery(username);
		List<BookDTO> bookDTOList = new ArrayList<>();		
		
		for (BookInfo book : books) {
			String likeFlag = "0";
			String keepFlag = "0";
			
			for(int i = 0; i < likeBookNum.size(); i++) {
				if (likeBookNum.get(i) == book.getBookNum()) {
					likeFlag = "1";
				}
			}
			
			for(int i = 0; i < keepBookNum.size(); i++) {
				if (keepBookNum.get(i) == book.getBookNum()) {
					keepFlag = "1";
				}
			}
			
			if (entityNullCheck(book)) {
				BookDTO bookDTO = BookDTO.builder()
						.bookNum(book.getBookNum())
						.bookTitle(book.getBookTitle())
						.bookGerne(book.getBookGerne())
						.bookThumbnail(book.getBookThumbnail())
						.bookLike(book.getBookLike())
						.bookPage(book.getBookPage())
						.likeCheck(likeFlag)
						.keepCheck(keepFlag)
						.build();
				
				bookDTOList.add(bookDTO);
			}
		}
		
		return bookDTOList;
	}

	@Override
	public List<BookDTO> getLikeList(List<Long> likeBookNum, List<Long> keepBookNum) {
		List<BookInfo> books = bookInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "bookLike"));
		List<BookDTO> bookDTOList = new ArrayList<>();
		
		for (BookInfo book : books) {
			String likeFlag = "0";
			String keepFlag = "0";
			
			for(int i = 0; i < likeBookNum.size(); i++) {
				if (likeBookNum.get(i) == book.getBookNum()) {
					likeFlag = "1";
				}
			}
			
			for(int i = 0; i < keepBookNum.size(); i++) {
				if (keepBookNum.get(i) == book.getBookNum()) {
					keepFlag = "1";
				}
			}
			
			if (entityNullCheck(book)) {
				 BookDTO bookDTO = BookDTO.builder()
						.bookNum(book.getBookNum())
						.bookTitle(book.getBookTitle())
						.bookGerne(book.getBookGerne())
						.bookThumbnail(book.getBookThumbnail())
						.bookLike(book.getBookLike())
						.bookPage(book.getBookPage())
						.likeCheck(likeFlag)
						.keepCheck(keepFlag)
						.build();
				
				bookDTOList.add(bookDTO);
			}
		}
		
		return bookDTOList;
	}

	@Override
	public List<BookDTO> getUpdateList(List<Long> likeBookNum, List<Long> keepBookNum) {
		List<BookInfo> books = bookInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "bookNum"));
		List<BookDTO> bookDTOList = new ArrayList<>();
		
		for (BookInfo book : books) {
			String likeFlag = "0";
			String keepFlag = "0";
			
			for(int i = 0; i < likeBookNum.size(); i++) {
				if (likeBookNum.get(i) == book.getBookNum()) {
					likeFlag = "1";
				}
			}
			
			for(int i = 0; i < keepBookNum.size(); i++) {
				if (keepBookNum.get(i) == book.getBookNum()) {
					keepFlag = "1";
				}
			}
			
			if (entityNullCheck(book)) {
				BookDTO bookDTO = BookDTO.builder()
						.bookNum(book.getBookNum())
						.bookTitle(book.getBookTitle())
						.bookGerne(book.getBookGerne())
						.bookThumbnail(book.getBookThumbnail())
						.bookLike(book.getBookLike())
						.bookPage(book.getBookPage())
						.likeCheck(likeFlag)
						.keepCheck(keepFlag)
						.build();
				
				bookDTOList.add(bookDTO);
			}
		}
		
		return bookDTOList;
	}

	@Override
	public List<BookDTO> getBookList(List<CombineDTO> combineDTO, List<Long> likeBookNum, List<Long> keepBookNum) {
		List<BookDTO> listBookDTOs = new ArrayList<>();

		for(int i = 0; i < combineDTO.size(); i++) {
			Optional<BookInfo> bookInfo = bookInfoRepository.findById(combineDTO.get(i).getBookNum());			
			String likeFlag = "0";
			String keepFlag = "0";
			
			for(int y = 0; y < likeBookNum.size(); y++) {
				if (likeBookNum.get(y) == bookInfo.get().getBookNum()) {
					likeFlag = "1";
				}
			}
			
			for(int y = 0; y < keepBookNum.size(); y++) {
				if (keepBookNum.get(y) == bookInfo.get().getBookNum()) {
					keepFlag = "1";
				}
			}
			
			BookDTO bookDTO = BookDTO.builder()
					.bookNum(bookInfo.get().getBookNum())
					.bookTitle(bookInfo.get().getBookTitle())
					.bookGerne(bookInfo.get().getBookGerne())
					.bookThumbnail(bookInfo.get().getBookThumbnail())
					.bookLike(bookInfo.get().getBookLike())
					.bookPage(bookInfo.get().getBookPage())
					.likeCheck(likeFlag)
					.keepCheck(keepFlag)
					.build();
			
			listBookDTOs.add(bookDTO);
		}
		
		return listBookDTOs;
	}
}
