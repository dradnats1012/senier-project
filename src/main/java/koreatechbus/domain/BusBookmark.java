package koreatechbus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "bus_bookmark")
@Getter
public class BusBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long bookmarkId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @Builder
    public BusBookmark(User user, Bus bus) {
        this.user = user;
        this.bus = bus;
    }

    public Long getBusId() {
        return bus.getBusId();
    }

    public BusBookmark() {

    }
}
