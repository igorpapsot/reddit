package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.User;

@Data
public class ChangeUserDTO {

    private Integer id;

    private String username;

    private String email;

    private String avatar;

    private boolean isBanned;

    public ChangeUserDTO() {
    }

    public ChangeUserDTO(Integer id, String username, String email, String avatar, boolean isBanned) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.isBanned = isBanned;
    }

    public ChangeUserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getAvatar(), user.isBanned());
    }
}
