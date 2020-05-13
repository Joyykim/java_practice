package Monster;

import Player.GameMap;
import Player.Player;

import java.util.Random;

public class M_Boss extends Monster {
    public M_Boss(int monsterPlaceY, int monsterPlaceX, GameMap map) {
        super(monsterPlaceY, monsterPlaceX, map);
        // 스탯 변수
        super.monsterHP = 5000;//5000
        super.monsterATK = 100;//100
    }

    //몬스터 사망시 플레이어 스탯증가 - 최대체력,공격력 반반 확률로 증가
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
    }

    //보스 공격 - 랜덤 1/3 확룔로 일반공격, 스킬, 회복
    @Override
    public int m_ATK(Player player,Monster monster){ //스킬 발동시 true 리턴
        Random random = new Random();
        System.out.println();

        loop:
        while (true){
            int ranNum = random.nextInt(5);
            switch (ranNum){
                case 0: //스킬 20%
                    if (player.poison > 0 || player.burning > 0 || player.curse > 0) { //플레이어가 이미 상태이상이라면 스킬 XX
                        continue loop;
                    }
                    else {
                        System.out.println("\n몬스터 스킬발동!!");
                        return m_skill(player, monster); // 리턴값으로 받은 int 를 메인메소드에 전달해서 상태이상 구현
                    }
                case 1: //회복 20%
                    if (player.assassinSkill > 0){
                        player.assassinSkill--;
                        System.out.println("몬스터가 회복을 시도했지만 독에 당해 회복에 실패했습니다");
                        return 0;
                    }else {
                        System.out.println("\n몬스터 회복\n+20");
                        monster.monsterHP += 100;
                        System.out.println("몬스터 체력:"+monster.monsterHP);
                        return 0;
                    }
                default: //일반공격 60%

                    int damage = monster.monsterATK;
                    ////플레이어 직업 스탯(회피율 방어력)에 따라    데미지 감소 or 회피

                    //회피율계산
                    int dodge = random.nextInt(player.playerDodge_Archer);

                    if (dodge == 4){ // 회피
                        System.out.println("\n\n몬스터의 공격을 회피하였습니다!!\n");
                        return 0;
                    }else {
                        //방어력 계산 (0으로 나누기 예외처리)
                        try {
                            damage = monster.monsterATK - monster.monsterATK / player.playerDefence_Knight;
                            ///////////
                            System.out.println(player.playerDefence_Knight+"방어력");
                            System.out.println(monster.monsterATK+"공격");
                            System.out.println(damage+"뎀지");
                            /////////
                        }catch (ArithmeticException e){
                            damage = monster.monsterATK;
                        }finally {
                            player.playerHP -= damage;
                        }
                        System.out.println("\n\n몬스터의 공격!\n\n데미지-"+damage+"\n플레이어 체력:"+player.playerHP);
                        return 0;
                    }
            }

        }
    }

    // int 리턴값에 따라 상태이상 종류 - 0은 이상없음
    public int m_skill(Player player,Monster monster){
        return 0;
    }

}
