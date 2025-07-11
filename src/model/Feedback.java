package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {
    private Long id;
    private String comment;
    private int rating; // 1-5 stars
    private JobSeeker jobSeeker;
    private Company company;
} 