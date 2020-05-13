package Monster;

import Player.GameMap;

public class M_GeneralType2 extends Monster{
    public M_GeneralType2(int monsterPlaceY, int monsterPlaceX, GameMap map) {
        super(monsterPlaceY, monsterPlaceX, map);
        super.monsterHP = 500;
        super.monsterATK = 50;
        map.mapArray[monsterPlaceY][monsterPlaceX] = "Monster2";
    }
}
