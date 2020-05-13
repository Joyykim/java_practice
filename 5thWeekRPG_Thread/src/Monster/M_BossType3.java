package Monster;

import Player.GameMap;
import Thread.*;

import java.util.Random;

public class M_BossType3 extends M_Boss{
    public M_BossType3(int monsterPlaceY, int monsterPlaceX, MainThread mainThread) {
        super(monsterPlaceY, monsterPlaceX, mainThread);
        super.monsterHP = 15000;//15000
        super.monsterATK = 200;//200
        super.mainThread.forestMap_night.mapArray[monsterPlaceY][monsterPlaceX] = GameMap.BOSS3;
    }


    // int 리턴값에 따라 상태이상 종류 - 0은 이상없음
    @Override
    public int m_skill(){
        Random random = new Random();
        int ranNum = random.nextInt(2);
        if (ranNum == 0){
            System.out.println("흑마법 - 저주");
            int damage = monsterATK*2;
            mainThread.player.playerHP -= damage;
            System.out.println("\n\n몬스터의 공격!\n\n데미지-"+damage+"\n플레이어 체력:"+mainThread.player.playerHP);
            return 3;
        }else {
            System.out.println("흑마법 - 마력 폭발");
            int damage = monsterATK*4;
            mainThread.player.playerHP -= damage;
            System.out.println("\n\n몬스터의 공격!\n\n데미지-"+damage+"\n플레이어 체력:"+mainThread.player.playerHP);
            return 0;
        }
    }

    //보스 몬스터 사망시 플레이어 스탯증가 - 최대체력,공격력 반반 확률로 증가
    //보스 클리어 변수 true
    @Override
    public void statUp_questLoot(){
        mainThread.player.ending = true;
        mainThread.player.playerMaxHP += 500;
        mainThread.player.playerHP = mainThread.player.playerMaxHP;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n비극의 원흉을 제거했습니다.\n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("괴물들이 모두 사람으로 돌아왔습니다.\n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("마을로 돌아가서 퀘스트를 완료하면 엔딩이 시작됩니다.\n\n");
    }

}
