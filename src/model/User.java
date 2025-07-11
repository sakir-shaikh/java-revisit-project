package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public abstract class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String userType; // "JOBSEEKER" or "RECRUITER"
} 