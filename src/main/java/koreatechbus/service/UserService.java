package koreatechbus.service;

import static koreatechbus.enums.Role.USER;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import koreatechbus.auth.JWTProvider;
import koreatechbus.domain.Bookmark;
import koreatechbus.domain.User;
import koreatechbus.dto.etc.MainDTO;
import koreatechbus.dto.user.LoginDTO;
import koreatechbus.dto.user.RegisterDTO;
import koreatechbus.enums.Role;
import koreatechbus.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;
    private static final String EMAIL_PATTERN =
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public UserService(UserRepository userRepository, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public User registerUser(RegisterDTO registerDTO) throws IllegalAccessException {
        isValidStudentNumber(registerDTO.schoolId());
        isValidEmail(registerDTO.email());
        isValidRegister(registerDTO);

        String password = hashPassword(registerDTO.password());

        return userRepository.save(User.builder()
            .schoolId(registerDTO.schoolId())
            .name(registerDTO.name())
            .password(password)
            .email(registerDTO.email())
            .role(USER)
            .build());
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public void isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("이메일의 형식이 올바르지 않습니다!");
        }
    }

    private void isValidStudentNumber(String studentNumber) {    // 정규 표현식을 사용하여 10자리 숫자인지 확인
        String regex = "^\\d{10}$";
        if (!studentNumber.matches(regex)) {
            throw new IllegalArgumentException("학번이 형식에 맞지 않습니다!");
        }
    }

    private void isValidRegister(RegisterDTO registerDTO) throws IllegalAccessException {
        if (userRepository.existsBySchoolId(registerDTO.schoolId())) {
            throw new IllegalAccessException("이미 존재하는 학번입니다!");
        }

        if (userRepository.existsByEmail(registerDTO.email())) {
            throw new IllegalAccessException("이미 존재하는 이메일입니다!");
        }
    }

    public String loginUser(LoginDTO loginDTO) {
        User user = userRepository.findBySchoolId(loginDTO.schoolId());

        if (!checkPass(loginDTO.password(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다!");
        }

        Integer schoolId = Integer.parseInt(loginDTO.schoolId());
        return jwtProvider.createToken(schoolId);
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {

        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public MainDTO getMainPage(String token) {
        String schoolId = jwtProvider.getSchoolId(token);   // 토큰에서 schoolId 추출
        User user = userRepository.findBySchoolId(schoolId);

        String name = user.getName();
        Role role = user.getRole();
        List<Bookmark> bookmarks = user.getBookmarks();

        return new MainDTO(name, schoolId, role, bookmarks);
    }
}

