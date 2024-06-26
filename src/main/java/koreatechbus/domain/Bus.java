package koreatechbus.domain;

import jakarta.persistence.*;
import koreatechbus.enums.Days;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bus")
@Getter
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Long busId;

    @Column(name = "name")
    private String name;

    @Column(name = "depart_Time")
    private String departTime;

    @Column(name = "arrival_Time")
    private String arrivalTime;

    @Column(name = "passengers")
    private Long passengers;

    @Column(name = "bookmark_num")      // 북마크로 등록한 사용자 수
    private Long bookmarkNum;

    @Column(name = "latitude")          // 위도
    private double latitude;

    @Column(name = "longitude")         // 경도
    private double longitude;

    @Column(name = "is_run", columnDefinition = "boolean default false")            // 운행여부
    private Boolean isRun = false;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    @Column(name = "run_days")
    private List<Days> runDays = new ArrayList<>();

    /*@OneToMany(mappedBy = "bus")          // 버스에서 이걸 가질 필요가 있을까????
    private Set<Bookmark> bookmarks = new HashSet<>();*/

    @Builder
    public Bus(String name, String departTime, String arrivalTime, List<Days> runDays) {
        this.name = name;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.runDays = runDays;
    }

    public Bus() {

    }

    public void plusBookmarkNum() {
        this.bookmarkNum += 1;
    }

    public void minusBookmarkNum() {
        this.bookmarkNum -= 1;
    }

    @PrePersist
    public void prePersist() {
        if (passengers == null)
            passengers = 0L;
        if (bookmarkNum == null)
            bookmarkNum = 0L;
        isRun = false;
    }

    public void setPosition(Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setIsRun(Boolean isRun){
        this.isRun = isRun;
    }
}
