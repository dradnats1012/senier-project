package koreatechbus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import static jakarta.persistence.FetchType.LAZY;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import static lombok.AccessLevel.PROTECTED;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "notification")
@NoArgsConstructor(access = PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    public Notification(
        String title,
        String message,
        User user
    ) {
        this.title = title;
        this.message = message;
        this.user = user;
    }

    public void read() {
        this.isRead = true;
    }
}
