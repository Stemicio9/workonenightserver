package com.w1n.workonenightserver.repository;

import com.w1n.workonenightserver.model.Annuncio;
import com.w1n.workonenightserver.model.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AnnunciRepository extends MongoRepository<Annuncio,String> {

    List<Annuncio> findAllByPubblicanteidAndDataAfter(Utente utente, Date data);

    List<Annuncio> findAllByPubblicanteidAndDataBefore(Utente utente,Date data);

}
