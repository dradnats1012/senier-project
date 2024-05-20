package koreatechbus.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String title;

    @Lob
    @Column(name = "content")
    @NotNull
    private String content;

    @Column(name = "post_time")
    private String postTime;

    @Column(name = "post_type") // 1 : 공지사항, 2 : 분실물, 3 : 자유
    @NotNull
    private Long postType;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "attachment_url")
    private String attachmentUrl;

    @Column(name = "anonymous")
    private Boolean anonymous;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(String title, String content, String postTime, Long postType, String imageUrl, String fileName, String attachmentUrl,
        Boolean anonymous, User user) {
        this.title = title;
        this.content = content;
        this.postTime = postTime;
        this.postType = postType;
        this.imageUrl = imageUrl;
        this.fileName = fileName;
        this.attachmentUrl = attachmentUrl;
        this.anonymous = anonymous;
        this.user = user;
    }

    public Post() {

    }
}
