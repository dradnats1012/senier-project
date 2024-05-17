package koreatechbus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "comment")
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "post_time")
    private String postTime;

    @Column(name = "anonymous")
    private Boolean anonymous;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "display_name")
    private String displayName;

    @Builder
    public Comment(String content, String postTime, Boolean anonymous, Post post, User user, String displayName) {
        this.content = content;
        this.postTime = postTime;
        this.anonymous = anonymous;
        this.post = post;
        this.user = user;
        this.displayName = displayName;
    }

    public Comment() {

    }
}
