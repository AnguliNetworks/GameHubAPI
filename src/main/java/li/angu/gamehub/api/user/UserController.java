package li.angu.gamehub.api.user;

import li.angu.gamehub.api.confirmation.Confirmation;
import li.angu.gamehub.api.confirmation.ConfirmationRepository;
import li.angu.gamehub.api.confirmation.ConfirmationType;
import li.angu.gamehub.api.mail.MailService;
import li.angu.gamehub.api.mail.templates.SignUpConfirmationTemplate;
import li.angu.gamehub.api.response.ApiSuccess;
import li.angu.gamehub.api.user.error.*;
import li.angu.gamehub.api.user.password.PasswordService;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*************************************************************************
 *
 * ANGULI NETWORKS CONFIDENTIAL
 * __________________
 *
 *  [2014] - [2018] Anguli Networks 
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Anguli Networks. The intellectual and 
 * technical concepts contained herein are proprietary to 
 * Anguli Networks and may be covered by German/EU and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Anguli Networks
 *
 * This File belongs to the GameHubAPI from Anguli Networks
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ConfirmationRepository confirmationRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/session-check", produces = "application/json")
    public ResponseEntity<Object> login(@RequestParam String userId, @RequestParam String session) {

        if (!userRepository.existsByIdAndSession(userId, session)) {
            throw new SessionNotFoundException();
        }

        ApiSuccess success = new ApiSuccess(HttpStatus.ACCEPTED, "Du bist nun eingeloggt!");

        return new ResponseEntity<>(success, new HttpHeaders(), success.getStatus());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "application/json")
    public ResponseEntity<Object> login(@RequestParam(required = false) @Email String mail, @RequestParam(required = false) String username, @RequestParam String password) {
        if (mail == null && username == null) {
            throw new NoMailOrUsernameFoundException();
        }

        User user = (mail != null ? userRepository.findByMail(mail) : userRepository.findByUsername(username)).orElseThrow(() -> new UserNotFoundException(mail != null ? mail : username));

        if (user.getSession() == null) {
            throw new AccountNotConfirmedException();
        }

        if (!this.passwordService.comparePassword(password, user.getPassword())) {
            throw new WrongPasswordException();
        }

        user.generateSession();

        userRepository.save(user);
        user.obfuscatePassword();

        ApiSuccess success = new ApiSuccess(HttpStatus.ACCEPTED, "Du bist nun angemeldet. Viel Spaß!", user);

        return new ResponseEntity<>(success, new HttpHeaders(), success.getStatus());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register", produces = "application/json")
    public ResponseEntity<Object> register(@RequestParam @Email String mail, @RequestParam String username, @RequestParam String password) {

        if (userRepository.existsByMail(mail)) {
            throw new MailAlreadyRegisteredException(mail);
        }

        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyRegisteredException(username);
        }

        passwordService.isSecure(password);

        User user = new User(mail, username, passwordService.encodePassword(password));

        userRepository.save(user);

        Map<String, String> values = new HashMap<>();
        Confirmation confirmation = new Confirmation(ConfirmationType.REGISTRATION, user);

        confirmationRepository.save(confirmation);

        values.put("confirmationId", confirmation.getId());

        mailService.sendMail(mail, "Bestätige Deinen GameHubOne Account", new SignUpConfirmationTemplate().getHTML(values));

        ApiSuccess success = new ApiSuccess(HttpStatus.CREATED, "Dein Account wurde angelegt. Jetzt noch schnell in Deine Mails schauen und auf den Link klicken :)");

        return new ResponseEntity<>(success, new HttpHeaders(), success.getStatus());
    }
}
