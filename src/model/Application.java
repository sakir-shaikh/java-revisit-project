package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
    private Long id;
    private JobSeeker jobSeeker;
    private Job job;
    private String status; // "PENDING", "ACCEPTED", "REJECTED"
    @Builder.Default
    private Date appliedDate = new Date();
} 