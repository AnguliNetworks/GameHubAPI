package li.angu.gamehub.api.user;

import li.angu.gamehub.api.response.ApiSuccess;
import li.angu.gamehub.api.user.error.MailAlreadyRegisteredException;
import li.angu.gamehub.api.user.error.UsernameAlreadyRegisteredException;
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

    @RequestMapping(method = RequestMethod.POST, value = "/register", produces = "application/json")
    public ResponseEntity<Object> register(@RequestParam @Email String mail, @RequestParam String username, @RequestParam String password) {

        if (userRepository.existsByMail(mail)) {
            throw new MailAlreadyRegisteredException(mail);
        }

        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyRegisteredException(username);
        }

        passwordService.isSecure(password);

        userRepository.save(new User(mail, username, passwordService.encodePassword(password)));

        // TODO ADD MAIL

        ApiSuccess success = new ApiSuccess(HttpStatus.CREATED, "Dein Account wurde angelegt. Jetzt noch schnell in Deine Mails schauen und auf den Link klicken :)");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return new ResponseEntity<>(success, httpHeaders, success.getStatus());
    }
}
