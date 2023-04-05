package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.RoleENUM;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.User;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private Integer id;

    private String username;

    private String avatar;

    private boolean isBanned;

    private RoleENUM role;

    private int karma;

    private String description;

    private String displayName;

    public UserDTO() {
    }

    public UserDTO(Integer id, String username, String avatar, boolean isBanned, RoleENUM role, String description, String displayName) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.isBanned = isBanned;
        this.role = role;
        this.description = description;
        this.displayName = displayName;
    }

    public UserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getAvatar(), user.isBanned(), user.getRole(), user.getDescription(), user.getDisplayName());
    }
}
