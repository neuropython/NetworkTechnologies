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
@CrossOrigin(origins = "http://localhost:3000")

public class ReviewsController {
    private final ReviewsService reviewsService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService){
        this.reviewsService = reviewsService;
    }

    /**
     * Get all reviews
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public @ResponseBody Iterable<Reviews> getAllReviews(){
        return reviewsService.getAllReviews();
    }
    /**
     * Add a new review
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Reviews addReviews(@RequestBody ReviewsDTO reviewsDTO, Principal principal){

        return reviewsService.addReviews(reviewsDTO, principal.getName());
    }
    /**
     * Get review by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public @ResponseBody Reviews getReviewById(@PathVariable Long id){
        return reviewsService.getReviewById(id);
    }
    /**
     * Delete review by id
     * @param id
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteReviewById(@PathVariable Long id, Principal principal){
        reviewsService.deleteReviewById(id, principal.getName());
    }
    /**
     * Update review by id
     * @param id
     */
    @PatchMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void updateReviewById(@PathVariable Long id, @RequestBody ReviewsDTO reviewsDTO, Principal principal){
        reviewsService.updateReviewById(id, reviewsDTO, principal.getName());
    }
    /**
     * Get my reviews
     * @return
     */
    @GetMapping("/mine")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody Iterable<Reviews> getMyReviews(Principal principal){
        return reviewsService.getMyReviews(principal.getName());
    }

    @GetMapping("/book/{bookId}")
    @PreAuthorize("permitAll()")
    public @ResponseBody Iterable<Reviews> getReviewsByBookId(@PathVariable Long bookId){
        return reviewsService.getReviewsByBookId(bookId);
    }
}
