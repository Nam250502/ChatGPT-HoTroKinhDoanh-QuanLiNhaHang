package nam.nguyen.store.service;



import lombok.extern.slf4j.Slf4j;

import nam.nguyen.store.model.Role;
import nam.nguyen.store.model.User;
import nam.nguyen.store.repository.RoleRepository;
import nam.nguyen.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository repo;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User getUserByUserName(String username){
        User user = userRepository.getUserByUsername(username);
        return user;
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public void register(User user , Integer roleId) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleService.geRoleById(roleId);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }
    public void removeUser(Integer iduser) {
        userRepository.deleteById(iduser);
    }

}
