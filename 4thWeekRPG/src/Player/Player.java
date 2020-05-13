package Player;

import Monster.Monster;
import NPC.N_Person;
import NPC.N_Shop;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
    public Player(int yPlace,int xPlace){
        this.yPlace = yPlace;
        this.xPlace = xPlace;
    }


    //변수 지정
    public String playerID;
    public String charName;
    //이동 변수
    public boolean KeyUp;
    public boolean KeyDown;
    public boolean KeyLeft;
    public boolean KeyRight;
    //스탯 변수
    public int playerMaxHP = 10000; //200
    public int playerHP = playerMaxHP;
    public int playerATK = 1000; //10
    public int playerMoney = 5000; //100
    public int playerPotion1 = 10; //5
    public int playerPotion2 = 10; //1
    public int playerSkillPoint = 10; //3
    //직업 변수
    public double playerAttack_Warrior = 1; // 공격력 * 스탯 계산
    public int playerDodge_Archer = 1; // 랜덤 바운드 5 => 4가 나오면 공격회피(확률 20%)
    public int playerCritical_Assassin = 1; // 랜덤 바운드 5 => 4가 나오면 치명타200%데미지 (확률 20%)
    public int playerDefence_Knight = 0; //몬스터 데미지 - (데미지/스탯) 예외처리필요

    //보스 클리어 변수
    public boolean boss1clear = false;
    public boolean boss2clear = false;
    public boolean boss3clear = false;

    // 보스 디버프 변수
    public int poison = 0;
    public int burning = 0;
    public int curse = 0;

    //플레이어 스킬 변수
    public int archerSkill = 0;
    public int knightSkill = 0;
    public int assassinSkill = 0;


    //전사 공격력 -
    public void setPlayerATK(double playerAttack_Warrior) {
        this.playerATK = (int)(this.playerATK * 1.2); // 전사 공격력 증가
        this.playerAttack_Warrior = playerAttack_Warrior;
    }
    //기사 체력 -
    public void setPlayerDefence_Knight(int playerDefence_Knight) {
        this.playerMaxHP = (int)(this.playerMaxHP * 1.2); // 기사 체력 증가
        this.playerDefence_Knight = playerDefence_Knight;
    }
    //궁수 회피율 -
    public void setPlayerDodge_Archer(int playerDodge_Archer) {
        this.playerDodge_Archer = playerDodge_Archer;
    }
    //암살자 치명타율
    public void setPlayerCritical_Assassin(int playerCritical_Assassin) {
        this.playerCritical_Assassin = playerCritical_Assassin;
    }



    //플레이어 위치 변수
    public int xPlace;
    public int yPlace;

    //보스12클리어 출력 변수
    public boolean boss12clear = true;

    //엔딩 변수
    public boolean ending = false;




    //퀘스트 리스트
    public ArrayList<String> playerQuest_yet = new ArrayList<>(); // NPC 와 대화시 리스트에 퀘스트명을 contains 로 확인해서 완료, 미완료 판단
    public ArrayList<String> playerQuest_done = new ArrayList<>();

    //퀘스트 아이템 변수 (퀘스트 달성 조건)
    public int Q_item1_1 = 0;
    public int Q_item1_2 = 0;
    public int Q_item1_3 = 0;
    public int Q_item2_1 = 0;
    public int Q_item2_2 = 0;
    public int Q_item2_3 = 0;


    // 퀘스트 달성 여부 메소드 - 메인 메소드 루프에서 실행
    public void isQuestComplete(Player player){
        if (player.Q_item1_1 == 5){
            if (!player.playerQuest_done.contains("퀘스트1-1")){
                player.playerQuest_yet.remove("퀘스트1-1");
                playerQuest_done.add("퀘스트1-1");
            }
        }
        if (player.Q_item1_2 == 5){
            if (!player.playerQuest_done.contains("퀘스트1-2")){
                player.playerQuest_yet.remove("퀘스트1-2");
                playerQuest_done.add("퀘스트1-2");
            }
        }
        if (player.Q_item1_3 == 5){
            if (!player.playerQuest_done.contains("퀘스트1-3")){
                player.playerQuest_yet.remove("퀘스트1-3");
                playerQuest_done.add("퀘스트1-3");
            }
        }
        if (player.Q_item2_1 == 5){
            if (!player.playerQuest_done.contains("퀘스트2-1")){
                player.playerQuest_yet.remove("퀘스트2-1");
                playerQuest_done.add("퀘스트2-1");
            }
        }
        if (player.Q_item2_2 == 5){
            if (!player.playerQuest_done.contains("퀘스트2-2")){
                player.playerQuest_yet.remove("퀘스트2-2");
                playerQuest_done.add("퀘스트2-2");
            }
        }
        if (player.Q_item2_3 == 5){
            if (!player.playerQuest_done.contains("퀘스트2-3")){
                player.playerQuest_yet.remove("퀘스트2-3");
                playerQuest_done.add("퀘스트2-3");
            }
        }
    }






    // W,A,S,D 입력시 move 실행(입력된 키를 인자로 받아서 동작, 플레이어 위치(String)를 이동시키고 만약 막힌 길이라면 알려줌)
    public void moves_P(String direction, GameMap map, Player player){

        //위치 이동(배열)
        map.currentPlayerPlace(player,map.mapArray); // 좌표 새로고침

        if (map.mapArray[yPlace][xPlace].equals("Player")){
            //플레이어 이동, 원래 인덱스는 "Road" 로 변경
            switch (direction){
                case "w":
                case "W":
                    try {
                        if (map.thisIsRoad(map.mapArray[yPlace-1][xPlace])){
                            map.mapArray[yPlace-1][xPlace] = "Player";
                            map.mapArray[yPlace][xPlace] = "Road";
                            yPlace--;
                            System.out.println("위로 이동");
                            break;
                        }else {
                            System.out.println("길이 아닙니다");
                            break;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){ //위의 길이 범위를 벗어남
                        System.out.println("길이 아닙니다");
                        break;
                    }
                case "a":
                case "A":
                    try {
                        if (map.thisIsRoad(map.mapArray[yPlace][xPlace-1])){
                            map.mapArray[yPlace][xPlace-1] = "Player";
                            map.mapArray[yPlace][xPlace] = "Road";
                            xPlace--;
                            System.out.println("왼쪽으로 이동");
                            break;
                        }else {
                            System.out.println("길이 아닙니다");
                            break;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("길이 아닙니다");
                        break;
                    }
                case "s":
                case "S":
                    try {
                        if (map.thisIsRoad(map.mapArray[yPlace+1][xPlace])){
                            map.mapArray[yPlace+1][xPlace] = "Player";
                            map.mapArray[yPlace][xPlace] = "Road";
                            yPlace++;
                            System.out.println("아래로 이동");
                            break;
                        }else {
                            System.out.println("길이 아닙니다");
                            break;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("길이 아닙니다");
                        break;
                    }
                case "d":
                case "D":
                    try {
                        if (map.thisIsRoad(map.mapArray[yPlace][xPlace+1])){
                            map.mapArray[yPlace][xPlace+1] = "Player";
                            map.mapArray[yPlace][xPlace] = "Road";
                            xPlace++;
                            System.out.println("오른쪽으로 이동");
                            break;
                        }else {
                            System.out.println("길이 아닙니다");
                            break;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("길이 아닙니다");
                        break;
                    }
            }
        }

        //맵 출력
        map.printMap(map.mapArray);
    }

    //일반공격
    public void attack_P(Player player, Monster monster){
        System.out.println("\n일반공격!!");

        int damage = (int)(player.playerATK * player.playerAttack_Warrior); // 전사 스탯 * 공격력을 정수로 변환

        Random random = new Random();
        int critical = random.nextInt(player.playerCritical_Assassin); // 랜덤 바운드 5로 설정 - 4가 나오면 치명타200% (확률20%)
        if (critical == 4){
            System.out.println("\n치명타 발동!!☆\n");
            damage *= 2;
        }

        monster.monsterHP -= damage;
        System.out.println("\n데미지-"+damage+"\n몬스터체력:"+monster.monsterHP);
    }

    // 스킬 사용
    //리턴값 int로 독/반격/회피 여부 결정
    public int skill_P(Player player, Monster monster){
        player.playerSkillPoint--;
        System.out.println("\n스킬공격!");
        return 0;
    }

    // 포션
    public boolean heal_P(Player player){
        Scanner scanner = new Scanner(System.in);
        if (player.playerHP == player.playerMaxHP){ //체력이 최대치라면 힐 불가
            System.out.println("플레이어의 체력이 이미 최대치입니다");
            return false;
        }else { //최대치가 아니라면 힐가능
            while (true){
                System.out.println("회복 포션을 골라주세요");
                System.out.println("1. 50 회복 포션  남은개수:"+player.playerPotion1);
                System.out.println("2. 200 회복 포션  남은개수:"+player.playerPotion2);
                System.out.println("z. 취소하고 돌아가기");
                String potionSelect = scanner.next(); //입력값 1,2에 따라 사용할 포션 종류 결정
                switch (potionSelect){
                    case "1":
                        if (player.playerPotion1 > 0){
                            player.playerHP += 50;
                            System.out.println("50 회복 포션을 사용합니다");
                            if (player.playerHP >= player.playerMaxHP){
                                player.playerHP = player.playerMaxHP;
                                System.out.println("체력이 최대치로 회복되었습니다");
                            }
                            System.out.println("현재 체력"+player.playerHP);
                            player.playerPotion1--;
                            return true;
                        }else {
                            System.out.println("사용할 수 있는 포션이 없습니다");
                            return false;
                        }
                    case "2":
                        if (player.playerPotion2 > 0){
                            player.playerHP += 200;
                            System.out.println("200 회복 포션을 사용합니다");
                            if (player.playerHP >= player.playerMaxHP){
                                player.playerHP = player.playerMaxHP;
                                System.out.println("체력이 최대치로 회복되었습니다");
                            }
                            System.out.println("현재 체력"+player.playerHP);
                            player.playerPotion2--;
                            return true;
                        }else {
                            System.out.println("사용할 수 있는 포션이 없습니다");
                            return false;
                        }
                    case "z":
                    case "Z":
                        System.out.println("\n돌아갑니다\n");
                        return false;
                    default:
                        System.out.println("다시 골라주세요");
                }
            }
        }







    }
}
