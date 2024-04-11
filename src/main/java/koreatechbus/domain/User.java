package koreatechbus.domain;

import jakarta.persistence.*;
import koreatechbus.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "school_id")
    private String schoolId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;      // 1 : 관리자, 2 : 버스 기사, 3 : 일반 사용자

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Builder
    public User(String schoolId, String name, String password, String email, Role role){
        this.schoolId = schoolId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User() {
    }
}
