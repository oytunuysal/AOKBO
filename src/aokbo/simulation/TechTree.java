/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo.simulation;

import java.util.ArrayList;

/**
 *
 * @author Oytun
 */
public class TechTree {

    ArrayList<baseGameItem> allGameItems;
    // implement-> darkage buildings 1 points, fedual 10 (or 15) pts ect ect.
    // for listing all requirements
    ArrayList<baseGameItem> allReq = new ArrayList<>();

    public Research DarkAgeResearch;
    public Building Mill;
    public Building Farm;
    public Building LumberCamp;
    public Building MiningCamp;
    public Building TownCenter;
    public Research Loom;
    public Building Barracks;
    public Research FeudalAgeResearch;
    public Research DoubleBitAxeResearch;
    public Building Blacksmith;
    public Research Fletching;
    public Building Market;
    public Building ArcheryRange;
    public Building Stable;
    public Research CastleAgeResearch;
    public Research BodkinArrow;
    public Building SiegeWorkshop;
    public Research BowSawResearch;
    public Research WheelbarrowResearch;
    public Research ImperialAgeResearch;

    public Unit Villager;

    public Unit MilitiaLine;
    public Research ManAtArms;
    public Research LongswordsMan;

    public TechTree() {
        allGameItems = new ArrayList<>();

        // all researches, buildings and units
        DarkAgeResearch = new Research("DarkAgeResearch", 0, 0, 0, 0, 0, 0);
        allGameItems.add(DarkAgeResearch);

        Mill = new Building("Mill", 0, 100, 0, 0, 0, 35);
        allGameItems.add(Mill);

        Farm = new Building("Farm", 0, 60, 0, 0, 0, 15);
        allGameItems.add(Farm);
        Farm.addPreRequisites(Mill);

        LumberCamp = new Building("Lumber Camp", 0, 100, 0, 0, 0, 35);
        allGameItems.add(LumberCamp);

        MiningCamp = new Building("Mining Camp", 0, 100, 0, 0, 0, 35);
        allGameItems.add(MiningCamp);

        TownCenter = new Building("TownCenter", 0, 275, 0, 100, 2, 150);
        allGameItems.add(TownCenter);

        Loom = new Research("Loom", 0, 0, 50, 0, 0, 25);
        allGameItems.add(Loom);

        Barracks = new Building("Barracks", 0, 175, 0, 0, 0, 50);
        allGameItems.add(Barracks);

        MilitiaLine = new Unit("MilitiaLine", 60, 0, 20, 0, 21, 0, 0);
        MilitiaLine.addPreRequisites(Barracks);
        allGameItems.add(MilitiaLine);

        FeudalAgeResearch = new Research("FeudalAgeResearch", 500, 0, 0, 0, 0, 130);
        allGameItems.add(FeudalAgeResearch);
        FeudalAgeResearch.addPreRequisites(Barracks);
        FeudalAgeResearch.addPreRequisites(Mill);
        FeudalAgeResearch.addPreRequisites(LumberCamp);
        FeudalAgeResearch.addUpgradeAffect(4, 0, 1);

        ManAtArms = new Research("Man at Arms", 100, 0, 45, 0, 0, 40);
        ManAtArms.addPreRequisites(FeudalAgeResearch);
        ManAtArms.addPreRequisites(Barracks);
        allGameItems.add(ManAtArms);

        DoubleBitAxeResearch = new Research("Double-Bit Axe", 100, 50, 0, 0, 1, 25); // 20% as template
        DoubleBitAxeResearch.addPreRequisites(FeudalAgeResearch);
        DoubleBitAxeResearch.addPreRequisites(LumberCamp);
        DoubleBitAxeResearch.addUpgradeAffect(1, 1, 20);

        WheelbarrowResearch = new Research("Wheelbarrow", 175, 50, 0, 0, 1, 75);
        WheelbarrowResearch.addPreRequisites(FeudalAgeResearch);
        WheelbarrowResearch.addPreRequisites(TownCenter);
        WheelbarrowResearch.addUpgradeAffect(0, 3, 25); // carry cap
        WheelbarrowResearch.addUpgradeAffect(0, 2, 10); // movement speed

        Blacksmith = new Building("Blacksmith", 0, 150, 0, 0, 1, 40);
        Blacksmith.addPreRequisites(FeudalAgeResearch);
        allGameItems.add(Blacksmith);

        Fletching = new Research("Fletching", 100, 0, 50, 0, 1, 30);
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

        CastleAgeResearch = new Research("CastleAgeResearch", 800, 0, 200, 0, 1, 160);
        CastleAgeResearch.addPreRequisites(FeudalAgeResearch);
        CastleAgeResearch.addPreRequisites(Blacksmith);
        CastleAgeResearch.addPreRequisites(Market);
        CastleAgeResearch.addPreRequisites(ArcheryRange);
        CastleAgeResearch.addPreRequisites(Stable);
        allGameItems.add(CastleAgeResearch);
        CastleAgeResearch.addUpgradeAffect(4, 0, 2);

        LongswordsMan = new Research("Long Swordsman", 200, 0, 65, 0, 0, 45);
        LongswordsMan.addPreRequisites(CastleAgeResearch);
        LongswordsMan.addPreRequisites(Barracks);
        LongswordsMan.addPreRequisites(ManAtArms);
        allGameItems.add(LongswordsMan);

        BodkinArrow = new Research("Bodkin Arrow", 200, 0, 100, 0, 0, 35);
        BodkinArrow.addPreRequisites(CastleAgeResearch);
        BodkinArrow.addPreRequisites(Fletching);
        allGameItems.add(BodkinArrow);

        SiegeWorkshop = new Building("SiegeWorkshop", 0, 200, 0, 0, 2, 40);
        SiegeWorkshop.addPreRequisites(CastleAgeResearch);
        SiegeWorkshop.addPreRequisites(Blacksmith);
        allGameItems.add(SiegeWorkshop);

        BowSawResearch = new Research("Bow Saw", 150, 100, 0, 0, 2, 50);
        BowSawResearch.addPreRequisites(CastleAgeResearch);
        BowSawResearch.addPreRequisites(DoubleBitAxeResearch);
        BowSawResearch.addUpgradeAffect(1, 1, 20);
        allGameItems.add(BowSawResearch);

        Villager = new Unit("Vil", 50, 0, 0, 0, 25, 0.8f, 10);
        Villager.addPreRequisites(TownCenter);
        allGameItems.add(Villager);

        ImperialAgeResearch = new Research("ImperialAgeResearch", 1000, 0, 800, 0, 0, 190);
        ImperialAgeResearch.addPreRequisites(TownCenter);
        allGameItems.add(ImperialAgeResearch);
        ImperialAgeResearch.addUpgradeAffect(4, 0, 3);

        // ...
        TownCenter.addPreRequisites(CastleAgeResearch);

        // Listing all items and prerequisites
        for (int i = 0; i < allGameItems.size(); i++) {
            System.out.println(allGameItems.get(i).getName());
            allGameItems.get(i).listPrerequisiteNames();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }

    public void clearAllReq() {
        allReq.clear();
    }

    // Lists all requirements
    public void listingAllReq(baseGameItem target) {
        for (int i = 0; i < target.getPrerequisites().size(); i++) {
            // System.out.println(target.getPrerequisiteName(i));
            if (!allReq.contains(target.getPrerequisites().get(i))) { // put target.getPrerequisites().get(i)) into a
                                                                      // variable
                allReq.add((baseGameItem) target.getPrerequisites().get(i));
                System.out.println(target.getPrerequisiteName(i));
                listingAllReq((baseGameItem) target.getPrerequisites().get(i));
            }
        }
    }

    public void totalStoneCost(baseGameItem target) {
        int temp = 0;
        for (baseGameItem targetCost : allReq) {
            temp += targetCost.getRequiredStone();
        }
        System.out.println(temp);
    }

    public void totalGoldCost(baseGameItem target) {
        int temp = 0;
        for (baseGameItem targetCost : allReq) {
            temp += targetCost.getRequiredGold();
        }
        System.out.println(temp);
    }

    public void totalFoodCost(baseGameItem target) {
        int temp = 0;
        for (baseGameItem targetCost : allReq) {
            temp += targetCost.getRequiredFood();
        }
        System.out.println(temp);
    }

    public void totalWoodCost(baseGameItem target) {
        int temp = 0;
        for (baseGameItem targetCost : allReq) {
            temp += targetCost.getRequiredWood();
        }
        System.out.println(temp);
    }

    private int cost(baseGameItem anItem) {
        return anItem.getRequiredWood();
    }

    public void listingAllReqbyName(String targetName) {

        for (baseGameItem target : allGameItems) {
            if (target.getName().equals(targetName)) {
                listingAllReq(target);
            }
        }
    }

}
