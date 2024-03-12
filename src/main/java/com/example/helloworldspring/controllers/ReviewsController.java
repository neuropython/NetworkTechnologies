package com.example.helloworldspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.helloworldspring.services.ReviewsService;
import com.example.helloworldspring.dto.ReviewsDTO;
import com.example.helloworldspring.entities.Reviews;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService){
        this.reviewsService = reviewsService;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Reviews> getAllReviews(){
        return reviewsService.getAllReviews();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Reviews addReviews(@RequestBody ReviewsDTO reviewsDTO){
        return reviewsService.addReviews(reviewsDTO);
    }

}
