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
public class JobSeeker extends User {
    private Profile profile;
    @Builder.Default
    private List<Application> applications = new ArrayList<>();
    @Builder.Default
    private List<Skill> skills = new ArrayList<>();
} 