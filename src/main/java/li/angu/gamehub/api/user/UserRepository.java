package li.angu.gamehub.api.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

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
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsernameIgnoreCase(String username);

    Optional<User> findByMailIgnoreCase(String mail);

    Optional<User> findById(String id);

    boolean existsByIdAndSession(String id, String sessino);

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByMailIgnoreCase(String mail);

}
