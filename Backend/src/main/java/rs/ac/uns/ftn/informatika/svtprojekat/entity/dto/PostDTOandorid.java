package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;

import java.time.LocalDate;

@Data
public class PostDTOandorid {

    private Integer id;

    private String title;

    private String text;

    //private LocalDate creationDate;

    private String imagePath;

    private Integer userId;

    private String flairName;

    private String communityName;

    private Integer communityId;

    private Integer flairId;

    private int karma;

    private String username;

    public PostDTOandorid() {
    }

    public PostDTOandorid(Integer id, String title, String text, LocalDate creationDate, String imagePath, UserDTO user, FlairDTO flair, CommunityDTO community) {
        this.id = id;
        this.title = title;
        this.text = text;
        //this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.userId = user.getId();
        this.flairName = flair.getName();
        this.communityName = community.getName();
        this.communityId = community.getId();
        this.flairId = flair.getId();
        this.username = user.getUsername();
    }

    public PostDTOandorid(Post post) {
        this(post.getId(), post.getTitle(), post.getText(), post.getCreationDate(), post.getImagePath(), new UserDTO(post.getUser()), new FlairDTO(post.getFlair()), new CommunityDTO(post.getCommunity()) );

    }
}
