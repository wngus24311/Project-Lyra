package com.lyra.project_lyra.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyra.project_lyra.entity.book.BookReview;

public interface BookReviewRepository extends JpaRepository<BookReview, Long>{

}