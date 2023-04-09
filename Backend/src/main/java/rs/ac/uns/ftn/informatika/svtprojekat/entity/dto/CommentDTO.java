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

    private String postId;

    private int karma;

    public CommentDTO() {
    }

    public CommentDTO(Integer id, String text, LocalDate timestamp, boolean isDeleted, UserDTO user, String postId) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.isDeleted = isDeleted;
        this.user = user;
        this.postId = postId;
    }

    public CommentDTO(Comment comment) {
        this(comment.getId(), comment.getText(), comment.getTimestamp(), comment.isDeleted(), new UserDTO(comment.getUser()) , comment.getPostId());
    }


}
