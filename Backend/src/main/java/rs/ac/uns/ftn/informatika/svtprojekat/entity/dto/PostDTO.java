package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PostDTO implements Serializable {

    private String id;

    private String title;

    private String text;

    private LocalDate creationDate;

    private String imagePath;

    private Integer userId;

    private FlairDTO flair;

    private String communityId;

    private int karma;

    public PostDTO() {
    }

    public PostDTO(String id, String title, String text, LocalDate creationDate, Integer userId, FlairDTO flair, String communityId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.userId = userId;
        this.flair = flair;
        this.communityId = communityId;
    }

    public PostDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getText(), post.getCreationDate(), post.getUserId(), new FlairDTO(post.getFlair()), post.getCommunityId());

    }
}
