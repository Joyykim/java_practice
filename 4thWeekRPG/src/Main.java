import Monster.*;
import NPC.*;
import Player.*;
import Thread.*;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        ////////변수 선언, 초기화

        // 플레이어 행동 변수
        String actionSelect;

        //플레이어 생성자
        Player player = new Player(5,5);

        //게임맵 생성자
        GameMap villageMap = new GameMap(10,20);
        GameMap forestMap = new GameMap(15,30);
        GameMap currentMap;

        //맵 구현 메소드
        makeVillageMap(villageMap.mapArray);//마을맵
        makeForestMap(forestMap.mapArray);//사냥맵

        //NPC 생성자, 리스트
        N_PersonType1 n_person1 = new N_PersonType1(4,1,villageMap);
        N_PersonType2 n_person2 = new N_PersonType2(4,4,villageMap);
        N_Shop_Potion n_shop_potion = new N_Shop_Potion(4,7,villageMap);
        N_Shop_Weapon n_shop_weapon = new N_Shop_Weapon(4,10,villageMap);
        ArrayList<NPC> npcList= new ArrayList<NPC>();
        npcList.add(n_person1);
        npcList.add(n_person2);
        npcList.add(n_shop_potion);
        npcList.add(n_shop_weapon);



        //몬스터 생성자, 리스트
        M_GeneralType1 monster1 = new M_GeneralType1(1,6, forestMap);
        M_GeneralType1 monster2 = new M_GeneralType1(1,13, forestMap);
        M_GeneralType1 monster3 = new M_GeneralType1(5,6, forestMap);
        M_GeneralType1 monster4 = new M_GeneralType1(5,13, forestMap);

        M_GeneralType2 monster5 = new M_GeneralType2(9,6, forestMap);
        M_GeneralType2 monster6 = new M_GeneralType2(9,13, forestMap);
        M_GeneralType2 monster7 = new M_GeneralType2(13,6, forestMap);
        M_GeneralType2 monster8 = new M_GeneralType2(13,13, forestMap);



        M_BossType1 monsterBoss1 = new M_BossType1(1,27, forestMap);
        M_BossType2 monsterBoss2 = new M_BossType2(13,27, forestMap);
        M_BossType3 monsterBoss3 = new M_BossType3(7,27, forestMap); //최종 보스


        ArrayList<Monster> monsterList = new ArrayList<Monster>();

        monsterList.add(monsterBoss1);
        monsterList.add(monsterBoss2);
        monsterList.add(monsterBoss3);


        /*                                           // y0~2  x21
        --------------------------------------------------------------
        |                                            00              |
        |                                            00          X1  |
        |        XX    X1                            00              |
        |                                                            |
        |                                              00000000000000| //6칸  y4 x22~29
        |GO      XX    X2                              00            |
        |    ME                                        00            |
        |                                              00        X3  |  -// y5~9 x22      y678 보스12처치시 개방
        |        XX    X3                              00            |
        |                                              00            |
        |                    00                        00000000000000| //6칸  y10 x22~29
        |        XX                                                  |
        |                                            00              |
        |                                            00          X2  |
        |0000000000000000000000000000000000000000000000              |
        --------------------------------------------------------------
         */                                           // y12~14  x21

        //포탈 생성자, 리스트
        Portal portalToForest = new Portal(5,19,villageMap);
        Portal portalToVillage = new Portal(7,0,forestMap);







        /////////////////////////////////////게임 실행문

        // 게임 소개 , 시간 지연

        int sleepNum = 3 ; // 1 당 1초 sleep


        //소리 출력 쓰레드
        MusicThread thread01 = new MusicThread();
        new Thread(thread01).start();


        //ID 결정
        System.out.println("당신의 이름은 무엇인가요?\n");
        System.out.print("이름 입력:");
        player.playerID = scanner.nextLine();
        String tempID = player.playerID; //임시 ID 저장


        for(int i=0; i < 2; i++){
            Thread.sleep(1000);
        }

        //
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(player.playerID+"님은 낯선 마을에 도착한 모험가입니다...");
        System.out.println("\n\n\n\n\n\n\n\n\n\n");


        for(int i=0; i < sleepNum; i++){
            Thread.sleep(1000);
        }

        // 출력
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("이 마을은 타락한 마법사에 의해 사람들이 괴물로 변해가고 있습니다...");
        System.out.println("\n\n\n\n\n\n\n\n\n\n");

        for(int i=0; i < sleepNum; i++){
            Thread.sleep(1000);
        }

        //
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("당신이 남은 사람들을 구할 유일한 희망입니다...");
        System.out.println("\n\n\n\n\n\n\n\n\n\n");

        for(int i=0; i < sleepNum; i++){
            Thread.sleep(1000);
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("타락한 마법사를 처치하고 이 비극을 끝내주세요!");
        System.out.println("\n\n\n\n\n\n\n\n\n\n");

        for(int i=0; i < sleepNum; i++){
            Thread.sleep(1000);
        }


        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("깊은 숲에 숨어있는 타락한 마법사에게 가려면 그가 소환한 두 악마를 먼저 쓰러뜨려야 합니다.");
        System.out.println("하나는 화염을, 하나는 독을 내뿜는 악마입니다");
        System.out.println("두 악마를 모두 쓰러뜨리면 깊은 숲으로 들어가는 길이 열립니다\n");
        System.out.println("숲으로 가려면 마을 동쪽의 포탈을 이용하세요.");
        System.out.println("다시 돌아오려면 숲 서쪽의 포탈을 이용하면 됩니다.");
        System.out.println("\n\n");

        System.out.println("----------------------------------------------------------");




        // 캐릭터 선택
        System.out.println("\n\n1~4의 숫자 입력해서 캐릭터를 선택하세요\n");
        System.out.println("1-궁수 2-전사 3-기사 4-암살자\n");
        System.out.println("1.궁수는 적의 공격을 20% 확률로 회피할 수 있습니다");
        System.out.println("궁수 스킬 - 적의 다음 공격을 무조건 회피, 두번의 화살 피해를 입힙니다");
        System.out.println();
        System.out.println("2.전사는 기본 공격력이 높고 일반공격이 20% 높은 피해를 입힙니다");
        System.out.println("전사 스킬 - 두배의 피해를 입히고 절반 만큼 자신의 체력을 회복합니다");
        System.out.println();
        System.out.println("3.기사는 기본 체력이 높고 공격 받을때 20% 감소된 피해를 입습니다");
        System.out.println("기사 스킬 - 반격 자세를 취하고 적이 공격시 (몬스터+플레이어 공격력)X2 의 피해를 입힙니다");
        System.out.println();
        System.out.println("4.암살자는 공격시 20% 확률로 두배의 피해를 입히는 치명타를 가할 수 있습니다");
        System.out.println("암살자 스킬 - 칼날에 독을 묻혀 4턴 동안 공격력/2 피해를 입히고 그 동안 회복불가 상태로 만듭니다");
        System.out.println();

        charSelectLoop:
        while (true){
            System.out.print("숫자 입력:");
            String CharSelect;
            CharSelect = scanner.next();

            switch (CharSelect){
                case "1":
                    player = new P_Archer(5,5);
                    player.charName = "궁수";
                    player.setPlayerDodge_Archer(5);
                    break charSelectLoop;
                case "2":
                    player = new P_Warrior(5,5);
                    player.charName = "전사";
                    player.setPlayerATK(1.2);
                    break charSelectLoop;
                case "3":
                    player = new P_Knight(5,5);
                    player.charName = "기사";
                    player.setPlayerDefence_Knight(20);
                    break charSelectLoop;
                case "4":
                    player = new P_Assassin(5,5);
                    player.charName = "암살자";
                    player.setPlayerCritical_Assassin(5);
                    break charSelectLoop;
                default:
                    System.out.println("\n\n1~4의 숫자만 입력해주세요\n");
            }
        }

        player.playerID = tempID; // 임시 ID 불러와서 대입
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //공백 출력
        System.out.println(player.charName+"를 선택하셨습니다");


        ///
        System.out.println("----------------------------------------------------------");






        // 게임설명 (조작법, 게임 목표 등)(나중에 다시 호출가능한 메소드로 작성)
        introduce();
        System.out.println("\n\n계속 진행하시려면 아무키나 입력하세요");
        String next = scanner.next();
        while (!next.isEmpty()){
            break;
        }

        // 게임 실행!!
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n마을로 왔습니다\n");

        //시작 위치
        currentMap = villageMap;
        currentMap.mapName = "villageMap";
        villageMap.mapArray[5][5] = "Player";
        currentMap.printMap(currentMap.mapArray);


        // 액션 결정@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        while (true){
            System.out.println("\n입력값으로 행동을 정하세요:");
            actionSelect = scanner.next();




            switch (actionSelect){
                // 이동
                case "w":
                case "W":
                case "a":
                case "A":
                case "s":
                case "S":
                case "d":
                case "D":
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    player.moves_P(actionSelect, currentMap, player);

                    //몬스터를 만나면 전투시작
                    //몬스터 리스트의 몬스터 xy 좌표를 플레이어의 좌표와 비교
                    for (Monster monster : monsterList){
                        if (currentMap.metMonster(player,monster,currentMap)){
                            //전투 메소드
                            battleMethod(player,monster,currentMap,monsterList);
                            //엔딩 여부
                            if (player.ending){

                                //엔딩!!!!!!!!!!!!!!!!!!!!!!!!
                                //3초 지연 쓰레드

                                System.out.print("\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n타락한 마법사 쓰러뜨리고 마을엔 평화가 찾아왔습니다\n.\n.\n.\n.\n.\n.");
                                //3초 지연 쓰레드
                                for(int i=0; i < sleepNum; i++){
                                    Thread.sleep(1000);
                                }
                                System.out.print("\n.\n.\n.\n.\n.\n.\n감사합니다 "+player.playerID+"님\n.\n.\n.\n.\n.\n.");
                                //3초 지연 쓰레드
                                for(int i=0; i < sleepNum; i++){
                                    Thread.sleep(1000);
                                }
                                System.out.print("\n.\n.\n.\n.\n.\n.\n제작자 김주원\n.\n.\n.\n.\n.\n.");
                                //3초 지연 쓰레드
                                for(int i=0; i < sleepNum; i++){
                                    Thread.sleep(1000);
                                }
                                System.out.print("\n.\n.\n.\n.\n.\n.\nThank you for playing\n.\n.\n.\n.\n.\n.");
                                System.exit(0);



                            }
                            //퀘스트 달성 여부 메소드
                            player.isQuestComplete(player);
                            break;
                        }
                    }

                    break;
                // 퀘스트 창
                case "q":
                case "Q":
                    questWindow(player);
                    break;
                //스탯 창
                case "p":
                case "P":
                    statWindow(player);
                    break;
                case "f":
                case "F":
                    //NPC 가 옆에 있다면 상호작용 가능
                    for (NPC npc : npcList){
                        if (currentMap.metNPC(player,npc,currentMap)){
                            npc.interact_N(player,npc);
                            currentMap.printMap(currentMap.mapArray);
                            break;
                        }
                    }
                    break;
                case "g":
                case "G":
                    //Portal 이 옆에 있다면 상호작용
                    if (currentMap.metPortal(player,portalToForest) || currentMap.metPortal(player,portalToVillage)){
                        switch (currentMap.mapName){
                            case "villageMap":
                                System.out.println("숲에 들어 왔습니다");
                                //현위치를 길로 만듬
                                currentMap.mapArray[player.yPlace][player.xPlace] = "Road";
                                //맵 교체
                                villageMap = currentMap;
                                currentMap = forestMap;
                                //맵 이름 교체
                                currentMap.mapName = "forestMap";
                                //x y 좌표 수정
                                player.yPlace = 7;
                                player.xPlace = 1;
                                //숲맵 배열에 플레이어 스트링 위치
                                currentMap.mapArray[player.yPlace][player.xPlace] = "Player";
                                //몬스터 재생성!!
                                monster1 = new M_GeneralType1(1,6, forestMap);
                                monster2 = new M_GeneralType1(1,13, forestMap);
                                monster3 = new M_GeneralType1(5,6, forestMap);
                                monster4 = new M_GeneralType1(5,13, forestMap);

                                monster5 = new M_GeneralType2(9,6, forestMap);
                                monster6 = new M_GeneralType2(9,13, forestMap);
                                monster7 = new M_GeneralType2(13,6, forestMap);
                                monster8 = new M_GeneralType2(13,13, forestMap);

                                monsterList.add(monster1);
                                monsterList.add(monster2);
                                monsterList.add(monster3);
                                monsterList.add(monster4);

                                monsterList.add(monster5);
                                monsterList.add(monster6);
                                monsterList.add(monster7);
                                monsterList.add(monster8);

                                //맵출력
                                currentMap.printMap(currentMap.mapArray);
                                break;
                            case "forestMap":
                                System.out.println("마을로 왔습니다\n");
                                //현위치를 길로 만듬
                                currentMap.mapArray[player.yPlace][player.xPlace] = "Road";
                                //맵 교체
                                forestMap = currentMap;
                                currentMap = villageMap;
                                //맵 이름 교체
                                currentMap.mapName = "villageMap";
                                //x y 좌표 수정
                                player.yPlace = 5;
                                player.xPlace = 18;
                                //마을맵 배열에 플레이어 스트링 위치
                                currentMap.mapArray[player.yPlace][player.xPlace] = "Player";
                                //몬스터 삭제
                                monsterList.remove(monster1);
                                monsterList.remove(monster2);
                                monsterList.remove(monster3);
                                monsterList.remove(monster4);

                                monsterList.remove(monster5);
                                monsterList.remove(monster6);
                                monsterList.remove(monster7);
                                monsterList.remove(monster8);


                                //맵출력
                                currentMap.printMap(currentMap.mapArray);
                                break;
                        }
                    }

                    break;
                default:
                    System.out.println("올바른 값을 입력해주세요");
                    break;
            }


            //플레이어 사망
            if (player.playerHP <= 0){
                System.out.println(player.playerID+"님은 사망하셨습니다...");
                System.out.println("띠로리...");
                break;
            }
        }


    }

    //조작법 출력 메소드
    private static void introduce(){
        System.out.println("\n※게임 설명※\n");
        System.out.println("플레이어를 육성해서");
        System.out.println("몬스터를 잡거나 퀘스트를 완료하면 스탯이 증가합니다!!");
        System.out.println("보스는 강력하니 꾸준히 육성해서 도전해보세요!!\n");
        System.out.println();
        System.out.println("플레이어가 몬스터 가까이 가면 전투가 발생합니다");
        System.out.println("NPC, 상점, 대장간, 포탈 가까이 가면 상호작용이 가능합니다");
        System.out.println("NPC에게선 퀘스트를 받을 수 있습니다");
        System.out.println("상점에서는 회복물약을 구매할 수 있습니다");
        System.out.println("대장간에서는 무기를 강화하거나 스킬 포인트를 구매할 수 있습니다");
        System.out.println();
        System.out.println("\n※맵 아이콘※\n");
        System.out.println("ME = 플레이어");
        System.out.println("P1 = 마을 이장");
        System.out.println("P2 = 현상금 ");
        System.out.println("WP = 대장간");
        System.out.println("SP = 상점");
        System.out.println("GO = 포탈");
        System.out.println("M1 = 괴물로 변한 마을사람");
        System.out.println("M2 = 괴물로 변한 군인들");

        System.out.println("00 = 막힌 길\n");
        System.out.println("\n※조작법※\n");
        System.out.println("⊙ 이동 => W-상 S-하 A-좌 D-우");
        System.out.println("⊙ NPC와 대화, 상점, 대장간 이용 => F");
        System.out.println("⊙ Portal 이용 => G");
        System.out.println("⊙ 퀘스트 목록 보기 => Q");
        System.out.println("⊙ 플레이어 스탯창 => P");


    }

    // 퀘스트 창 출력 메소드
    private static void questWindow(Player player){
        System.out.println("진행중인 퀘스트");
        for (String qYet:player.playerQuest_yet){
            System.out.print(" - ");
            System.out.println(qYet);
        }
        System.out.println("완료한 퀘스트");
        for (String qDone:player.playerQuest_done){
            System.out.print(" - ");
            System.out.println(qDone);
        }
    }

    // 스탯 창 출력 메소드
    private static void statWindow(Player player){
        System.out.println("이름: "+player.playerID);
        System.out.println("직업: "+player.charName);
        System.out.println("공격력: "+player.playerATK);
        System.out.println("현재체력: "+player.playerHP);
        System.out.println("최대체력: "+player.playerMaxHP);
        System.out.println("스킬 포인트: "+player.playerSkillPoint);
        System.out.println("돈: "+player.playerMoney);
        System.out.println("50회복물약: "+player.playerPotion1);
        System.out.println("200회복물약: "+player.playerPotion2);
    }




    // 전투 메소드 (전투시작, 전투진행, 전투 종료)
    private static void battleMethod(Player player, Monster monster,GameMap map,ArrayList<Monster> monsterList){
        battleStart(player,monster);
        battleOn(player,monster,map,monsterList);
    }

    // 전투발생 메소드 (플레이어, 몬스터 인자로 받아서 전투실행, 정보 출력)
    private static void battleStart(Player player, Monster monster){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("----------------------------------------------------------");
        System.out.println("몬스터를 마주쳤습니다!! 전투발생!!\n");
        System.out.println("플레이어\n공격력:"+player.playerATK+"\n체력:"+player.playerHP+"\n스킬 포인트:"+player.playerSkillPoint+"\n50회복물약 개수:"+player.playerPotion1+"\n200회복물약 개수:"+player.playerPotion2);
        System.out.println("\n몬스터\n공격력:"+monster.monsterATK+"\n체력:"+monster.monsterHP);
    }

    // 전투진행 메소드 (턴제로 진행, 플레이어 행동후 몬스터 공격 => 반복)
    private static void battleOn (Player player, Monster monster,GameMap map,ArrayList<Monster> monsterList){
        battleLoop:
        while (true) {
            System.out.println("\n1-일반공격\n2-스킬사용\n3-회복포션 사용\nZ-도주(1/3확률로 성공)\n");
            Scanner scanner = new Scanner(System.in);
            String action = scanner.next();
            switch (action) {
                case "2": // 스킬 사용
                    if (player.playerSkillPoint > 0){ // 사용 가능
                        switch (player.skill_P(player, monster)){
                            case 1: //궁수
                                player.archerSkill++;
                            case 2: //기사
                            case 3: //암살자
                            default:
                        }
                        break;
                    }else { // 사용 불가
                        System.out.println("스킬포인트가 부족해서 스킬발동 불가");
                        continue battleLoop;
                    }
                case "3": // 포션 사용
                    // 플레이어 저주 시
                    if (player.curse > 0){
                        System.out.println("플레이어가 저주에 당해 회복을 할 수 없습니다\n남은 횟수:"+(player.curse-1));
                        continue battleLoop;
                    }
                    // 플레이어 중독 시
                    if (player.poison > 0){
                        System.out.println("플레이어가 독에 당해 회복을 할 수 없습니다\n남은 횟수:"+(player.poison-1));
                        continue battleLoop;
                    }
                    //중독이 아닐 때
                    else if (player.heal_P(player)){ // 사용 가능
                        break;
                    }else { // 사용 불가
                        continue battleLoop;
                    }
                case "z": // 도주 선택
                case "Z":
                    Random random = new Random();
                    if (random.nextInt(3) != 0) { // 도주 (1,2나옴)
                        System.out.println("도주 성공~");
                        map.printMap(map.mapArray);
                        return;
                    } else { // 도주불가 (0나옴)
                        System.out.println("도주 실패...");
                        break;
                    }

                case "1": // 일반 공격
                    player.attack_P(player, monster);
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
                    continue battleLoop;
            }



            //암살자 스킬 시
            if (monster.playerPoison > 0){
                monster.playerPoison--;
                monster.monsterHP -= player.playerATK/2;
                System.out.println("\n암살자의 독에 당해 몬스터가 데미지를 입습니다");
                System.out.println("\n데미지-"+player.playerATK/2+"\n몬스터체력:"+monster.monsterHP);
            }

            //플레이어 저주 시
            if (player.curse > 0){
                player.playerHP -= monster.monsterATK/3;
                System.out.println("\n저주 데미지:"+monster.monsterATK/3+"\n남은 횟수:"+(player.curse-1));
                player.curse--;
            }

            // 중독 변수
            if (player.poison > 0){
                player.poison--;
            }

            //플레이어 화상 시
            if (player.burning > 0){
                player.playerHP -= monster.monsterATK/3;
                System.out.println("\n화상 데미지:"+monster.monsterATK/3+"\n남은 횟수:"+(player.burning-1));
                player.burning--;
            }

            // 몬스터 사망 (종료시 플레이어의 변수 증감(아이템, 퀘스트))
            if (monster.monsterHP <= 0) {
                System.out.println("\n\n몬스터 사망! 전투 종료!");
                monster.statUp(player);
                map.mapArray[monster.monsterPlaceY][monster.monsterPlaceX] = "Road"; // 출력맵에서 몬스터 삭제



                //보스 1,2 처치시 통로 OPEN
                if (player.boss1clear && player.boss2clear && player.boss12clear){
                    player.boss12clear = false;
                    System.out.println("\n\n\n깊은 숲에 숨은 타락한 마법사에게 가는 길이 열렸습니다!!");
                    System.out.println("하지만 조심하세요. 지금까지 상대했던 적들과는 다를 것 입니다.");
                    System.out.println("");
                    map.mapArray[6][23] = "Road";
                    map.mapArray[7][23] = "Road";
                    map.mapArray[8][23] = "Road";

        /*                                           // y0~2  x21
        --------------------------------------------------------------
        |                                            00              |
        |                                            00          X1  |
        |        XX    X1                            00              |
        |                                                            |
        |                                              00000000000000| //6칸  y4 x22~29
        |GO      XX    X2                              00            |
        |    ME                                        00            |
        |                                              00        X3  |  -// y5~9 x22      y678 보스12처치시 개방
        |        XX    X3                              00            |
        |                                              00            |
        |                    00                        00000000000000| //6칸  y10 x22~29
        |        XX                                                  |
        |                                            00              |
        |                                            00          X2  |
        |                                            00              |
        --------------------------------------------------------------
         */                                           // y12~14  x21

                }
                //엔딩 &프로그램 종료    @@@@@@@@@@@@@@@@@@@@@@@@@@@@
                if (player.boss1clear && player.boss2clear && player.boss3clear){
                    player.ending = true;
                    return;
                }

                //변수 초기화
                player.archerSkill = 0;
                player.assassinSkill = 0;
                player.knightSkill = 0;

                monsterList.remove(monster);
                player.burning = 0;
                player.poison = 0;
                player.curse = 0;
                map.printMap(map.mapArray);
                break;
            }

            //몬스터 공격
            //보스 스킬시 int 리턴 - 상태이상 여부 결정
            if (player.archerSkill != 0){ // 궁수 스킬 발동시 회피,공격
                player.archerSkill = 0;
                System.out.println("공격 회피!");
            }else if (player.knightSkill > 0){ //기사 스킬 발동시 회피, 반격
                player.knightSkill--;
                monster.monsterHP -= monster.monsterATK*2 + player.playerATK*2;
                int damage = monster.monsterATK*2 + player.playerATK*2;
                System.out.println("반격!");
                System.out.println("\n데미지-"+damage+"\n몬스터체력:"+monster.monsterHP);
            }else { // 궁수 기사 스킬 발동하지 않았을때
                switch (monster.m_ATK(player,monster)){
                    case 1: //중독 - 두턴 동안 플레이어 회복 불가
                        player.poison += 2;
                        System.out.println("적의 독에 당했습니다!\n두 턴 동안 체력을 회복할 수 없습니다");
                        break;
                    case 2: //화상 - 두턴 동안 데미지
                        player.burning += 2;
                        System.out.println("적의 화염 공격에 화상을 입었습니다!\n두 턴 동안 데미지를 입습니다");
                        break;
                    case 3: //화상 - 두턴 동안 데미지
                        player.curse += 2;
                        System.out.println("적의 저주에 당했습니다!\n두 턴 동안 데미지를 입고 체력을 회복할 수 없습니다");
                        break;
                }
            }


            System.out.println("\n\n\n----------------------------------------------------------");



            // 플레이어 사망
            if (player.playerHP <= 0) {
                System.out.println("HP가 0이 되었습니다.");
                break;
            }

        }
    }






    //맵 구현 메소드("Road"로 모두 채우고 그 위에 "EndWay" "NPC" "Shop" "Monster" "Player"까지 덮어쓰기)
    private static void makeVillageMap(String[][] mapArray){
        // Road 로 채움
        for (String[] strings : mapArray) {
            Arrays.fill(strings, "Road");
        }
        //EndWay
        mapArray[0][0] = "EndWay";
        mapArray[0][1] = "EndWay";
        mapArray[0][2] = "EndWay";
        mapArray[0][3] = "EndWay";
        mapArray[0][4] = "EndWay";
        mapArray[0][5] = "EndWay";
        mapArray[1][0] = "EndWay";
        mapArray[1][1] = "EndWay";
        mapArray[1][2] = "EndWay";
        mapArray[2][0] = "EndWay";

    }

    private static void makeForestMap(String[][] mapArray){

        // Road 로 채움
        for (String[] strings : mapArray) {
            Arrays.fill(strings, "Road");
        }
        //EndWay
        //1
        mapArray[0][21] = "EndWay";
        mapArray[1][21] = "EndWay";
        mapArray[2][21] = "EndWay";

        //2
        mapArray[4][23] = "EndWay";
        mapArray[4][24] = "EndWay";
        mapArray[4][25] = "EndWay";
        mapArray[4][26] = "EndWay";
        mapArray[4][27] = "EndWay";
        mapArray[4][28] = "EndWay";
        mapArray[4][29] = "EndWay";

        //3
        mapArray[5][23] = "EndWay";
        mapArray[6][23] = "EndWay";
        mapArray[7][23] = "EndWay";
        mapArray[8][23] = "EndWay";
        mapArray[9][23] = "EndWay";

        //4
        mapArray[10][23] = "EndWay";
        mapArray[10][24] = "EndWay";
        mapArray[10][25] = "EndWay";
        mapArray[10][26] = "EndWay";
        mapArray[10][27] = "EndWay";
        mapArray[10][28] = "EndWay";
        mapArray[10][29] = "EndWay";

        //5
        mapArray[12][22] = "EndWay";
        mapArray[13][22] = "EndWay";
        mapArray[14][22] = "EndWay";


                /*                                           // 1   y0~2  x22
        --------------------------------------------------------------
        |                                            00              |
        |                                            00          X1  |
        |        XX    X1                            00              |
        |                                                            |
        |                                              00000000000000| //2   7칸  y4 x23~29
        |GO      XX    X2                              00            |
        |    ME                                        00            |
        |                                              00        X3  |  -//3    y5~9 x23      y678 보스12처치시 개방
        |        XX    X3                              00            |
        |                                              00            |
        |                    00                        00000000000000| //4   7칸  y10 x22~29
        |        XX                                                  |
        |                                            00              |
        |                                            00          X2  |
        |                                            00              |
        --------------------------------------------------------------
         */                                           //5    y12~14  x22

    }

}
