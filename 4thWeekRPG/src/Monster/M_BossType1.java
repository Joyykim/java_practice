package Monster;

import Player.GameMap;
import Player.Player;

import java.util.Random;

public class M_BossType1 extends M_Boss{
    public M_BossType1(int monsterPlaceY, int monsterPlaceX, GameMap map) {
        super(monsterPlaceY, monsterPlaceX, map);
        map.mapArray[monsterPlaceY][monsterPlaceX] = "Boss1";
    }

    // int 리턴값에 따라 상태이상 종류 - 0은 이상없음
    @Override
    public int m_skill(Player player, Monster monster){
        System.out.println("1보스 스킬발동 - 중독");
        int damage = monsterATK*2;
        player.playerHP -= damage;
        System.out.println("\n\n몬스터의 공격!\n\n데미지-"+damage+"\n플레이어 체력:"+player.playerHP);
        return 1;
    }

    //보스 몬스터 사망시 플레이어 스탯증가 - 최대체력,공격력 반반 확률로 증가
    //보스 클리어 변수 true
    @Override
    public void statUp (Player player){


        Random random = new Random();
        int ATKorHP = random.nextInt(2);
        if (ATKorHP == 0){
            player.playerMaxHP += 200;
            System.out.println("최대체력이 200 증가했습니다");
        }else {
            player.playerATK += 50;
            System.out.println("공격력이 50 증가했습니다");
        }

        player.boss1clear = true;
    }
}
