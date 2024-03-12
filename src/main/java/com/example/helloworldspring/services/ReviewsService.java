package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.ReviewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.helloworldspring.repositories.ReviewsRepository;
import com.example.helloworldspring.entities.Reviews;
@Service
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    public Iterable<Reviews> getAllReviews(){
        return reviewsRepository.findAll();
    }

    public Reviews addReviews(ReviewsDTO reviewsDTO){
        Reviews reviews = new Reviews();
        reviews.setReviewId(reviewsDTO.getReviewId());
        reviews.setBookId(reviewsDTO.getBookId());
        reviews.setUserId(reviewsDTO.getUserId());
        reviews.setReview(reviewsDTO.getReview());
        return reviewsRepository.save(reviews);
    }


}
