package Monster;

import Thread.*;

import java.util.Random;

public class M_Boss extends Monster {
    M_Boss(int monsterPlaceY, int monsterPlaceX, MainThread mainThread) {
        super(monsterPlaceY, monsterPlaceX, mainThread);
    }


    //몬스터 사망시 플레이어 스탯증가 - 최대체력,공격력 반반 확률로 증가
    @Override
    public void statUp_questLoot(){
        Random random = new Random();

        int ATKorHP = random.nextInt(2);
        if (ATKorHP == 0){
            mainThread.player.playerMaxHP += 200;
            System.out.println("최대체력이 200 증가했습니다");
        }else {
            mainThread.player.playerATK += 50;
            System.out.println("공격력이 50 증가했습니다");
        }
    }

    // 보스 공격
    // 60% 일반공격
    // 20% 스킬
    // 20% 회복
    // return 값에 따라 상태이상을 전투메소드에 적용 (0이면 상태이상없음)
    @Override
    public int m_ATK(){ //스킬 발동시 true 리턴
        Random random = new Random();

        loop:
        while (true){
            int ranNum = random.nextInt(5);
            switch (ranNum){
                case 0: //스킬 20%
                    if (mainThread.player.poison > 0 || mainThread.player.burning > 0 || mainThread.player.curse > 0) { //플레이어가 이미 상태이상이라면 스킬 XX
                        continue loop;
                    }
                    else {
                        return m_skill(); // 리턴값으로 받은 int 를 메인메소드에 전달해서 상태이상 구현
                    }
                case 1: //회복 20%
                    if (mainThread.player.assassinSkill > 0){
                        mainThread.player.assassinSkill--;
                        System.out.println("\n몬스터가 회복을 시도했지만 독에 당해 회복에 실패했습니다\n");
                        return 0;
                    }else {
                        System.out.println("\n몬스터 회복 +100");
                        monsterHP += 100;
                        System.out.println("몬스터 체력: "+monsterHP);
                        return 0;
                    }
                default: //일반공격 60%

                    int damage = monsterATK;
                    ////플레이어 직업 스탯(회피율 방어력)에 따라    데미지 감소 or 회피

                    //회피율계산
                    int dodge = random.nextInt(mainThread.player.playerDodge_Archer);

                    if (dodge == 4){ // 회피 시
                        System.out.println("\n\n몬스터의 공격을 회피하였습니다!!\n");
                        return 0;

                    }else {
                        //방어력 계산 (0으로 나누기 예외처리)
                        try {
                            damage = monsterATK - monsterATK / mainThread.player.playerDefence_Knight;
                        }catch (ArithmeticException e){
                            damage = monsterATK;
                        }finally {
                            mainThread.player.playerHP -= damage; //데미지 적용
                        }
                        System.out.println("\n\n몬스터의 공격!\n\n데미지-"+damage+"\n플레이어 체력:"+mainThread.player.playerHP);
                        return 0;
                    }
            }

        }
    }

    // int 리턴값에 따라 상태이상 종류 - 0은 이상없음
    public int m_skill(){
        return 0;
    }

}
