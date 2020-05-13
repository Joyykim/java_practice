package Monster;

import Player.GameMap;
import Player.Player;

public class M_BossType3 extends M_Boss{
    public M_BossType3(int monsterPlaceY, int monsterPlaceX, GameMap map) {
        super(monsterPlaceY, monsterPlaceX, map);
        super.monsterHP = 10000;//10000
        super.monsterATK = 200;//200
        map.mapArray[monsterPlaceY][monsterPlaceX] = "Boss3";
    }

    // int 리턴값에 따라 상태이상 종류 - 0은 이상없음
    @Override
    public int m_skill(Player player, Monster monster){
        System.out.println("3보스 스킬발동 - 저주");
        int damage = monsterATK*2;
        player.playerHP -= damage;
        System.out.println("\n\n몬스터의 공격!\n\n데미지-"+damage+"\n플레이어 체력:"+player.playerHP);

        return 3;
    }

    //보스 몬스터 사망시 플레이어 스탯증가 - 최대체력,공격력 반반 확률로 증가
    //보스 클리어 변수 true
    @Override
    public void statUp (Player player){

        player.boss3clear = true;

    }

}
