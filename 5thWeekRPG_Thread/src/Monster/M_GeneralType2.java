package Monster;

import Player.GameMap;
import Thread.*;

public class M_GeneralType2 extends Monster{

    //일반 몬스터2
    //HP 500 ATK 50
    public M_GeneralType2(int monsterPlaceY, int monsterPlaceX, MainThread mainThread) {
        super(monsterPlaceY, monsterPlaceX, mainThread);
        super.monsterHP = 500;
        super.monsterATK = 50;
        super.mainThread.forestMap.mapArray[monsterPlaceY][monsterPlaceX] = GameMap.MONSTER2;
    }

}


