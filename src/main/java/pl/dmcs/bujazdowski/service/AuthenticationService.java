package pl.dmcs.bujazdowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dmcs.bujazdowski.domain.RegistrationMailTemplate;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.domain.UserCredentials;
import pl.dmcs.bujazdowski.factory.UserFactory;
import pl.dmcs.bujazdowski.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class AuthenticationService {

    private final static Logger log = Logger.getLogger(AuthenticationService.class.getName());

    private final UserFactory userFactory;
    private final MailSenderService mailSenderService;
    private final UserRepository userRepository;
    private Set<UserCredentials> users = new HashSet<>();

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserFactory userFactory,
                                 MailSenderService mailSenderService,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userFactory = userFactory;
        this.mailSenderService = mailSenderService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.users.add(new UserCredentials("admin@example.com", "password"));
    }

    public Boolean authenticateUser(UserCredentials userCredentials) {
        return users.contains(userCredentials);
    }

    public void registration(User user) {
        userFactory.createNewUser(user);
        userRepository.saveUser(user);

        RegistrationMailTemplate mail = new RegistrationMailTemplate(user);
        mailSenderService.sendMail(mail);
        log.info("Registered new user: " + user.toString());
    }

}
