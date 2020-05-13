package Monster;

import NPC.N_PersonType1;
import NPC.N_PersonType2;
import Player.*;
import Thread.*;

import java.util.Random;

public class Monster {
    public Monster(int monsterPlaceY, int monsterPlaceX, MainThread mainThread){
        this.monsterPlaceY = monsterPlaceY;
        this.monsterPlaceX = monsterPlaceX;
        this.mainThread = mainThread;
    }

    public Monster(MainThread mainThread){
        this.monsterPlaceY = 0;
        this.monsterPlaceX = 0;
        this.mainThread = mainThread;
    }

    public MainThread mainThread;

    // 스탯 변수
    public int monsterHP = 100; //100
    public int monsterATK = 10; //10

    // 위치 변수
    public int monsterPlaceY;
    public int monsterPlaceX;

    // 암살자 독스킬 변수
    public int playerPoison = 0;

    //
    private boolean q11 = true;
    private boolean q21 = true;

    //몬스터 공격 메소드
    //플레이어의 체력을 감소 - 일반몬스터
    public int m_ATK(){

        int damage = monsterATK;
        ////플레이어 직업 스탯(회피율 방어력)에 따라    데미지 감소 or 회피

        //회피율계산
        Random random = new Random();
        int dodge = random.nextInt(mainThread.player.playerDodge_Archer);
        if (dodge == 4){
            System.out.println("\n\n몬스터의 공격을 회피하였습니다!!\n");
            return 0;
        }

        //방어력 계산 (0으로 나누기 예외처리)
        try {
            damage = monsterATK - monsterATK / mainThread.player.playerDefence_Knight;
        }catch (ArithmeticException e){
            damage = monsterATK;
        }finally {
            mainThread.player.playerHP -= damage;
        }

        System.out.println("\n\n몬스터의 공격!\n\n데미지-"+damage+"\n플레이어 체력:"+mainThread.player.playerHP);
        return 0;
    }

    // 몬스터 사망시 스탯상승, 퀘스트 아이템 획득 메소드
    public void statUp_questLoot(){
        Random random = new Random();
        int ATKorHP = random.nextInt(2); //반반 확률로 공격력이나 체력 상승
        if (ATKorHP == 0){
            mainThread.player.playerMaxHP += 10; //10
            mainThread.player.playerHP += 10;
            System.out.println("최대체력이 증가했습니다");
        }else {
            mainThread.player.playerATK += 5; //5
            System.out.println("공격력이 증가했습니다");
        }
        ///////////////////////////////////////////////////////////


        // 퀘스트 아이템 획득
        // 플레이어가 퀘스트를 하나라도 가지고 있을때만 실행
        if (!mainThread.player.playerQuest_yet.isEmpty()){
            // 랜덤값을 뽑아 퀘스트 int 결정
            // 만약 플레이어가 퀘스트를 진행중이라면 int++
            // 진행중인 퀘스트가 아니라면 다시 랜덤


            //식량창고 - 숲, 1/5확률
            if (mainThread.player.playerQuest_yet.contains(N_PersonType1.QUEST11)){
                int ranNum = random.nextInt(5);
                if (mainThread.currentMap.mapName.equals(GameMap.FOREST) && ranNum == 0){
                    mainThread.player.Q_item1_1 = true;
                    if (q11){
                        System.out.println("퀘스트 아이템 - '식량창고 열쇠' 획득");
                        q11 = false;
                    }
                }
            }


            //의료도구 회수 - 숲, 1/3확률
            if (mainThread.player.playerQuest_yet.contains(N_PersonType1.QUEST12)){
                int ranNum = random.nextInt(3);
                if (mainThread.currentMap.mapName.equals(GameMap.FOREST) && ranNum == 0){
                    mainThread.player.Q_item1_2++;
                    System.out.println("퀘스트 아이템 - '의료도구' 획득");
                }
            }


            //목걸이 회수 - 숲, 1/6확률
            if (mainThread.player.playerQuest_yet.contains(N_PersonType2.QUEST21)) {
                int ranNum = random.nextInt(6);
                if (mainThread.currentMap.mapName.equals(GameMap.FOREST) && ranNum == 0){
                    mainThread.player.Q_item2_1 = true;
                    if (q21){
                        System.out.println("퀘스트 아이템 - '목걸이' 획득");
                        q21 = false;
                    }
                }
            }


            //한밤중의 습격 - 마을
            if (mainThread.player.playerQuest_yet.contains(N_PersonType2.QUEST22)) {
                if (mainThread.currentMap.mapName.equals(GameMap.VILLAGE)){
                    System.out.println("퀘스트 진행 - '한밤중의 습격' +1");
                    mainThread.player.Q_item2_2++;
                }
            }



            //괴물 퇴치 - 숲
            if (mainThread.player.playerQuest_yet.contains(N_PersonType2.QUEST23)){
                if (mainThread.currentMap.mapName.equals(GameMap.FOREST)){
                    System.out.println("퀘스트 진행 - '괴물 퇴치' +1");
                    mainThread.player.Q_item2_3++;
                }
            }


        }
    }

}
