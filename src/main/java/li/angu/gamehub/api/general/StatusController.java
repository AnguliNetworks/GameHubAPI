package li.angu.gamehub.api.general;

import li.angu.gamehub.api.mail.MailService;
import li.angu.gamehub.api.response.ApiSuccess;
import li.angu.gamehub.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class StatusController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/status")
    public ResponseEntity<Object> getStatus() {

        userRepository.findOne("not-existing-id");

        ApiSuccess success = new ApiSuccess(HttpStatus.OK, "Alle Dienste sind online");

        return new ResponseEntity<>(success, new HttpHeaders(), success.getStatus());
    }

}
