package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Reaction;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.ReactionTypeENUM;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ReactionDTO implements Serializable {

    private Integer id;

    private ReactionTypeENUM type;

    private LocalDate timestamp;

    private Integer userId;

    private Integer commentId;

    private Integer postId;

    public ReactionDTO() {
    }

    public ReactionDTO(Integer id, ReactionTypeENUM type, LocalDate timestamp, Integer userId, Integer commentId, Integer postId) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.userId = userId;
        this.commentId = commentId;
        this.postId = postId;
    }

    public ReactionDTO(Reaction reaction) {
        this(reaction.getId(), reaction.getType(), reaction.getTimestamp(), reaction.getUser().getId(), reaction.getComment().getId(), reaction.getPost().getId());

    }
}
