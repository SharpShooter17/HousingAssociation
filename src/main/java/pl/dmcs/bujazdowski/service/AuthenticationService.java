package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.controller.model.UserModel;
import pl.dmcs.bujazdowski.dao.RoleRepository;
import pl.dmcs.bujazdowski.dao.UserRepository;
import pl.dmcs.bujazdowski.domain.*;
import pl.dmcs.bujazdowski.exception.AuthorizationException;
import pl.dmcs.bujazdowski.exception.UserAlreadyExists;
import pl.dmcs.bujazdowski.exception.UserNotFoundException;
import pl.dmcs.bujazdowski.factory.UserFactory;
import pl.dmcs.bujazdowski.security.OnlyAdministrator;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private final static Logger log = Logger.getLogger(AuthenticationService.class.getName());

    private final UserFactory userFactory;
    private final MailSenderService mailSenderService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthenticationService(MailSenderService mailSenderService,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 RoleRepository roleRepository) {
        this.mailSenderService = mailSenderService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userFactory = new UserFactory(this);
    }

    public void activateAccount(Long userId, String token, String password) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.activateUser(token, passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Transactional
    @OnlyAdministrator
    public void registration(UserModel usermodel) {
        userRepository.findByEmail(usermodel.getEmail())
                .ifPresent(userExists -> {
                    throw new UserAlreadyExists(userExists);
                });

        User user = userFactory.createNewUser(usermodel);
        userRepository.save(user);

        RegistrationMailTemplate mail = new RegistrationMailTemplate(user);
        mailSenderService.sendMail(mail);
        log.info("Registered new user: " + user.toString());
    }

    public User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
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

    @Transactional
    public void edition(Long userId, UserModel userModel) {
        validateIfCurrentUserOrAdministrator(userId);
        User user = findUser(userId);
        mapUser(userModel, user);
        userRepository.save(user);
    }

    public boolean isCurrentUserOrAdministrator(Long userId) {
        User currentUser = currentUser();
        return currentUser.getId().equals(userId) ||
                currentUser.getRoles().stream().anyMatch(role -> role.getName().equals(RoleType.ADMINISTRATOR));
    }

    public void validateIfCurrentUserOrAdministrator(Long userId) {
        if (!isCurrentUserOrAdministrator(userId)) {
            throw new AuthorizationException(currentUser());
        }
    }

    @Transactional
    @OnlyAdministrator
    public void remove(Long userId) {
        userRepository.delete(userId);
    }

    public void mapUser(UserAppI from, UserAppI to) {
        to.setEmail(from.getEmail());
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setTelephone(from.getTelephone());
        to.getRoles().clear();
        Set<Role> roles = findRoles(
                from.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
        roles.forEach(to::addRole);
    }
}
