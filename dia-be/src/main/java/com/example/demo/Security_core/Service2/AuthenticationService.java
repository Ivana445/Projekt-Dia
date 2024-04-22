package com.example.demo.Security_core.Service2;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.Perzistent.UserRepository;
import com.example.demo.Perzistent.UserEntity;
import com.example.demo.Security_core.Perzistent2.RoleEntity;
import com.example.demo.Security_core.Perzistent2.TokenEntity;
import com.example.demo.Security_core.Perzistent2.TokenRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Service.UserDTO;

import javax.management.relation.Role;
import javax.sql.RowSet;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    private static final int TOKEN_VALIDITY_IN_MINUTES = 15;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    // https://bcrypt-generator.com/, round 1


    public AuthenticationService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Transactional
    public String authenticate(String username, String password) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Username and/or password do not match!");
        }

        if (!passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            throw new AuthenticationCredentialsNotFoundException("Username and/or password do not match!");
        }

        TokenEntity token = new TokenEntity();
        Set<String> userRoles = new HashSet<>();
        String randomString = UUID.randomUUID().toString();
        token.setToken(randomString);
        token.setUser(optionalUser.get());
        token.setCreatedAt(LocalDateTime.now());
        tokenRepository.save(token);
        System.out.println("token co vkladam do databazy: " + token);

        return token.getToken();
    }

    @Transactional
    public UserDTO authenticate(String token) {
        Optional<TokenEntity> optionalToken = tokenRepository.findByToken(token);

        System.out.println("1");
        System.out.println(optionalToken);
        System.out.println(token);
        if (optionalToken.isEmpty() || token.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }
        System.out.println("2");
        validateTokenExpiration(optionalToken.get());
        System.out.println("3");
        Set<RoleEntity> roles = optionalToken.get().getUser().getRoles();
        Set<String> roleNames = roles.stream()
                .map( entry -> entry.getRoleName())
                .collect(Collectors.toSet());
        System.out.println("4");
        System.out.println("roles"+roles);
        System.out.println("roles" + roleNames);
        return new UserDTO(optionalToken.get().getUser().getId(), optionalToken.get().getUser().getUsername(), optionalToken.get().getUser().getPassword(),optionalToken.get().getUser().getEmail(), roleNames);
    }

    private void validateTokenExpiration(TokenEntity token) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenExpiration = token.getCreatedAt().plus(TOKEN_VALIDITY_IN_MINUTES, ChronoUnit.MINUTES);

        if (now.isAfter(tokenExpiration)) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }
    }
//    @Transactional
//    public String generateTokenForUser(String username) {
//        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
//
//        if (optionalUser.isEmpty()) {
//            throw new IllegalArgumentException("User not found!");
//        }
//
//        TokenEntity token = new TokenEntity();
//        String randomString = UUID.randomUUID().toString();
//        token.setToken(randomString);
//        token.setUser(optionalUser.get());
//        token.setCreatedAt(LocalDateTime.now());
//        tokenRepository.save(token);
//
//        return token.getToken();
//    }

    @Transactional
    public void tokenRemove(String token) {
        tokenRepository.deleteByToken(token);
    }
}
