package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Comment;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CommentDTO implements Serializable {

    private Integer id;

    private String text;

    private LocalDate timestamp;

    private boolean isDeleted;

    private UserDTO user;

    private PostDTO post;

    private int karma;

    public CommentDTO() {
    }

    public CommentDTO(Integer id, String text, LocalDate timestamp, boolean isDeleted, UserDTO user, PostDTO post) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.isDeleted = isDeleted;
        this.user = user;
        this.post = post;
    }

    public CommentDTO(Comment comment) {
        this(comment.getId(), comment.getText(), comment.getTimestamp(), comment.isDeleted(), new UserDTO(comment.getUser()) , new PostDTO(comment.getPost()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

}
