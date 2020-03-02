/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

/**
 *
 * @author Oytun
 */
public class AOKBO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Research DarkAgeResearch = new Research(500, 0, 0, 0, 0, 0);
        Building Mill = new Building(0, 100, 0, 0, 0, 35);
        Building TownCenter = new Building(0, 275, 0, 100, 2, 150);
        Research Loom = new Research(0, 0, 50, 0, 0, 25);
        Building Barracks = new Building(0, 175, 0, 0, 0, 50);
        Building Blacksmith = new Building(0, 150, 0, 0, 1, 40);
        Research Fletching = new Research(100, 0, 50, 0, 1, 30);
        Building Market = new Building(0, 175, 0, 0, 1, 60);
        Research FedualAgeResearch = new Research(500, 0, 0, 0, 0, 130);
        Market.addPreResearchs(Mill);
        Market.addPreResearchs(FedualAgeResearch);
        Building ArcheryRange = new Building(0, 175, 0, 0, 1, 50);
        ArcheryRange.addPreResearchs(FedualAgeResearch);
        ArcheryRange.addPreResearchs(Barracks);
        Research CastleAgeResearch = new Research(800, 0, 200, 0, 1, 160);
        CastleAgeResearch.addPreResearchs(FedualAgeResearch);
        Research BodkinArrow = new Research(200, 0, 100, 0, 0, 35);
        BodkinArrow.addPreResearchs(CastleAgeResearch);
        BodkinArrow.addPreResearchs(Fletching);
        Building SiegeWorkshop = new Building(0, 200, 0, 0, 2, 40);
        SiegeWorkshop.addPreResearchs(CastleAgeResearch);
        SiegeWorkshop.addPreResearchs(Blacksmith);
        //CastleAgeResearch.addPreResearchs(Building);
        //CastleAgeResearch.addPreResearchs(Building);
                
        
    }
    
}
