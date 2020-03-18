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
public class TechTree {

    ArrayList<baseGameItem> allGameItems;

    //for listing all requirements
    ArrayList<baseGameItem> allReq = new ArrayList<>();

    Research DarkAgeResearch;
    Building Mill;
    Building LumberCamp;
    Building TownCenter;
    Research Loom;
    Building Barracks;
    Research FeudalAgeResearch;
    Research DoubleBitAxResearch;
    Building Blacksmith;
    Research Fletching;
    Building Market;
    Building ArcheryRange;
    Building Stable;
    Research CastleAgeResearch;
    Research BodkinArrow;
    Building SiegeWorkshop;

    public TechTree() {
        allGameItems = new ArrayList<>();

        //all researchs buildings
        DarkAgeResearch = new Research("DarkAgeResearch", 0, 0, 0, 0, 0, 0, 0, 0);
        allGameItems.add(DarkAgeResearch);

        Mill = new Building("Mill", 0, 100, 0, 0, 0, 35);
        allGameItems.add(Mill);

        LumberCamp = new Building("Lumber Camp", 0, 100, 0, 0, 0, 35);
        allGameItems.add(LumberCamp);

        TownCenter = new Building("TownCenter", 0, 275, 0, 100, 2, 150);
        allGameItems.add(TownCenter);

        Loom = new Research("Loom", 0, 0, 50, 0, 0, 25, 0, 0);
        allGameItems.add(Loom);

        Barracks = new Building("Barracks", 0, 175, 0, 0, 0, 50);
        allGameItems.add(Barracks);

        FeudalAgeResearch = new Research("FeudalAgeResearch", 500, 0, 0, 0, 0, 130, 0, 0);
        allGameItems.add(FeudalAgeResearch);
        FeudalAgeResearch.addPreRequisites(Barracks);
        FeudalAgeResearch.addPreRequisites(Mill);
        FeudalAgeResearch.addPreRequisites(LumberCamp);

        DoubleBitAxResearch = new Research("Double-Bit Axe", 100, 50, 0, 0, 0, 25, 1, 20); //20% as template
        DoubleBitAxResearch.addPreRequisites(FeudalAgeResearch);
        DoubleBitAxResearch.addPreRequisites(LumberCamp);

        Blacksmith = new Building("Blacksmith", 0, 150, 0, 0, 1, 40);
        Blacksmith.addPreRequisites(FeudalAgeResearch);
        allGameItems.add(Blacksmith);

        Fletching = new Research("Fletching", 100, 0, 50, 0, 1, 30, 0, 0);
        allGameItems.add(Fletching);
        Fletching.addPreRequisites(FeudalAgeResearch);
        Fletching.addPreRequisites(Blacksmith);

        Market = new Building("Market", 0, 175, 0, 0, 1, 60);
        Market.addPreRequisites(Mill);
        Market.addPreRequisites(FeudalAgeResearch);
        allGameItems.add(Market);

        ArcheryRange = new Building("ArcheryRange", 0, 175, 0, 0, 1, 50);
        ArcheryRange.addPreRequisites(FeudalAgeResearch);
        ArcheryRange.addPreRequisites(Barracks);
        allGameItems.add(ArcheryRange);

        Stable = new Building("Stable", 0, 175, 0, 0, 1, 50);
        Stable.addPreRequisites(FeudalAgeResearch);
        Stable.addPreRequisites(Barracks);
        allGameItems.add(Stable);

        CastleAgeResearch = new Research("CastleAgeResearch", 800, 0, 200, 0, 1, 160, 0, 0);
        CastleAgeResearch.addPreRequisites(FeudalAgeResearch);
        CastleAgeResearch.addPreRequisites(Blacksmith);
        CastleAgeResearch.addPreRequisites(Market);
        CastleAgeResearch.addPreRequisites(ArcheryRange);
        CastleAgeResearch.addPreRequisites(Stable);
        allGameItems.add(CastleAgeResearch);

        BodkinArrow = new Research("Bodkin Arrow", 200, 0, 100, 0, 0, 35, 0, 0);
        BodkinArrow.addPreRequisites(CastleAgeResearch);
        BodkinArrow.addPreRequisites(Fletching);
        allGameItems.add(BodkinArrow);

        SiegeWorkshop = new Building("SiegeWorkshop", 0, 200, 0, 0, 2, 40);
        SiegeWorkshop.addPreRequisites(CastleAgeResearch);
        SiegeWorkshop.addPreRequisites(Blacksmith);
        allGameItems.add(SiegeWorkshop);

        //...
        TownCenter.addPreRequisites(CastleAgeResearch);

        //Listing all items and prerequisites
        for (int i = 0; i < allGameItems.size(); i++) {
            System.out.println(allGameItems.get(i).getName());
            allGameItems.get(i).listPrerequisiteNames();
        }

        //listing each item with it prerequisites
        //for (int i = 0; i < allGameItems.size(); i++) {
        //   System.out.println(allGameItems.get(i).preRequisites.);
        // }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        //AOKBO targetAokbo = new AOKBO();
        //targetAokbo.listingAllReq(SiegeWorkshop);
        // listingAllReq(BodkinArrow);
        // totalWoodCost(BodkinArrow);
        // System.out.println();
        // System.out.println();
        //  System.out.println();
        // System.out.println();
    }

    public void clearAllReq() {
        allReq.clear();
    }

    //Lists all requirements
    public void listingAllReq(baseGameItem target) {
        for (int i = 0; i < target.getPrerequisites().size(); i++) {
            //System.out.println(target.getPrerequisiteName(i));
            //listingAllReq((baseGameItem) target.getPrerequisites().get(i));
            if (!allReq.contains(target.getPrerequisites().get(i))) {
                allReq.add((baseGameItem) target.getPrerequisites().get(i));
                System.out.println(target.getPrerequisiteName(i));
                listingAllReq((baseGameItem) target.getPrerequisites().get(i));
            }
        }
    }

    public void totalStoneCost(baseGameItem target) {
        int temp = 0;
        for (baseGameItem targetCost : allReq) {
            temp += targetCost.requiredStone;
        }
        System.out.println(temp);
    }

    public void totalGoldCost(baseGameItem target) {
        int temp = 0;
        for (baseGameItem targetCost : allReq) {
            temp += targetCost.requiredGold;
        }
        System.out.println(temp);
    }

    public void totalFoodCost(baseGameItem target) {
        int temp = 0;
        for (baseGameItem targetCost : allReq) {
            temp += targetCost.requiredFood;
        }
        System.out.println(temp);
    }

    public void totalWoodCost(baseGameItem target) {
        int temp = 0;
        // allReq.forEach(targetCost -> {temp += cost(targetCost)} );
        for (baseGameItem targetCost : allReq) {
            temp += targetCost.requiredWood;
        }
        System.out.println(temp);
    }

    private int cost(baseGameItem anItem) {
        return anItem.requiredWood;
    }

    public void listingAllReqbyName(String targetName) {

        for (baseGameItem target : allGameItems) {
            if (target.name.equals(targetName)) {
                listingAllReq(target);
            }
        }
    }
}
