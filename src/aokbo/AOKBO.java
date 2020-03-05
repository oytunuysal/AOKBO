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
        TechTree firstTree = new TechTree();
        firstTree.listingAllReqbyName("Fletching");
        firstTree.clearAllReq();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        firstTree.listingAllReqbyName("SiegeWorkshop");
    }

}
