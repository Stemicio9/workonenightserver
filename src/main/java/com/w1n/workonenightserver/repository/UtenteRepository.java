package com.w1n.workonenightserver.repository;

import com.w1n.workonenightserver.model.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends MongoRepository<Utente, String> {

    Utente findByEmail(String email);
    Utente findByUid(String uid);

}
