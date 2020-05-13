package Monster;

import Player.GameMap;
import Thread.*;

public class M_GeneralType_Random extends Monster{

    //랜덤전투 몬스터
    //HP 700 ATK 70
    public M_GeneralType_Random(MainThread mainThread) {
        super(mainThread);
        super.monsterHP = 700;
        super.monsterATK = 70;
    }
}


