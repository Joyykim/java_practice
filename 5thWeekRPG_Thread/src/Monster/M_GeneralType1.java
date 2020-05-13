package Monster;

import Player.GameMap;
import Thread.*;

public class M_GeneralType1 extends Monster{

    //일반 몬스터1
    //HP 300 ATK 30
    public M_GeneralType1(int monsterPlaceY, int monsterPlaceX, MainThread mainThread) {
        super(monsterPlaceY, monsterPlaceX, mainThread);
        super.monsterHP = 300;
        super.monsterATK = 30;
        super.mainThread.forestMap.mapArray[monsterPlaceY][monsterPlaceX] = GameMap.MONSTER1;
    }
}
