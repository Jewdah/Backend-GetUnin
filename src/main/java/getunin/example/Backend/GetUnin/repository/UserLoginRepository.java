package getunin.example.Backend.GetUnin.repository;

import getunin.example.Backend.GetUnin.entity.User;
import getunin.example.Backend.GetUnin.entity.UserLogin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends CrudRepository<UserLogin, Long> {

    User findByUserLogin(String username);

    UserLogin findByUsername(String email);

    UserLogin findUserLoginsByUserLogin_Id(Long id);

    void deleteByUsername(String username);
}

