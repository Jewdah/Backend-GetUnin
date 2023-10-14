package getunin.example.Backend.GetUnin.service;

import getunin.example.Backend.GetUnin.entity.UserLogin;
import getunin.example.Backend.GetUnin.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Value("${created}")
    private Long create;

    @Value("${deleted}")
    private Long delete;

    @Value("${disabled}")
    private Long disable;

    @Value("${changePassword}")
    private Long changePasswords;

    @Value("${default.profile}")
    private Long parameterImage;

    @Value("${jpg.Png.Jpeg}")
    private Long typeImage;

    @Value("${driver}")
    private Long driver;

    @Value("${passenger}")
    private Long passenger;

    @Value("${admin.platfoxrm}")
    private Long adminPlatform;

    @Value("${admin.Company}")
    private Long adminCompany;

    @Value("${SUBJECTRECOVERY}")
    private String subjectRecovery;

    private final UserLoginRepository userLoginRepository;
    private final MessageSource messageSource;

    private final PasswordEncoder bcryptEncoder;

    public JwtUserDetailsService(UserLoginRepository userLoginRepository, MessageSource messageSource, PasswordEncoder bcryptEncoder) {
        this.userLoginRepository = userLoginRepository;

        this.messageSource = messageSource;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserLogin user = userLoginRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

}
