package rs.ac.uns.ftn.informatika.svtprojekat.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "report")
@Data
public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "report_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "reason", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportReasonENUM reason;

    @Column(name = "timestamp", nullable = false)
    private LocalDate timestamp;

    @Column(name = "accepted", nullable = false)
    private boolean accepted;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User byUser;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id", nullable = false)
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    private Post post;

    public Report() {
    }

}
