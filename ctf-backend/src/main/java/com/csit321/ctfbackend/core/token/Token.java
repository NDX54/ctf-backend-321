package com.csit321.ctfbackend.core.token;

import com.csit321.ctfbackend.user.model.BaseUser;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId; // Token ID

    private String token; // Token value

    @Enumerated(EnumType.STRING)
    private TokenType tokenType; // Token type (e.g., BEARER)

    private boolean expired; // Expiration status

    private boolean revoked; // Revocation status

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BaseUser user; // Associated user
}
