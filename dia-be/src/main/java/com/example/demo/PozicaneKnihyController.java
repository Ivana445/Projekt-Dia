package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
public class PozicaneKnihyController {
    private DemoController demoController;
    private ArrayList<PozicaneKnihy> vypozicky = new ArrayList<>();
    private ArrayList<PozicaneKnihy> pozicaneKnihy = new ArrayList<>();
//    private ArrayList<Kniha> knihy = demoController.ziskatKniha();
//    private ArrayList<Ludia> ludia = new ArrayList<>();
//
    public PozicaneKnihyController(DemoController demoController) {
        this.demoController = demoController;
    }


    @PostMapping("/api/borrowings")
    public void vytvorVypozicku(@RequestBody PozicaneKnihy pozicaneKnihy) {

            System.out.println(pozicaneKnihy);
            int clovekId = pozicaneKnihy.clovekId;
            int knihaId = pozicaneKnihy.knihaId;

            System.out.println("idcka "+ clovekId +
                    knihaId);

            PozicaneKnihy novaPozicanaKniha = new PozicaneKnihy(1, clovekId,
                    knihaId);

            novaPozicanaKniha.setId(1);
            novaPozicanaKniha.setKnihaId(knihaId);
            novaPozicanaKniha.setClovekId(clovekId);

            this.pozicaneKnihy.add(novaPozicanaKniha);

            // Vypíšte informácie o novej výpožičke
            System.out.println("Vytvorená výpožička: " + novaPozicanaKniha);


    }

    @GetMapping("/api/borrowings")
    public ArrayList<PozicaneKnihy> listPozicanychKnih() {
        System.out.println("Vypozicky: " +pozicaneKnihy);
        return pozicaneKnihy;

    }

    @GetMapping("/api/borrowings/{id}")
    public PozicaneKnihy zobrazVypozicku(@PathVariable int id) {
        for (PozicaneKnihy pozicane: pozicaneKnihy){
            if (pozicane.getId() == id){
                System.out.println("nasiel som vypozicku");
                return pozicane;
            }
        }
        return null;
    }

    @DeleteMapping("/api/borrowings/{id}")
    public void vymazVypozicku(@PathVariable int id) {
        PozicaneKnihy pozicaneToDelete = null;
        for (PozicaneKnihy pozicane : this.pozicaneKnihy) {
            if (pozicane.getId() == id) {
                pozicaneToDelete = pozicane;
                break;
            }
        }
        if (pozicaneToDelete != null) {
            this.pozicaneKnihy.remove(pozicaneToDelete);
            System.out.println("Mazem vypozicku s id: " + id);
        } else {
            System.out.println("ID " + id + " neexistuje v zozname pozicanych knih.");
        }

    }
}
