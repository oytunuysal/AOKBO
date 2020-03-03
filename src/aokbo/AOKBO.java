/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

import java.util.ArrayList;

/**
 *
 * @author Oytun
 */
public class AOKBO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<baseGameItem> allGameItems = new ArrayList<>();

        //all researchs buildings
        Research DarkAgeResearch = new Research("DarkAgeResearch", 0, 0, 0, 0, 0, 0);
        allGameItems.add(DarkAgeResearch);

        Building Mill = new Building("Mill", 0, 100, 0, 0, 0, 35);
        allGameItems.add(Mill);

        Building TownCenter = new Building("TownCenter", 0, 275, 0, 100, 2, 150);
        allGameItems.add(TownCenter);

        Research Loom = new Research("Loom", 0, 0, 50, 0, 0, 25);
        allGameItems.add(Loom);

        Building Barracks = new Building("Barracks", 0, 175, 0, 0, 0, 50);
        allGameItems.add(Barracks);

        Building Blacksmith = new Building("Blacksmith", 0, 150, 0, 0, 1, 40);
        allGameItems.add(Blacksmith);

        Research Fletching = new Research("Fletching", 100, 0, 50, 0, 1, 30);
        allGameItems.add(Fletching);

        Research FedualAgeResearch = new Research("FedualAgeResearch", 500, 0, 0, 0, 0, 130);
        allGameItems.add(FedualAgeResearch);

        Building Market = new Building("Market", 0, 175, 0, 0, 1, 60);
        Market.addPreRequisites(Mill);
        Market.addPreRequisites(FedualAgeResearch);
        allGameItems.add(Market);

        Building ArcheryRange = new Building("ArcheryRange", 0, 175, 0, 0, 1, 50);
        ArcheryRange.addPreRequisites(FedualAgeResearch);
        ArcheryRange.addPreRequisites(Barracks);
        allGameItems.add(ArcheryRange);

        Building Stable = new Building("Stable", 0, 175, 0, 0, 1, 50);
        Stable.addPreRequisites(FedualAgeResearch);
        Stable.addPreRequisites(Barracks);
        allGameItems.add(Stable);

        Research CastleAgeResearch = new Research("CastleAgeResearch", 800, 0, 200, 0, 1, 160);
        CastleAgeResearch.addPreRequisites(FedualAgeResearch);
        CastleAgeResearch.addPreRequisites(Blacksmith);
        CastleAgeResearch.addPreRequisites(Market);
        CastleAgeResearch.addPreRequisites(ArcheryRange);
        CastleAgeResearch.addPreRequisites(Stable);
        allGameItems.add(CastleAgeResearch);

        Research BodkinArrow = new Research("BodkinArrow", 200, 0, 100, 0, 0, 35);
        BodkinArrow.addPreRequisites(CastleAgeResearch);
        BodkinArrow.addPreRequisites(Fletching);
        allGameItems.add(BodkinArrow);

        Building SiegeWorkshop = new Building("SiegeWorkshop", 0, 200, 0, 0, 2, 40);
        SiegeWorkshop.addPreRequisites(CastleAgeResearch);
        SiegeWorkshop.addPreRequisites(Blacksmith);
        allGameItems.add(SiegeWorkshop);

        //...
        TownCenter.addPreRequisites(CastleAgeResearch);

        //Listing all items and prequisites
        int i;
        for (i = 0; i < allGameItems.size(); i++) {
            System.out.println(allGameItems.get(i).getName());
            allGameItems.get(i).listPrerequisiteNames();
        }

        //listing each item with it prequisites
        //for (int i = 0; i < allGameItems.size(); i++) {
        //   System.out.println(allGameItems.get(i).preRequisites.);
        // }
    }

}
