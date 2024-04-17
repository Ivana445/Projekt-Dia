package com.example.demo.Security_core;
import com.example.demo.Security_core.Perzistent2.TokenEntity;
import com.example.demo.Service.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.Security_core.Service2.AuthenticationService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
public class LibraryAuthenticationFilter extends OncePerRequestFilter{
    private AuthenticationService authenticationService;
    public LibraryAuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if ( !StringUtils.hasLength(authHeader) || ! authHeader.startsWith("Bearer ")) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }
       // System.out.println("filter funguje");
        String token = authHeader.substring("Bearer".length()).trim();
     //   System.out.println(token);

        System.out.println("1. aut");
        UserDTO userDTO = authenticationService.authenticate(token);
   //     System.out.println(userDTO);
 //       System.out.println("filter funguje");
        List<SimpleGrantedAuthority> roles = userDTO.getRoles().stream().map(
                SimpleGrantedAuthority::new).collect(Collectors.toList());
//        System.out.println("role: " + userDTO.getRoles());
//        System.out.println("role: " + roles.toString());
        UsernamePasswordAuthenticationToken auth
                = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), null, roles);
        SecurityContextHolder.getContext().setAuthentication(auth);

        System.out.println(response);
        System.out.println(request);
        filterChain.doFilter(request, response);
    }
}
