package com.w1n.workonenightserver.controller;

import com.w1n.workonenightserver.configuration.utils.CustomPrincipal;
import com.w1n.workonenightserver.model.Annuncio;
import com.w1n.workonenightserver.model.Skill;
import com.w1n.workonenightserver.service.AnnunciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("annunci")
public class AnnunciController {

    @Autowired
    private AnnunciService annunciService;


    @PostMapping("pubblica")
    public boolean pubblicaannuncio(@RequestBody Annuncio annuncio,  @AuthenticationPrincipal CustomPrincipal customPrincipal){
        return this.annunciService.pubblicaannuncio(annuncio,customPrincipal.getUid());
    }


    @PostMapping("cercaskill/{nomeskill}")
    public List<Skill> cercaskill(@PathVariable String nomeskill, @AuthenticationPrincipal CustomPrincipal customPrincipal){
        return annunciService.queryskills(nomeskill);
    }


    //@todo
    // Questo metodo è solo per aggiungere skill di prova
    @PostMapping("aggiungiskill")
    public boolean aggiungiskill(@RequestBody Skill skill, @AuthenticationPrincipal CustomPrincipal customPrincipal){
        return annunciService.aggiungiskill(skill);
    }



    // PER IL DATORE DI LAVORO
    @GetMapping("mieiannunciattivi")
    public List<Annuncio> mieiannunciattivi(@AuthenticationPrincipal CustomPrincipal customPrincipal){
        return annunciService.findannunci(customPrincipal.getUid(),false);
    }

    @GetMapping("mieiannuncimatchati")
    public List<Annuncio> mieiannuncimatchati(@AuthenticationPrincipal CustomPrincipal customPrincipal){
        return annunciService.findannunci(customPrincipal.getUid(),true);
    }

    @GetMapping("storicoannunci")
    public List<Annuncio> storicoannunci(@AuthenticationPrincipal CustomPrincipal customPrincipal){
        return annunciService.findstorico(customPrincipal.getUid());
    }

    // PER IL LAVORATORE
    @GetMapping("ricercaannunci")
    public List<Annuncio> ricercaannuncilavoratore(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                                   @RequestParam(required = false) List<String> skill,
                                                   @RequestParam(required = false) Date mindata,
                                                   @RequestParam(required = false) Date maxdata,
                                                   @RequestParam(required = false) double lat,
                                                   @RequestParam(required = false) double lng,
                                                   @RequestParam(required = false) double distanza,
                                                   @RequestParam(required = false) int oramin,
                                                   @RequestParam(required = false) int oramax,
                                                   @RequestParam(required = false) double pagamin,
                                                   @RequestParam(required = false) double pagamax){
        return annunciService.ricercaannunci(customPrincipal.getUid(),skill,mindata,maxdata,lat,lng,distanza,oramin,oramax,pagamin,pagamax);
    }

}
