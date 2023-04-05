package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.User;

@Data
public class ChangePasswordDTO {

    private String newPassword;

    private String password;

    private String repeatPassword;

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(String password) {
        this.password = password;
    }

    public ChangePasswordDTO(User user) {
        this(user.getPassword());
    }
}
