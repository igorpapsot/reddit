package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.User;

import java.time.LocalDate;

@Data
public class RegisterDTO {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private String avatar;

    private LocalDate registrationDate;

    private boolean isBanned;

    private String newPassword;

    public RegisterDTO() {
    }

    public RegisterDTO(Integer id, String username, String password, String email, String avatar, LocalDate registrationDate, boolean isBanned) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = registrationDate;
        this.isBanned = isBanned;
        this.newPassword = newPassword;
    }

    public RegisterDTO(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getAvatar(), user.getRegistrationDate(), user.isBanned());
    }
}
