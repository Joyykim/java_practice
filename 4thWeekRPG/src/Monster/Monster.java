package Monster;

import Player.*;

import java.util.Random;

public class Monster {
    public Monster(int monsterPlaceY, int monsterPlaceX, GameMap map){
        this.monsterPlaceY = monsterPlaceY;
        this.monsterPlaceX = monsterPlaceX;
        map.mapArray[monsterPlaceY][monsterPlaceX] = "Monster";
    }

    // 스탯 변수
    public int monsterHP = 100; //100
    public int monsterATK = 10; //10

    // 위치 변수
    public int monsterPlaceY;
    public int monsterPlaceX;

    // 암살자 독스킬 변수
    public int playerPoison = 0;

    //몬스터 공격 메소드
    //플레이어의 체력을 감소 - 일반몬스터
    public int m_ATK(Player player, Monster monster){

        int damage = monster.monsterATK;
        ////플레이어 직업 스탯(회피율 방어력)에 따라    데미지 감소 or 회피

        //회피율계산
        Random random = new Random();
        int dodge = random.nextInt(player.playerDodge_Archer);
        if (dodge == 4){
            System.out.println("\n\n몬스터의 공격을 회피하였습니다!!\n");
            return 0;
        }

        //방어력 계산 (0으로 나누기 예외처리)
        try {
            damage = monster.monsterATK - monster.monsterATK / player.playerDefence_Knight;
        }catch (ArithmeticException e){
            damage = monster.monsterATK;
        }finally {
            player.playerHP -= damage;
        }




        System.out.println("\n\n몬스터의 공격!\n\n데미지-"+damage+"\n플레이어 체력:"+player.playerHP);
        return 0;
    }

    // 몬스터 사망시 플레이어 스탯상승 메소드
    public void statUp (Player player){
        Random random = new Random();
        int ATKorHP = random.nextInt(2); //반반 확률로 공격력이나 체력 상승
        if (ATKorHP == 0){
            player.playerMaxHP += 10;
            System.out.println("최대체력이 증가했습니다");
        }else {
            player.playerATK += 5;
            System.out.println("공격력이 증가했습니다");
        }
        ///////////////////////////////////////////////////////////


        // 퀘스트 아이템 획득
        // 플레이어가 퀘스트를 하나라도 가지고 있을때만 실행
        while (!player.playerQuest_yet.isEmpty()){
            // 랜덤값을 뽑아 퀘스트 int 결정
            // 만약 플레이어가 퀘스트를 진행중이라면 int++
            // 진행중인 퀘스트가 아니라면 다시 랜덤

            int QuestInt = random.nextInt(6);

            switch (QuestInt){
                case 0:
                    if (player.playerQuest_yet.contains("퀘스트1-1")){
                        player.Q_item1_1++;
                        System.out.println("퀘스트1-1 아이템 획득");
                        return;
                    }else {
                        continue;
                    }
                case 1:
                    if (player.playerQuest_yet.contains("퀘스트1-2")){
                        player.Q_item1_2++;
                        return;
                    }else {
                        continue;
                    }
                case 2:
                    if (player.playerQuest_yet.contains("퀘스트1-3")){
                        player.Q_item1_3++;
                        return;
                    }else {
                        continue;
                    }
                case 3:
                    if (player.playerQuest_yet.contains("퀘스트2-1")){
                        player.Q_item2_1++;
                        return;
                    }else {
                        continue;
                    }
                case 4:
                    if (player.playerQuest_yet.contains("퀘스트2-2")){
                        player.Q_item2_2++;
                        return;
                    }else {
                        continue;
                    }
                case 5:
                    if (player.playerQuest_yet.contains("퀘스트2-3")){
                        player.Q_item2_3++;
                        return;
                    }
                default:
            }

        }







    }
}
