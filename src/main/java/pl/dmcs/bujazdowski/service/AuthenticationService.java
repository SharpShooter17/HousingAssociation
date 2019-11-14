package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.dao.RoleRepository;
import pl.dmcs.bujazdowski.dao.UserRepository;
import pl.dmcs.bujazdowski.domain.RegistrationMailTemplate;
import pl.dmcs.bujazdowski.domain.Role;
import pl.dmcs.bujazdowski.domain.RoleType;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.exception.UserAlreadyExists;
import pl.dmcs.bujazdowski.exception.UserNotFoundException;
import pl.dmcs.bujazdowski.factory.UserFactory;
import pl.dmcs.bujazdowski.security.OnlyAdministrator;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class AuthenticationService {

    private final static Logger log = Logger.getLogger(AuthenticationService.class.getName());

    private final UserFactory userFactory;
    private final MailSenderService mailSenderService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthenticationService(UserFactory userFactory,
                                 MailSenderService mailSenderService,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 RoleRepository roleRepository) {
        this.userFactory = userFactory;
        this.mailSenderService = mailSenderService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void activateAccount(Long userId, String token, String password) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.activateUser(token, passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Transactional
    @OnlyAdministrator
    public void registration(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(userExists -> {
                    throw new UserAlreadyExists(userExists);
                });

        userFactory.createNewUser(user);
        userRepository.save(user);

        RegistrationMailTemplate mail = new RegistrationMailTemplate(user);
        mailSenderService.sendMail(mail);
        log.info("Registered new user: " + user.toString());
    }

    public User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    public User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public Set<Role> findRoles(Set<RoleType> roleTypes) {
        return roleRepository.findAllByNameIn(roleTypes);
    }

    public Role findRole(RoleType roleType) {
        return roleRepository.findByName(roleType);
    }
}
