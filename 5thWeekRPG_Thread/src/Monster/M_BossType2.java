package Monster;

import Player.GameMap;
import Thread.*;


public class M_BossType2 extends M_Boss{
    public M_BossType2(int monsterPlaceY, int monsterPlaceX, MainThread mainThread) {
        super(monsterPlaceY, monsterPlaceX, mainThread);
        super.mainThread.forestMap_night.mapArray[monsterPlaceY][monsterPlaceX] = GameMap.BOSS2;
        monsterHP = 7000;//7000
        monsterATK = 130;//130
    }


    // int 리턴값에 따라 상태이상 종류 - 0은 이상없음
    @Override
    public int m_skill(){
        System.out.println("악마의 화염!!");
        int damage = monsterATK*2;
        mainThread.player.playerHP -= damage;
        System.out.println("\n\n몬스터의 공격!\n\n데미지-"+damage+"\n플레이어 체력:"+mainThread.player.playerHP);
        return 2;
    }

    //보스 몬스터 사망시 플레이어 스탯증가 - 보스는 최대체력,공격력 모두 증가
    //보스 클리어 변수 true
    @Override
    public void statUp_questLoot(){

        mainThread.player.playerMaxHP += 400;
        mainThread.player.playerHP += 400;
        mainThread.player.playerATK += 150;
        System.out.println("최대체력이 400, 공격력이 150 증가했습니다");
        System.out.println();

        mainThread.player.boss2clear = true;
    }
}
