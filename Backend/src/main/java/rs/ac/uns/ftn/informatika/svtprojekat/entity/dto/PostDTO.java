package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PostDTO implements Serializable {

    private Integer id;

    private String title;

    private String text;

    private LocalDate creationDate;

    private String imagePath;

    private UserDTO user;

    private FlairDTO flair;

    private CommunityDTO community;

    private int karma;

    public PostDTO() {
    }

    public PostDTO(Integer id, String title, String text, LocalDate creationDate, String imagePath, UserDTO user, FlairDTO flair, CommunityDTO community) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.user = user;
        this.flair = flair;
        this.community = community;
    }

    public PostDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getText(), post.getCreationDate(), post.getImagePath(), new UserDTO(post.getUser()), new FlairDTO(post.getFlair()), new CommunityDTO(post.getCommunity()) );

    }
}
