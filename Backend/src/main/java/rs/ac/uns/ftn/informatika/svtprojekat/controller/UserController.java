package rs.ac.uns.ftn.informatika.svtprojekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.User;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.dto.*;
import rs.ac.uns.ftn.informatika.svtprojekat.security.TokenUtils;
import rs.ac.uns.ftn.informatika.svtprojekat.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userService.findAll();

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User u : users) {
            System.out.println(u.toString());
            usersDTO.add(new UserDTO(u));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/account")
    public ResponseEntity<UserDTO> getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDTO retVal = new UserDTO(user);
        int karma = userService.getKarma(user);
        System.out.println(karma);
        retVal.setKarma(karma);

        return new ResponseEntity<>( retVal, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<RegisterDTO> register(@RequestBody RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setAvatar(registerDTO.getAvatar());
        user.setEmail(registerDTO.getEmail());
        user.setBanned(false);
        user.setPassword(registerDTO.getPassword());
        LocalDate date = LocalDate.now();
        user.setRegistrationDate(date);

        User usernameUser = userService.findUserByUsername(user.getUsername());

        if(user.getUsername() == null || user.getAvatar() == null ||
            user.getEmail() == null || user.getPassword() == null ||
             user.getRegistrationDate() == null) {
             return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if(usernameUser == null ) {
            System.out.println(user);
            user = userService.createUser(new RegisterDTO(user));
            return new ResponseEntity<>(new RegisterDTO(user), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @PutMapping(consumes = "application/json", value = "/changePassword")
    @CrossOrigin(origins = "http://localhost:4200")
    public HttpStatus changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());
        Integer userId = user.getId();
        if (userId == null) {
            return HttpStatus.NOT_FOUND;
        }
        else {
            if( passwordEncoder.matches(changePasswordDTO.getPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
                userService.save(user);
                return HttpStatus.ACCEPTED;
            }
            else {
                return HttpStatus.NOT_ACCEPTABLE;
            }
        }

    }

    @PostMapping("/login")
    public ResponseEntity<jwtDTO> loginSecurity(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
                System.out.println(tokenUtils.generateToken(userDetails));
                jwtDTO jwtDTO = new jwtDTO();
                jwtDTO.setJwt(tokenUtils.generateToken(userDetails));
            return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity changeUser(@RequestBody ChangeUserDTO changeUserDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());
        Integer userId = user.getId();
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            if(changeUserDTO.getEmail() != null) {
                user.setEmail(changeUserDTO.getEmail());
            }
            if(changeUserDTO.getUsername() != null) {
                user.setUsername(changeUserDTO.getUsername());
            }
            if(changeUserDTO.getAvatar() != null) {
                user.setAvatar(changeUserDTO.getAvatar());
            }
            user.setBanned(changeUserDTO.isBanned());
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(consumes = "text/plain", value = "/displayName")
    public HttpStatus changeDisplayName(@RequestBody String displayName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());
        Integer userId = user.getId();
        if (userId == null) {
            return HttpStatus.NOT_FOUND;
        }
        else {
            if(displayName.length() > 5) {
                user.setDisplayName(displayName);
                userService.save(user);
                return HttpStatus.ACCEPTED;
            }
            else {
                System.out.println(displayName.length());
                System.out.println(displayName);
                return HttpStatus.NOT_ACCEPTABLE;
            }

        }

    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(consumes = "text/plain", value = "/description")
    public HttpStatus changeDescription(@RequestBody String description) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());
        Integer userId = user.getId();
        if (userId == null) {
            return HttpStatus.NOT_FOUND;
        }
        else {
            if(description.length() > 10) {
                user.setDescription(description);
                userService.save(user);
                return HttpStatus.ACCEPTED;
            }
            else {
                return  HttpStatus.NOT_ACCEPTABLE;
            }
        }

    }
}