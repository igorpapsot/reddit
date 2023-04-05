package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.User;

@Data
public class LoginDTO {

    private Integer id;

    private String username;

    private String password;

    public LoginDTO() {
    }

    public LoginDTO(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public LoginDTO(User user) {
        this(user.getId(), user.getUsername(), user.getPassword());
    }
}
