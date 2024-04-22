package com.example.demo.Service;

import com.example.demo.Perzistent.UserEntity;
import com.example.demo.Perzistent.UserRepository;
import com.example.demo.Security_core.Perzistent2.RoleEntity;
import com.example.demo.Security_core.Perzistent2.RoleRepository;
import com.example.demo.Security_core.Service2.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationService authenticationService;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }



    private static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public Long registerUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username is already in use.");
        }
        if (userRepository.existsByemail(userDTO.getEmail()) && isValidEmail(userDTO.getEmail())){
            throw new IllegalArgumentException("Email is already in use");
        }

        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
            // Vytvorenie roly "ROLE_USER", ak neexistuje
        RoleEntity role = roleRepository.findByRoleName("ROLE_USER");
        System.out.println("rola dpic" + roleRepository.findByRoleName("ROLE_USER"));
        user.getRoles().add(role);

        System.out.println("registracia role " + user.getRoles());
        if (isValidPassword(userDTO.getPassword())) {
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(encodedPassword);
        }
        userRepository.save(user);
        //generateTokenForUser(userDTO.getUsername());
        return user.getId();
    }


    private static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null){
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }
    public UserDTO GetRegistration(Long id){
        Optional<UserEntity> opt = userRepository.findById(id);
        if (opt.isEmpty()){
            return null;
        }
        UserEntity entity = opt.get();
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }
    //@PreAuthorize("hasRole('ROLE_USER')")
    public String PostLogin(UserDTO userDTO) { //string
         //Získanie používateľa z databázy podľa používateľského mena
        String token = authenticationService.authenticate(userDTO.getUsername(), userDTO.getPassword());

         //Ak autentifikácia bola úspešná, môžeme vrátiť používateľské ID
        if (token != null) {
            Optional<UserEntity> userOptional = userRepository.findByUsername(userDTO.getUsername());
            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                return token;
            }
        }

        // Ak autentifikácia zlyhala, vrátime null
        return null;
    }
   // @PreAuthorize("hasRole('ROLE_USER')")
    public UserDTO GetLogin(Long id){
        // Overíme platnosť autentifikačného tokenu
        //authentificationService.authenticate(token);

        // Ak je token platný, môžeme získať údaje o používateľovi s daným ID
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            UserDTO userdto = new UserDTO();
            userdto.setId(userEntity.getId());
            userdto.setUsername(userEntity.getUsername());
            userdto.setPassword(userEntity.getPassword());
            userdto.setEmail(userEntity.getEmail());
            return userdto;
        }
        return null;
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    public void ChangePassword(Long id, UserDTO userDTO){
            System.out.println("if ");
            Optional<UserEntity> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                UserEntity userEntity = userOptional.get();
                System.out.println("heslo pred zmenenim " + userEntity.getPassword());
                // Overíme, či je zadané nové heslo a zvalidujeme ho
                if (userDTO.getPassword() != null) {
                    if (isValidPassword(userDTO.getPassword())) {
                        // Ak je heslo platné, aktualizujem ho v databáze
                        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
                        userEntity.setPassword(encodedPassword);
                        userRepository.save(userEntity);
                        System.out.println("heslo po zmeneni " + userEntity.getPassword());
                    } else {
                        throw new IllegalArgumentException("Invalid password format");
                    }
                } else {
                    throw new IllegalArgumentException("New password not provided");
                }
            } else {
                throw new IllegalArgumentException("User with id " + id + " does not exist");
            }

    }
    @PreAuthorize("hasRole('ROLE_USER')")
    public String GetPassword(Long id, String token) {
        authenticationService.authenticate(token);
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            return userEntity.getPassword();
        } else {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }
    }

}
