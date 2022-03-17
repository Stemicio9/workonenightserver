package com.w1n.workonenightserver.repository;

import com.w1n.workonenightserver.model.DatiUtente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatiUtenteRepository extends MongoRepository<DatiUtente,String> {
    DatiUtente findByUid(String uid);
    List<DatiUtente> findAllByNomeutenteContains(String contained);

    // QUESTA QUERY SERVE PER RENDERE LA RICERCA CASE INSENSITIVE
    @Query(value = "{'nomeutente': {$regex : ?0, $options: 'i'}}")
    List<DatiUtente> findAllByNomeutenteRegex(String regex);

}
