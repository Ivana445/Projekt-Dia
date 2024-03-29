package com.example.demo.Service;

import com.example.demo.Perzistent.UserEntity;
import com.example.demo.Perzistent.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;
    public Long userRegistration(UserDTO userDTO) {
        if (isUsernameAvailable(userDTO.getUsername()) && isEmailAvailable(userDTO.getEmail())) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(userDTO.getUsername());
            if (isValidEmail(userDTO.getEmail())) {
                userEntity.setEmail(userDTO.getEmail());
            }else {
                System.out.println("Zadaj platný email!");
                return null;
            }
            if (isValidPassword(userDTO.getPassword())) {
                userEntity.setPassword(userDTO.getPassword());
            }else {
                System.out.println("Zle heslo! Musíš v nom vyuzit 1 male pismeno, 1 velke pismeno, cislo a musi mat minimalne 8 znakov");
                return null;
            }
            userRepository.save(userEntity);
            return userEntity.getId();
        } else {
            System.out.println("Meno alebo email už používa iný používateľ");
            return null; // Vrátiť null, keď užívateľ alebo e-mail už existuje
        }

    }

    private boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    private boolean isEmailAvailable(String email) {
        return !userRepository.existsByemail(email);
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
    private static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
}
