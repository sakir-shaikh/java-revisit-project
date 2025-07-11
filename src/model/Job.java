package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    private Long id;
    private String title;
    private String description;
    private Company company;
    @Builder.Default
    private List<Application> applications = new ArrayList<>();
    @Builder.Default
    private List<Skill> requiredSkills = new ArrayList<>();
} 