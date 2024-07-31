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
    private Long tokenId;

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean expired;

    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BaseUser user;
}
