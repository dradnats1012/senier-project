package koreatechbus.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "post")
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "post_time")
    private String postTime;

    @Column(name = "post_type") // 1 : 공지사항, 2 : 분실물, 3 : 자유
    private Long postType;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "anonymous")
    private Boolean anonymous;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(String title, String content, String postTime, Long postType, String imageUrl, Boolean anonymous, User user) {
        this.title = title;
        this.content = content;
        this.postTime = postTime;
        this.postType = postType;
        this.imageUrl = imageUrl;
        this.anonymous = anonymous;
        this.user = user;
    }

    public Post() {

    }
}
