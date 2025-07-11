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
public class Profile {
    private Long id;
    private String summary;
    @Builder.Default
    private List<Skill> skills = new ArrayList<>();
} 