package koreatechbus.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "seats")
@Getter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "seat_number")
    private Long seatNum;

    @Column(name = "is_used")
    private Boolean isUsed = false;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Seat(Long seatNum, Bus bus) {
        this.seatNum = seatNum;
        this.bus = bus;
    }

    public void chooseSeat(User user){
        this.user = user;
        this.isUsed = true;
    }

    public void cancelSeat(){
        this.user = null;
        this.isUsed = false;
    }

    public Seat (){
    }
}
