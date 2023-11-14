package com.getunin.service;

import com.getunin.configuration.MailSenderConfig;
import com.getunin.dto.CreateUserLoginRequest;
import com.getunin.dto.EmailContent;
import com.getunin.dto.JwtRequest;
import com.getunin.entity.Parameter;
import com.getunin.entity.User;
import com.getunin.entity.UserLogin;
import com.getunin.exception.listexceptions.ConflictException;
import com.getunin.exception.listexceptions.NotFoundException;
import com.getunin.repository.UserLoginRepository;
import com.getunin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

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


    private final UserLoginRepository userLoginRepository;
    private final MessageSource messageSource;

    private final UserRepository repository;
    private final PasswordEncoder bcryptEncoder;

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserLoginRepository userLoginRepository, MessageSource messageSource, EmailService emailService, UserRepository repository, PasswordEncoder bcryptEncoder, MailSenderConfig mailSenderConfig, UserRepository userRepository) {
        this.userLoginRepository = userLoginRepository;

        this.messageSource = messageSource;
        this.repository = repository;
        this.bcryptEncoder = bcryptEncoder;
        this.userRepository = userRepository;
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

    public User createUser(JwtRequest user) {
        Optional<User> userDb = Optional.ofNullable(repository.findByIdentification(user.getIdentification()));
        Optional<User> userDbEmail = Optional.ofNullable(repository.findByEmail(user.getEmail()));

        if (userDb.isPresent() && userDbEmail.isPresent()) {
            if (!Objects.equals(userDb.get().getId(), userDbEmail.get().getId())) {
                throw new ConflictException(messageSource.getMessage("email.User.Exist", null,"", LocaleContextHolder.getLocale()));
            }else if (!Objects.equals(userDb.get().getStatus().getId(), disable) || !Objects.equals(userDb.get().getStatus().getId(), delete)) {
                throw new ConflictException(messageSource.getMessage("user.Exist",null, "", LocaleContextHolder.getLocale()));
            }else {
                User userTop = repository.save(jwtUserRequest(user,userDb.get()));

                createUserLogin(new CreateUserLoginRequest(userTop.getEmail(), user.getPassword()), userTop);

                return userTop;
            }
        }else if (userDb.isPresent()) {
            if (!Objects.equals(userDb.get().getStatus().getId(), disable) || !Objects.equals(userDb.get().getStatus().getId(), delete)) {
                throw new ConflictException(messageSource.getMessage("user.Exist",null, "", LocaleContextHolder.getLocale()));
            }else {
                User userTop = repository.save(jwtUserRequest(user,userDb.get()));

                createUserLogin(new CreateUserLoginRequest(userTop.getEmail(), user.getPassword()), userTop);

                return userTop;
            }
        } else if (userDbEmail.isPresent()) {
            if (!Objects.equals(userDbEmail.get().getStatus().getId(), disable) || !Objects.equals(userDbEmail.get().getStatus().getId(), delete)) {
                throw new ConflictException(messageSource.getMessage("user.Exist",null, "", LocaleContextHolder.getLocale()));
            }else {

                User userTop = repository.save(jwtUserRequest(user,userDbEmail.get()));

                createUserLogin(new CreateUserLoginRequest(userTop.getEmail(), user.getPassword()), userTop);

                return userTop;
            }
        }else {

            User userTop = repository.save(jwtUserRequest(user,new User()));

            createUserLogin(new CreateUserLoginRequest(userTop.getEmail(), user.getPassword()), userTop);

            return userTop;
        }
    }

    public User updateUserGeneral(JwtRequest request, Long id){

        Optional<User> userDb = Optional.ofNullable(repository.findByIdentification(request.getIdentification()));
        Optional<User> userEmail = Optional.ofNullable(repository.findByEmail(request.getEmail()));
        Optional<User> userId = repository.findById(id);
        Optional<UserLogin> userLogin = Optional.ofNullable(userLoginRepository.findUserLoginsByUserLogin_Id(id));

        if (userId.isPresent() && userLogin.isPresent()){
            if (userDb.isPresent() && userEmail.isPresent()){
                if (Objects.equals(userDb.get().getId(), userId.get().getId())){
                    if(Objects.equals(userEmail.get().getId(), userId.get().getId()) &&
                            Objects.equals(userDb.get().getId(), userEmail.get().getId())){

                        User userUpdate = userId.get();
                        userUpdate.setIdentification(request.getIdentification());
                        userUpdate.setIdentificationType(request.getIdentificationType());
                        userUpdate.setFirstName(request.getFirstName());
                        userUpdate.setLastName(request.getLastName());
                        userUpdate.setPhoneNumber(request.getPhoneNumber());
                        userUpdate.setEmail(request.getEmail());
                        userUpdate.setStatus(request.getStatus());
                        userUpdate.setRoleId(request.getRoleId());
                        userUpdate.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));

                        UserLogin userdbLogin = userLogin.get();
                        userdbLogin.setUsername(request.getEmail());
                        userLoginRepository.save(userdbLogin);


                        return repository.save(userUpdate);
                    }else {
                        throw new ConflictException(messageSource.getMessage("email.Exist",null,"",LocaleContextHolder.getLocale()));
                    }
                }else {
                    throw new ConflictException(messageSource.getMessage("number.User.Exist",null,"",LocaleContextHolder.getLocale()));
                }
            } else if (userDb.isPresent()) {
                if (Objects.equals(userId.get().getId(), userDb.get().getId())){
                    User userUpdate = userId.get();
                    userUpdate.setIdentification(request.getIdentification());
                    userUpdate.setIdentificationType(request.getIdentificationType());
                    userUpdate.setFirstName(request.getFirstName());
                    userUpdate.setLastName(request.getLastName());
                    userUpdate.setPhoneNumber(request.getPhoneNumber());
                    userUpdate.setEmail(request.getEmail());
                    userUpdate.setRoleId(request.getRoleId());
                    userUpdate.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));

                    UserLogin userdbLogin = userLogin.get();
                    userdbLogin.setUsername(request.getEmail());
                    userLoginRepository.save(userdbLogin);

                    User userTop = repository.save(userUpdate);
                    createUserLogin(new CreateUserLoginRequest(userTop.getEmail(),userTop.getIdentification()),userTop);
                    return userTop;
                }else {
                    throw new NotFoundException(messageSource.getMessage("number.User.Exist",null,"", LocaleContextHolder.getLocale()));
                }
            }else if (userEmail.isPresent()) {
                if (Objects.equals(userId.get().getId(), userEmail.get().getId())){

                    User userUpdate = userId.get();
                    userUpdate.setIdentification(request.getIdentification());
                    userUpdate.setIdentificationType(request.getIdentificationType());
                    userUpdate.setFirstName(request.getFirstName());
                    userUpdate.setLastName(request.getLastName());
                    userUpdate.setPhoneNumber(request.getPhoneNumber());
                    userUpdate.setEmail(request.getEmail());
                    userUpdate.setStatus(request.getStatus());

                    UserLogin userdbLogin = userLogin.get();
                    userdbLogin.setUsername(request.getEmail());
                    userLoginRepository.save(userdbLogin);

                    userUpdate.setRoleId(request.getRoleId());
                    userUpdate.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));

                    return repository.save(userUpdate);
                }else {
                    throw new NotFoundException(messageSource.getMessage("EMAIL.USER.EXIST",null,"", LocaleContextHolder.getLocale()));
                }
            }else {

                User userUpdate = userId.get();
                userUpdate.setIdentification(request.getIdentification());
                userUpdate.setIdentificationType(request.getIdentificationType());
                userUpdate.setFirstName(request.getFirstName());
                userUpdate.setLastName(request.getLastName());
                userUpdate.setPhoneNumber(request.getPhoneNumber());
                userUpdate.setEmail(request.getEmail());
                userUpdate.setStatus(request.getStatus());

                UserLogin userdbLogin = userLogin.get();
                userdbLogin.setUsername(request.getEmail());
                userLoginRepository.save(userdbLogin);

                userUpdate.setRoleId(request.getRoleId());
                userUpdate.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));

                User userTop = repository.save(userUpdate);

                repository.save(userUpdate);

                return repository.save(userUpdate);
            }
        }else {
            throw new NotFoundException(messageSource.getMessage("user.Not.Found",null,"", LocaleContextHolder.getLocale()));
        }
    }

    public User createUserLogin(CreateUserLoginRequest user, User userReturn) {
        Optional<UserLogin> userLogin = Optional.ofNullable(userLoginRepository.findByUsername(user.getUsername()));

        if (userLogin.isEmpty()){
            userReturn.setStatus(new Parameter(changePasswords));
            User userTp = userRepository.save(userReturn);
            UserLogin userSave = new UserLogin();
            userSave.setUsername(userReturn.getEmail());
            userSave.setPassword(bcryptEncoder.encode( user.getPassword()));
            userSave.setUserLogin(userTp);
            userLoginRepository.save(userSave);

            return userTp;
        }else {

            userReturn.setStatus(new Parameter(changePasswords));
            User userTp = userRepository.save(userReturn);
            UserLogin userSave = userLogin.get();
            userSave.setUsername(userReturn.getEmail());
            userSave.setPassword(bcryptEncoder.encode( user.getPassword()));
            userSave.setUserLogin(userTp);
            userLoginRepository.save(userSave);

            return userTp;
        }
    }

    private User jwtUserRequest(JwtRequest user, User userU){

        userU.setIdentification(user.getIdentification());
        userU.setIdentificationType(user.getIdentificationType());
        userU.setFirstName(user.getFirstName());
        userU.setLastName(user.getLastName());
        userU.setPhoneNumber(user.getPhoneNumber());
        userU.setEmail(user.getEmail());

        Parameter status = new Parameter();
        status.setId(create);
        userU.setStatus(status);
        userU.setRoleId(user.getRoleId());
        userU.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));

        return userU;
    }


}
