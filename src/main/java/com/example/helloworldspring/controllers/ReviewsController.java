package com.example.helloworldspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.helloworldspring.services.ReviewsService;
import com.example.helloworldspring.dto.ReviewsDTO;
import com.example.helloworldspring.entities.Reviews;
import org.springframework.http.HttpStatus;

import java.security.Principal;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService){
        this.reviewsService = reviewsService;
    }

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public @ResponseBody Iterable<Reviews> getAllReviews(){
        return reviewsService.getAllReviews();
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Reviews addReviews(@RequestBody ReviewsDTO reviewsDTO, Principal principal){

        return reviewsService.addReviews(reviewsDTO, principal.getName());
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public @ResponseBody Reviews getReviewById(@PathVariable Long id){
        return reviewsService.getReviewById(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteReviewById(@PathVariable Long id, Principal principal){
        reviewsService.deleteReviewById(id, principal.getName());
    }

}
