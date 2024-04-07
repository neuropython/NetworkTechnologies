package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.ReviewsDTO;
import com.example.helloworldspring.entities.Book;
import com.example.helloworldspring.entities.User;
import com.example.helloworldspring.exceptionHandlers.CustomException;
import com.example.helloworldspring.exceptionHandlers.ExceptionCodes;
import com.example.helloworldspring.repositories.BookRepository;
import com.example.helloworldspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.helloworldspring.repositories.ReviewsRepository;
import com.example.helloworldspring.entities.Reviews;
@Service
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public Iterable<Reviews> getAllReviews(){
        return reviewsRepository.findAll();
    }

    public Reviews addReviews(ReviewsDTO reviewsDTO, String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException(ExceptionCodes.USER_NOT_FOUND);
        }
       Book book = bookRepository.findById(reviewsDTO.getBookId().intValue())
    .orElseThrow(() -> new CustomException(ExceptionCodes.BOOK_WITH_ID_NOT_FOUND));
        Reviews review = new Reviews();
        review.setUser(user);
        review.setBook(book);
        review.setRating(reviewsDTO.getRating());
        review.setReview(reviewsDTO.getReview());
        return reviewsRepository.save(review);
    }

    public Reviews getReviewById(Long id){
        return reviewsRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new CustomException(ExceptionCodes.REVIEW_NOT_FOUND));
    }

//    public Iterable<Reviews> getReviewsByBookId(Long bookId){
//        return reviewsRepository.findByBookId(bookId);
//    }




}
