package Monster;

import Player.GameMap;

public class M_GeneralType1 extends Monster{
    public M_GeneralType1(int monsterPlaceY, int monsterPlaceX, GameMap map) {
        super(monsterPlaceY, monsterPlaceX, map);
        super.monsterHP = 300;
        super.monsterATK = 20;
        map.mapArray[monsterPlaceY][monsterPlaceX] = "Monster1";
    }
}
