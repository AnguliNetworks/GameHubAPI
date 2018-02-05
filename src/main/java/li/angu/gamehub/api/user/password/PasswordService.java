package li.angu.gamehub.api.user.password;

import li.angu.gamehub.api.user.error.PasswordNotSecureException;
import org.passay.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.StringJoiner;

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
@Service
public class PasswordService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public void isSecure(String password) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 255),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return;
        }

        StringJoiner stringJoiner = new StringJoiner(" ");

        validator.getMessages(result).forEach(stringJoiner::add);

        throw new PasswordNotSecureException(stringJoiner.toString());
    }

    public boolean comparePassword(String password, String hash) {
        return passwordEncoder.matches(password, hash);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
