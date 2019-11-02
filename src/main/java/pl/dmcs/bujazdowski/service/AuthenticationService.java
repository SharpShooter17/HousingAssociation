package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.dao.UserRepository;
import pl.dmcs.bujazdowski.domain.RegistrationMailTemplate;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.domain.UserCredentials;
import pl.dmcs.bujazdowski.exception.UserAlreadyExists;
import pl.dmcs.bujazdowski.factory.UserFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class AuthenticationService implements UserDetailsService {

    private final static Logger log = Logger.getLogger(AuthenticationService.class.getName());

    private final UserFactory userFactory;
    private final MailSenderService mailSenderService;
    private final UserRepository userRepository;
    private Set<UserCredentials> users = new HashSet<>();

    @Autowired
    public AuthenticationService(UserFactory userFactory,
                                 MailSenderService mailSenderService,
                                 UserRepository userRepository) {
        this.userFactory = userFactory;
        this.mailSenderService = mailSenderService;
        this.userRepository = userRepository;
        this.users.add(new UserCredentials("admin@example.com", "password"));
    }

    public Boolean authenticateUser(UserCredentials userCredentials) {
        return users.contains(userCredentials);
    }

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
