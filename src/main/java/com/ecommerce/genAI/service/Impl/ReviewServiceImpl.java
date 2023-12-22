package com.ecommerce.genAI.service.Impl;

import com.ecommerce.genAI.constants.ErrorMessage;
import com.ecommerce.genAI.exception.ApiRequestException;
import com.ecommerce.genAI.repository.PerfumeRepository;
import com.ecommerce.genAI.repository.ReviewRepository;
import com.ecommerce.genAI.domain.Perfume;
import com.ecommerce.genAI.domain.Review;
import com.ecommerce.genAI.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final PerfumeRepository perfumeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviewsByPerfumeId(Long perfumeId) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ApiRequestException(ErrorMessage.PERFUME_NOT_FOUND, HttpStatus.NOT_FOUND));
        return perfume.getReviews();
    }

    @Override
    @Transactional
    public Review addReviewToPerfume(Review review, Long perfumeId) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ApiRequestException(ErrorMessage.PERFUME_NOT_FOUND, HttpStatus.NOT_FOUND));
        List<Review> reviews = perfume.getReviews();
        reviews.add(review);
        double totalReviews = reviews.size();
        double sumRating = reviews.stream().mapToInt(Review::getRating).sum();
        perfume.setPerfumeRating(sumRating / totalReviews);
        return reviewRepository.save(review);
    }
}
