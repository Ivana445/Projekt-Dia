package com.example.demo.Security_core.Perzistent2;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import com.example.demo.Perzistent.UserEntity;

@Entity(name = "token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "valid_until", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
}
