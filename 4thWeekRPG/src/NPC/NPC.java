package NPC;

import Player.*;

public class NPC {
    public NPC(int npcPlaceY, int npcPlaceX, GameMap map){
        this.npcPlaceY = npcPlaceY;
        this.npcPlaceX = npcPlaceX;
        map.mapArray[npcPlaceY][npcPlaceX] = "NPC";
    }

    public void interact_N(Player player, NPC npc){
        System.out.println("상호작용 NPC");
    }

    public int npcPlaceY;
    public int npcPlaceX;
}
