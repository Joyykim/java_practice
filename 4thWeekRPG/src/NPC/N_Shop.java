package NPC;

import Player.*;

import java.util.ArrayList;
import java.util.Scanner;

public class N_Shop extends NPC {

    public N_Shop(int npcPlaceY, int npcPlaceX, GameMap map) {
        super(npcPlaceY, npcPlaceX, map);
        map.mapArray[npcPlaceY][npcPlaceX] = "Shop";
    }

    // 리스트로 물건을 보관
    public ArrayList<String> shopInventory = new ArrayList<>();

    @Override
    public void interact_N(Player player, NPC npc){
        Scanner scanner = new Scanner(System.in);
        System.out.println("상호작용 상점");
        for (String item : shopInventory){ // 상점 물품 출력
            System.out.println(item);
        }

    }
}
