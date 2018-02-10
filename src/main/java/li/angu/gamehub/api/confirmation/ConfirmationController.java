package li.angu.gamehub.api.confirmation;

import li.angu.gamehub.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
@Controller
public class ConfirmationController {

    @Autowired
    private ConfirmationRepository confirmationRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/confirm/{typeName}/{id}")
    public ModelAndView confirm(@PathVariable String typeName, @PathVariable String id) {
        ConfirmationType confirmationType = ConfirmationType.valueOf(typeName.toUpperCase());
        Confirmation confirmation = confirmationRepository.findById(id).orElse(null);

        ModelAndView modelAndView = new ModelAndView("confirm");

        if (confirmation == null || confirmation.getType() != confirmationType) {
            return modelAndView.addObject("message", "Der Bestätigungslink ist ungültig. Bitte überprüfe, ob Du den Link richtig eingegeben hast.");
        }

        confirmation.getUser().generateSession();

        userRepository.save(confirmation.getUser());
        confirmationRepository.delete(confirmation);

        return modelAndView.addObject("message", "Vielen Dank, für die Bestätigung. Du kannst den Tab jetzt schließen.");
    }
}
