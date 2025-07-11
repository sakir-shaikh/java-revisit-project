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
public class Company {
    private Long id;
    private String name;
    private String description;
    @Builder.Default
    private List<Job> jobs = new ArrayList<>();
} 