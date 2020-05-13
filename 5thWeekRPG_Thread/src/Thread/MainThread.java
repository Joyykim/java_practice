package Thread;

import Monster.*;
import NPC.*;
import Player.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MainThread extends Thread {

    ////////////////////////////////생성자
    private DayOrNightThread day_Night_Thread;
    public Player player;

    //쓰레드 생성자
    private final MusicThread musicThread_intro = new MusicThread("c:/a_project_sound/intro.wav"); //인트로 뮤직

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();


    //게임맵 생성자
    public GameMap villageMap = new GameMap(10,20,GameMap.VILLAGE);
    public GameMap forestMap = new GameMap(15,30,GameMap.FOREST);
    public GameMap forestMap_night = new GameMap(15,30,GameMap.FOREST);
    public GameMap currentMap;


    ///////////////몬스터 생성자, 리스트
/*
    M_GeneralType1 monster2;
    M_GeneralType1 monster3;
    M_GeneralType1 monster4;

    M_GeneralType2 monster5;
    M_GeneralType2 monster6;
    M_GeneralType2 monster7;
    M_GeneralType2 monster8;*/

    //일반, 보스 몬스터 리스트 생성
    //일반 몬스터는 숲으로 포탈시에 생성,add
    private ArrayList<Monster> monsterList = new ArrayList<Monster>();
    private ArrayList<Monster> bossMonsterList = new ArrayList<Monster>();


    //음악 스트링
    private final String BATTLE_MUSIC = "c:/a_project_sound/battle.wav";
    private final String ENDING_MUSIC = "c:/a_project_sound/ending.wav";
    private final String PORTAL_SOUND = "c:/a_project_sound/ending.wav";


    @Override
    public void run(){

        //맵 구현
        makeVillageMap(villageMap.mapArray);//마을맵
        makeForestMap(forestMap.mapArray);//사냥맵
        makeForestMap(forestMap_night.mapArray);//사냥맵 밤


        //플레이어 생성자
        player = new Player(5,16,villageMap);


        //NPC 생성자, 리스트
        N_PersonType1 n_person1 = new N_PersonType1(3,5,this);
        N_PersonType2 n_person2 = new N_PersonType2(3,10,this);
        N_Shop_Potion n_shop_potion = new N_Shop_Potion(7,5,this);
        N_Shop_Weapon n_shop_weapon = new N_Shop_Weapon(7,10,this);
        ArrayList<NPC> npcList= new ArrayList<NPC>();

        //메인퀘스트 수락시 출력문 변수 - 수락시 한번만 출력되게
        boolean first_Q = true;
        boolean second_Q = true;
        boolean third_Q = true;

        //보스는 밤맵에 생성
        M_BossType1 monsterBoss1 = new M_BossType1(1,27, this);
        M_BossType2 monsterBoss2 = new M_BossType2(13,27, this);
        M_BossType3 monsterBoss3 = new M_BossType3(7,27, this);



        //포탈 생성자 - 포탈은 3개 밖에 없으니 1각각 or 연산자로 처리
        Portal portal_village = new Portal(5,19,villageMap);
        Portal portal_forest = new Portal(7,0,forestMap);
        Portal portal_forest_night = new Portal(7,0,forestMap_night);


        //객체들을 리스트에 추가
        npcList.add(n_person1);
        npcList.add(n_person2);
        npcList.add(n_shop_potion);
        npcList.add(n_shop_weapon);

        bossMonsterList.add(monsterBoss1);
        bossMonsterList.add(monsterBoss2);
        bossMonsterList.add(monsterBoss3);


        /////////////////////////////////////게임 실행문

        //인트로 뮤직 쓰레드
        musicThread_intro.start();

        //인트로, 직업 선택 메소드
        player = gameIntro_CharSelect(player);

        // 게임설명 (조작법, 게임 목표 등)(나중에 다시 호출가능한 메소드로 작성)
        introduce();
        System.out.println("\n\n계속 진행하시려면 아무키나 입력하세요");
        scanner.next();


        //인트로 끝내기
        synchronized (musicThread_intro){
            //System.out.println("stop intro");
            musicThread_intro.stop();
            //System.out.println("musicThread_intro 상태: "+musicThread_intro.getState());
        }


        // 게임 실행!!
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n마을로 왔습니다\n");

        //시작 위치
        currentMap = villageMap;
        currentMap.mapName = GameMap.VILLAGE;
        currentMap.printMap();



        //낮,밤 쓰레드 최초 생성,실행 (BGM 도 함께 컨트롤)
        //낮,밤 쓰레드는 상호작용(전투,거래,대화)동안은 정지
        day_Night_Thread = new DayOrNightThread(this,true);
        day_Night_Thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}


        // 액션 결정@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        actionLoop:
        while (true){

            //디버깅 출력
/*            System.out.println("☆");
            System.out.println("P x좌표: "+player.xPlace);
            System.out.println("P y좌표: "+player.yPlace);
            System.out.println("현재맵 이름: "+currentMap.mapName);
            System.out.println("isDay: "+ day_Night_Thread.isDay);
            System.out.println("☆");*/

            System.out.println("\n입력으로 행동을 정하세요: ");

            // 플레이어 행동 변수
            String actionSelect = scanner.next();

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
                    ////////////////////////////////////////////////////////////////
                    player.moves_P(actionSelect, currentMap);

                    //최종보스 처치했다면 전투없음
                    if (!player.ending){
                        if (day_Night_Thread.isDay){
                            System.out.println("지금은 낮입니다.");
                        }else {
                            System.out.println("지금은 밤입니다.");
                        }

                        /////보스 리스트 검사 - 밤일때만
                        if (!day_Night_Thread.isDay){
                            for (Monster monster : bossMonsterList){
                                if (currentMap.metMonster(player,monster,currentMap)){
                                    //보스 전투 실행
                                    boss_battle();
                                    continue actionLoop;
                                }
                            }
                        }




                        //랜덤 전투
                        //숲이거나 한밤중의 습격 퀘스트22 진행 중일때만
                        if(currentMap.mapName.equals(GameMap.FOREST) || player.playerQuest_yet.contains(N_PersonType2.QUEST22)){

                            //밤이면 움직일때마다 랜덤 전투
                            if (!day_Night_Thread.isDay){
                                //랜덤전투 몬스터 생성, 전투 실행
                                M_GeneralType_Random monsterRandom = new M_GeneralType_Random(this);
                                random_battle(monsterRandom);
                                continue actionLoop;

                            }
                        }

                        //몬스터를 만나면 전투시작
                        //몬스터 리스트의 몬스터 xy 좌표를 플레이어의 좌표와 비교

                        /////일반 몬스터 리스트 검사 - 낮일때만
                        if (day_Night_Thread.isDay){
                            for (Monster monster : monsterList){
                                if (currentMap.metMonster(player,monster,currentMap)){

                                    //밤낮 쓰레드 종료, 남은 시간 리턴 메소드
                                    int leftTime = battle_stop_getLeftTime();

                                    //전투음악 재생
                                    MusicThread musicThread_battle = new MusicThread(BATTLE_MUSIC);
                                    musicThread_battle.start();

                                    //전투 메소드
                                    battleMethod(player,monster,currentMap,monsterList,this);

                                    //전투음악 삭제
                                    synchronized (musicThread_battle){
                                        musicThread_battle.stop();
                                    }

                                    //퀘스트 달성 여부 메소드
                                    player.isQuestComplete();

                                    //밤낮 쓰레드 생성,실행
                                    //밤낮 쓰레드 생성,실행
                                    synchronized (day_Night_Thread){
                                        //낮, 중간부터 실행
                                        day_Night_Thread = new DayOrNightThread(this,true,leftTime);
                                        day_Night_Thread.start();
                                    }

                                    continue actionLoop;
                                }
                            }
                        }
                    }
                    continue actionLoop;
                // 퀘스트 창
                case "q":
                case "Q":
                    day_Night_Thread.printOn_interact = false; //상호 작용시 낮밤 쓰레드 출력문 off
                    questWindow(player);
                    currentMap.printMap();
                    day_Night_Thread.printOn_interact = true; //상호 작용 끝나면 낮밤 쓰레드 출력문 on
                    break;
                //스탯 창
                case "p":
                case "P":
                    day_Night_Thread.printOn_interact = false; //상호 작용시 낮밤 쓰레드 출력문 off
                    statWindow(player);
                    day_Night_Thread.printOn_interact = true; //상호 작용 끝나면 낮밤 쓰레드 출력문 on
                    break;
                //상호작용
                case "f":
                case "F":
                    //NPC 가 옆에 있다면 상호작용 가능
                    for (NPC npc : npcList){
                        if (currentMap.metNPC(player,npc,currentMap)){

                            day_Night_Thread.printOn_interact = false; //상호 작용시 낮밤 쓰레드 출력문 off
                            npc.interact_N();
                            day_Night_Thread.printOn_interact = true; //상호 작용 끝나면 낮밤 쓰레드 출력문 on


                            //첫번째 악마 메인퀘스트 수락시
                            if (player.playerQuest_yet.contains(N_PersonType1.QUEST13) && first_Q){
                                first_Q = false;
                                System.out.println("\n\n\n숲의 북쪽 동굴에 숨은 첫번째 악마에게 가는 길이 열렸습니다!!");
                                System.out.println("악마가 내뿜는 불을 조심하세요. 불에 맞으면 지속적인 피해를 입습니다.");
                                System.out.println();
                                forestMap_night.mapArray[4][21] = GameMap.ROAD;
                                forestMap_night.mapArray[4][22] = GameMap.ROAD;
                                forestMap.mapArray[4][21] = GameMap.ROAD;
                                forestMap.mapArray[4][22] = GameMap.ROAD;
                            }

                            //두번째 악마 메인퀘스트 수락시
                            if (player.playerQuest_yet.contains(N_PersonType1.QUEST14) && second_Q){
                                second_Q = false;
                                System.out.println("\n\n\n숲의 남쪽 늪지대에 숨은 두번째 악마에게 가는 길이 열렸습니다!!");
                                System.out.println("악마가 내뿜는 독을 조심하세요. 독에 당하면 회복물약도 소용없습니다.");
                                System.out.println();
                                forestMap_night.mapArray[10][21] = GameMap.ROAD;
                                forestMap_night.mapArray[10][22] = GameMap.ROAD;
                                forestMap.mapArray[10][21] = GameMap.ROAD;
                                forestMap.mapArray[10][22] = GameMap.ROAD;
                            }

                            //마지막 메인퀘스트 수락시
                            if (player.playerQuest_yet.contains(N_PersonType1.QUEST15) && third_Q){
                                third_Q = false;
                                System.out.println("\n\n\n깊은 숲에 숨은 타락한 마법사에게 가는 길이 열렸습니다!!");
                                System.out.println("조심하세요. 지금까지 상대했던 적들과는 다를 것 입니다.");
                                System.out.println();
                                forestMap_night.mapArray[6][23] = GameMap.ROAD;
                                forestMap_night.mapArray[7][23] = GameMap.ROAD;
                                forestMap_night.mapArray[8][23] = GameMap.ROAD;
                                forestMap.mapArray[6][23] = GameMap.ROAD;
                                forestMap.mapArray[7][23] = GameMap.ROAD;
                                forestMap.mapArray[8][23] = GameMap.ROAD;
                            }

                            currentMap.printMap();
                            break;
                        }
                    }
                    break;

                case "g":
                case "G":
                    //Portal 이 옆에 있다면 상호작용
                    if (currentMap.metPortal(player,portal_village) || currentMap.metPortal(player,portal_forest) || currentMap.metPortal(player,portal_forest_night)){
                        //맵 이름 참조해서 이동할 맵 결정
                        switch (currentMap.mapName){
                            // 마을에서 숲으로 이동

                            case GameMap.VILLAGE:

                                // 낮  마을 -> 숲
                                if (day_Night_Thread.isDay){

                                    System.out.println("낮  마을 -> 숲\n");
                                    //현위치를 길로 만듬
                                    currentMap.mapArray[player.yPlace][player.xPlace] = GameMap.ROAD;
                                    //맵 교체
                                    villageMap = currentMap;
                                    currentMap = forestMap;
                                    //맵 이름 교체
                                    currentMap.mapName = GameMap.FOREST;

                                    //x y 좌표 수정
                                    player.yPlace = 7;
                                    player.xPlace = 1;
                                    //숲맵 배열에 플레이어 스트링 위치
                                    currentMap.mapArray[player.yPlace][player.xPlace] = GameMap.PLAYER;

                                    //엔딩시 몬스터 생성X
                                    if (!player.ending){
                                        //몬스터 재생성
                                        monster_regenerate(this);
                                    }

                                    //맵출력
                                    currentMap.printMap();


                                    //밤낮 쓰레드 삭제후 재생성 - 낮
                                    portal_dayNight_regenerate(player,true);

                                    continue actionLoop;

                                }else {

                                    // 밤  마을 -> 숲

                                    System.out.println("밤  마을 -> 숲\n");
                                    //현위치를 길로 만듬
                                    currentMap.mapArray[player.yPlace][player.xPlace] = GameMap.ROAD;
                                    //맵 교체
                                    villageMap = currentMap;
                                    currentMap = forestMap_night;
                                    //맵 이름 교체
                                    currentMap.mapName = GameMap.FOREST;

                                    //x y 좌표 수정
                                    player.yPlace = 7;
                                    player.xPlace = 1;
                                    //숲맵 배열에 플레이어 스트링 위치
                                    currentMap.mapArray[player.yPlace][player.xPlace] = GameMap.PLAYER;

                                    //밤이면 랜덤전투 발생이기 때문에 몬스터 생성X

                                    //맵출력
                                    currentMap.printMap();


                                    //밤낮 쓰레드 삭제후 재생성 - 밤
                                    portal_dayNight_regenerate(player,false);
                                    continue actionLoop;
                                }

                            case GameMap.FOREST:
                                // 숲에서 마을로 이동

                                if (day_Night_Thread.isDay){

                                    // 낮  숲 -> 마을

                                    System.out.println("낮  숲 -> 마을\n");
                                    //현위치를 길로 만듬
                                    currentMap.mapArray[player.yPlace][player.xPlace] = GameMap.ROAD;
                                    //맵 교체
                                    forestMap = currentMap;
                                    currentMap = villageMap;
                                    //맵 이름 교체
                                    currentMap.mapName = GameMap.VILLAGE;
                                    //x y 좌표 수정
                                    player.yPlace = 5;
                                    player.xPlace = 18;
                                    //마을맵 배열에 플레이어 스트링 위치
                                    currentMap.mapArray[player.yPlace][player.xPlace] = GameMap.PLAYER;

                                    //몬스터 삭제
                                    if (!player.ending){
                                        monsterList.removeAll(monsterList);
                                    }

                                    //맵출력
                                    currentMap.printMap();

                                    //밤낮 쓰레드 삭제후 재생성 - 낮
                                    portal_dayNight_regenerate(player,true);
                                    continue actionLoop;

                                }else {

                                    // 밤  숲 -> 마을

                                    System.out.println();
                                    System.out.println("밤  숲 -> 마을\n");
                                    //현위치를 길로 만듬
                                    currentMap.mapArray[player.yPlace][player.xPlace] = GameMap.ROAD;
                                    //맵 교체
                                    forestMap_night = currentMap;
                                    currentMap = villageMap;
                                    //맵 이름 교체
                                    currentMap.mapName = GameMap.VILLAGE;
                                    //x y 좌표 수정
                                    player.yPlace = 5;
                                    player.xPlace = 18;
                                    //마을맵 배열에 플레이어 스트링 위치
                                    currentMap.mapArray[player.yPlace][player.xPlace] = GameMap.PLAYER;

                                    //몬스터 삭제
                                    if (!player.ending){
                                        monsterList.clear();
                                    }

                                    //맵출력
                                    currentMap.printMap();

                                    //밤낮 쓰레드 삭제후 재생성 - 밤
                                    portal_dayNight_regenerate(player,false);

                                    continue actionLoop;
                                }
                        }
                    }

                    break;
                default:
                    System.out.println("올바른 값을 입력해주세요");
                    continue actionLoop;
            }

            //플레이어 사망
            if (player.playerHP <= 0){
                System.out.println(player.playerID+"님은 사망하셨습니다...");
                System.out.println("띠로리...");
                System.exit(0);
            }
        }
    }


    // 플레이어 직업 선택후 플레이어 객체 리턴
    private Player gameIntro_CharSelect(Player player){

        SkipThread skipThread = new SkipThread(this);

        // 게임 소개 , 시간 지연


        //ID 결정
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("당신의 이름은 무엇인가요?\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        System.out.print("이름 입력:");
        player.playerID = scanner.next();
        String tempID = player.playerID; //임시 ID 저장

        boolean watch_intro = true;

        skipLoop:
        while (true){
            System.out.println("1. 인트로 보기");
            System.out.println("2. 인트로 건너뛰기");
            String skip = scanner.next();
            switch (skip){
                case "1":
                    System.out.print("인트로가 실행됩니다");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ignored) { }
                    System.out.print(".");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ignored) { }
                    System.out.print(".");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ignored) { }
                    System.out.print(".");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ignored) { }
                    System.out.print(".");



                    break skipLoop;
                case "2":
                    System.out.println("인트로를 건너뜁니다.");
                    watch_intro = false;
                    break skipLoop;
                default:
                    System.out.println("\n잘못 입력하셨습니다.\n\n");
            }
        }


        if (watch_intro){

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(player.playerID+"님은 낯선 마을에 도착한 모험가입니다...");
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            System.out.print("                                                           대사 스킵 - 아무키나 입력");

            try {
                skipThread = new SkipThread(this);
                skipThread.start();
                Thread.sleep(3000);
            } catch (InterruptedException ignored) { }

            // 출력
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("이 마을은 타락한 마법사에 의해 사람들이 괴물로 변해가고 있습니다...");
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            System.out.print("                                                           대사 스킵 - 아무키나 입력");

            try {
                skipThread = new SkipThread(this);
                skipThread.start();
                Thread.sleep(3000);
            } catch (InterruptedException ignored) { }

            //
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("당신이 남은 사람들을 구할 유일한 희망입니다...");
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            System.out.print("                                                           대사 스킵 - 아무키나 입력");

            try {
                skipThread = new SkipThread(this);
                skipThread.start();
                Thread.sleep(3000);
            } catch (InterruptedException ignored) { }

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("타락한 마법사를 처치하고 이 비극을 끝내주세요!");
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            System.out.print("                                                           대사 스킵 - 아무키나 입력");

            try {
                skipThread = new SkipThread(this);
                skipThread.start();
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {
            }finally {
                skipThread.stop();
            }
        }
        scanner.nextLine();



        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n※플레이 목표※\n");
        System.out.println("깊은 숲에 숨어있는 타락한 마법사를 처치하세요.");
        System.out.println("그에게 가려면 먼저 그가 소환한 두 악마를 쓰러뜨려야 합니다.");
        System.out.println("하나는 화염을, 하나는 독을 내뿜는 악마입니다.");
        System.out.println("두 악마를 모두 쓰러뜨리면 깊은 숲으로 들어가는 길이 열립니다.");
        System.out.println("※ 마을 이장에게 가서 메인퀘스트를 받아야지만 악마에게 갈 수 있습니다.\n");
        System.out.println("숲으로 가려면 마을 동쪽의 포탈을 이용하세요.");
        System.out.println("다시 돌아오려면 숲 서쪽의 포탈을 이용하면 됩니다.");
        System.out.println();

        System.out.println("----------------------------------------------------------");

        // 캐릭터 선택
        System.out.println("\n※직업 선택※\n");
        System.out.println("1~4의 숫자 입력해서 직업을 선택하세요.");
        System.out.println();
        System.out.println("1-궁수 2-전사 3-기사 4-암살자");
        System.out.println();
        System.out.println("1.궁수는 적의 공격을 20% 확률로 회피할 수 있습니다.");
        System.out.println("궁수 스킬 - 적의 다음 공격을 무조건 회피, 두번의 화살 피해를 입힙니다.");
        System.out.println();
        System.out.println("2.전사는 기본 공격력이 높고 일반공격이 20% 높은 피해를 입힙니다.");
        System.out.println("전사 스킬 - 두배의 피해를 입히고 절반 만큼 자신의 체력을 회복합니다.");
        System.out.println();
        System.out.println("3.기사는 기본 체력이 높고 공격 받을때 20% 감소된 피해를 입습니다.");
        System.out.println("기사 스킬 - 반격 자세를 취하고 적이 공격시 (몬스터+플레이어 공격력)X2 의 피해를 입힙니다.");
        System.out.println();
        System.out.println("4.암살자는 공격시 20% 확률로 두배의 피해를 입히는 치명타를 가할 수 있습니다.");
        System.out.println("암살자 스킬 - 칼날에 독을 묻혀 4턴 동안 공격력/2 피해를 입히고 그 동안 회복불가 상태로 만듭니다.");
        System.out.println();

        charSelectLoop:
        while (true){
            System.out.print("숫자 입력:");
            String CharSelect;
            try {
                Scanner scanner1 = new Scanner(System.in);
                CharSelect = scanner1.nextLine();
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("\n\n1~4의 숫자만 입력해주세요\n");
                continue charSelectLoop;
            }

            switch (CharSelect){
                case "1":
                    player = new P_Archer(player.yPlace,player.xPlace,villageMap);
                    player.charName = "궁수";
                    player.setPlayerDodge_Archer(5);
                    break charSelectLoop;
                case "2":
                    player = new P_Warrior(player.yPlace,player.xPlace,villageMap);
                    player.charName = "전사";
                    player.setPlayerATK(1.2);
                    break charSelectLoop;
                case "3":
                    player = new P_Knight(player.yPlace,player.xPlace,villageMap);
                    player.charName = "기사";
                    player.setPlayerDefence_Knight(20);
                    break charSelectLoop;
                case "4":
                    player = new P_Assassin(player.yPlace,player.xPlace,villageMap);
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
        return player;
    }



    //조작법 출력 메소드
    private static void introduce(){
        System.out.println("\n※게임 설명※\n");
        System.out.println("플레이어를 육성해서 P1 NPC가 주는 메인 퀘스트를 진행하세요!!");
        System.out.println("괴물을 잡거나 퀘스트를 완료하면 스탯이 증가합니다!!");
        System.out.println("보스는 강력하니 꾸준히 육성해서 도전해보세요!!\n");
        System.out.println();
        System.out.println("플레이어가 괴물에게 가까이 가면 전투가 발생합니다.");
        System.out.println("3분마다 밤낮이 바뀝니다. 밤에는 괴물이 보이지 않고 플레이어 이동중에 급습하여 전투가 발생합니다.");
        System.out.println("밤에는 괴물이 강해지니 조심하세요.");
        System.out.println("두 악마, 타락한 마법사에는 낮에는 보이지 않고 밤에만 접근할 수 있습니다.");
        System.out.println();
        System.out.println("NPC, 상점, 대장간, 포탈 가까이 가면 상호작용이 가능합니다.");
        System.out.println("NPC에게선 퀘스트를 받을 수 있습니다.");
        System.out.println("상점에서는 회복물약을 구매할 수 있습니다.");
        System.out.println("대장간에서는 무기를 강화하거나 스킬 포인트를 구매할 수 있습니다.");
        System.out.println();
        System.out.println("\n※맵 아이콘※\n");
        System.out.println("ME = 플레이어");
        System.out.println("P1 = 마을 이장");
        System.out.println("P2 = 현상금 게시판");
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
        Scanner scanner = new Scanner(System.in);
        loop1:
        while (true){
            System.out.println("\n1. 진행중인 퀘스트");
            System.out.println("2. 완료한 퀘스트");
            System.out.println("Z. 나가기");
            System.out.println("입력하세요:");
            String sc1 = scanner.next();
            switch (sc1) {
                case "1":
                    loop2:
                    while (true) {
                        //진행중인 퀘스트 출력
                        System.out.println("\n※진행중인 퀘스트 목록");
                        System.out.println("진행 상황을 확인하려면 해당 퀘스트 숫자를 입력하세요");
                        System.out.println("Z를 입력하면 뒤로가기\n");
                        for (String qYet : player.playerQuest_yet) {
                            switch (qYet) {
                                case N_PersonType1.QUEST11:
                                    System.out.print("1. ※SubQuest - ");
                                    System.out.println(qYet);
                                    System.out.println();
                                    break;
                                case N_PersonType1.QUEST12:
                                    System.out.print("2. ※SubQuest - ");
                                    System.out.println(qYet);
                                    break;
                                case N_PersonType1.QUEST13:
                                case N_PersonType1.QUEST14:
                                case N_PersonType1.QUEST15:
                                    System.out.print("M. ※MainQuest - ");
                                    System.out.println(qYet);
                                    break;
                                case N_PersonType2.QUEST21:
                                    System.out.print("3. ※SubQuest - ");
                                    System.out.println(qYet);
                                    break;
                                case N_PersonType2.QUEST22:
                                    System.out.print("4. ※SubQuest - ");
                                    System.out.println(qYet);
                                    break;
                                case N_PersonType2.QUEST23:
                                    System.out.print("5. ※SubQuest - ");
                                    System.out.println(qYet);
                                    break;
                                default:
                            }
                        }
                        System.out.println("입력하세요:");

                        //진행상황 확인
                        String sc2 = scanner.next();
                        switch (sc2) {
                            case "1": //11
                                if (player.playerQuest_yet.contains(N_PersonType1.QUEST11)) {
                                    System.out.println(N_PersonType1.QUEST11);
                                    System.out.println("목표: 숲의 괴물이 랜덤으로 드랍하는 식량창고 열쇠 회수.");
                                    System.out.println("진행상황: 0 / 1");
                                }else {
                                    System.out.println("잘못 입력하셨습니다.");
                                }
                                continue loop2;
                            case "2": //12
                                if (player.playerQuest_yet.contains(N_PersonType1.QUEST12)) {
                                    System.out.println(N_PersonType1.QUEST12);
                                    System.out.println("목표: 숲의 괴물들을 처치하고 의료도구 3개 회수.");
                                    System.out.println("진행상황: " + player.Q_item1_2 + " / 3");
                                }else {
                                    System.out.println("잘못 입력하셨습니다.");
                                }
                                continue loop2;
                            case "3": //21
                                if (player.playerQuest_yet.contains(N_PersonType2.QUEST21)) {
                                    System.out.println(N_PersonType2.QUEST21);
                                    System.out.println("목표: 숲의 괴물이 랜덤으로 드랍하는 다이아몬드 목걸이 1개 회수.");
                                    System.out.println("진행상황: 0 / 1");
                                }else {
                                    System.out.println("잘못 입력하셨습니다.");
                                }
                                continue loop2;
                            case "4": //22
                                if (player.playerQuest_yet.contains(N_PersonType2.QUEST22)) {
                                    System.out.println(N_PersonType2.QUEST22);
                                    System.out.println("목표: 밤 중에 마을에 출몰한 괴물 5마리 처치.");
                                    System.out.println("진행상황: " + player.Q_item2_2 + " / 5");
                                }else {
                                    System.out.println("잘못 입력하셨습니다.");
                                }
                                continue loop2;
                            case "5": //23
                                if (player.playerQuest_yet.contains(N_PersonType2.QUEST23)) {
                                    System.out.println(N_PersonType2.QUEST23);
                                    System.out.println("목표: 숲의 괴물 5마리 처치.");
                                    System.out.println("진행상황: " + player.Q_item2_3 + " / 5");
                                }else {
                                    System.out.println("잘못 입력하셨습니다.");
                                }
                                continue loop2;
                            case "m":
                            case "M":
                                if (player.playerQuest_yet.contains(N_PersonType1.QUEST13)){
                                    System.out.println(N_PersonType1.QUEST13);
                                    System.out.println("목표: 숲의 북쪽 끝 늪지대에 숨은 첫번째 악마를 처치.");
                                    System.out.println("진행상황: 0 / 1");
                                }else if (player.playerQuest_yet.contains(N_PersonType1.QUEST14)){
                                    System.out.println(N_PersonType1.QUEST14);
                                    System.out.println("목표: 숲의 남쪽 끝 동굴에 숨은 두번째 악마를 처치.");
                                    System.out.println("진행상황: 0 / 1");
                                }else if (player.playerQuest_yet.contains(N_PersonType1.QUEST15)){
                                    System.out.println(N_PersonType1.QUEST15);
                                    System.out.println("가장 깊은 숲에 숨은 타락한 마법사를 처치하세요");
                                    System.out.println("진행상황: 0 / 1");
                                }else {
                                    System.out.println("잘못 입력하셨습니다.");
                                }
                                continue loop2;
                            case "z":
                            case "Z":
                                System.out.println("뒤로 갑니다\n");
                                continue loop1;
                            default:
                                System.out.println("잘못 입력하셨습니다.");
                        }
                    }
                case "2":
                    //완료한 퀘스트 출력
                    System.out.println("※완료한 퀘스트");
                    for (String qDone:player.playerQuest_done){
                        switch (qDone) {
                            case N_PersonType1.QUEST13:
                            case N_PersonType1.QUEST14:
                            case N_PersonType1.QUEST15:
                                System.out.print("※MainQuest - ");
                                System.out.println(qDone);
                                break;
                            case N_PersonType1.QUEST11:
                            case N_PersonType1.QUEST12:
                            case N_PersonType2.QUEST21:
                            case N_PersonType2.QUEST22:
                            case N_PersonType2.QUEST23:
                                System.out.print("※SubQuest - ");
                                System.out.println(qDone);
                                break;
                            default:
                        }
                    }
                    continue loop1;
                case "z":
                case "Z":
                    System.out.println("퀘스트 창 종료.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }




    // 전투 메소드 (전투시작, 전투진행, 전투 종료)

    private static synchronized void battleMethod(Player player, Monster monster,GameMap map,ArrayList<Monster> monsterList,MainThread mainThread) {

        battleStart(player,monster);
        battleOn(player,monster,map,monsterList,mainThread);

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

    private static void battleOn (Player player, Monster monster,GameMap map,ArrayList<Monster> monsterList,MainThread mainThread){
        final String SWORD_SOUND = "c:/a_project_sound/slash.wav";


        actionLoop:
        while (true) {
            System.out.println("\n1-일반공격\n2-스킬사용\n3-회복포션 사용\nZ-도주(1/3확률로 성공)\n");
            System.out.print("입력 값:");
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
                        continue actionLoop;
                    }
                case "3": // 포션 사용
                    // 플레이어 저주 시
                    if (player.curse > 0){
                        System.out.println("플레이어가 저주에 당해 회복을 할 수 없습니다\n남은 횟수: "+(player.curse-1));
                        continue actionLoop;
                    }
                    // 플레이어 중독 시
                    if (player.poison > 0){
                        System.out.println("플레이어가 독에 당해 회복을 할 수 없습니다\n남은 횟수: "+(player.poison-1));
                        continue actionLoop;
                    }
                    //중독이 아닐 때
                    else if (player.heal_P(player)){ // 사용 가능
                        break;
                    }else { // 사용 불가
                        continue actionLoop;
                    }
                case "z": // 도주 선택
                case "Z":
                    /////////////////////////////////////////////////////
                    Random random = new Random();
                    int runNum;
                    switch (monster.getClass().toString()){
                        case "Monster.M_BossType1":
                        case "Monster.M_BossType2":
                        case "Monster.M_BossType3":
                            System.out.println("보스 도주 = 1/6");
                            runNum = random.nextInt(6);
                            break;
                        default:
                            System.out.println("일반 도주 = 1/3");
                            runNum = random.nextInt(3);
                    }
                    if (runNum == 0) { // 도주 (1,2나옴)
                        System.out.println("도주 성공~");
                        //도주 성공시 보스,몬스터? 재생성
                        //리스트에서 remove,재생성,add
                        monsterList.remove(monster);
                        //몬스터 클래스에 맞는 생성자 선택
                        switch (monster.getClass().toString()){
                            case "class Monster.M_GeneralType1": //일반1
                                monster = new M_GeneralType1(monster.monsterPlaceY,monster.monsterPlaceX,mainThread);
                                monsterList.add(monster);
                                break;
                            case "class Monster.M_GeneralType2": //일반2
                                monster = new M_GeneralType2(monster.monsterPlaceY,monster.monsterPlaceX,mainThread);
                                monsterList.add(monster);
                                break;
                            case "class Monster.M_BossType1": //보스1
                                monster = new M_BossType1(monster.monsterPlaceY,monster.monsterPlaceX,mainThread);
                                monsterList.add(monster);
                                break;
                            case "class Monster.M_BossType2": //보스2
                                monster = new M_BossType2(monster.monsterPlaceY,monster.monsterPlaceX,mainThread);
                                monsterList.add(monster);
                                break;
                            case "class Monster.M_BossType3": //보스3
                                monster = new M_BossType3(monster.monsterPlaceY,monster.monsterPlaceX,mainThread);
                                monsterList.add(monster);
                                break;
                        }
                        map.printMap();
                        return;
                    } else { // 도주불가 (0나옴)
                        System.out.println("도주 실패...");
                        break;
                    }

                case "1": // 일반 공격
                    player.attack_P(player, monster);
                    SoundThread slash_sound = new SoundThread(SWORD_SOUND);
                    slash_sound.start();
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
                    continue actionLoop;
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
                System.out.println("\n저주 데미지:"+monster.monsterATK/3+"\n남은 횟수: "+(player.curse-1));
                player.curse--;
            }

            // 중독 변수
            if (player.poison > 0){
                player.poison--;
            }

            //플레이어 화상 시
            if (player.burning > 0){
                player.playerHP -= monster.monsterATK/3;
                System.out.println("\n화상 데미지:"+monster.monsterATK/3+"\n남은 횟수: "+(player.burning-1));
                player.burning--;
            }

            // 몬스터 사망 (종료시 플레이어의 변수 증감(아이템, 퀘스트))
            if (monster.monsterHP <= 0) {
                System.out.println("\n\n몬스터 사망! 전투 종료!");
                //스탯 업
                monster.statUp_questLoot();
                // 출력맵에서 몬스터 삭제
                map.mapArray[monster.monsterPlaceY][monster.monsterPlaceX] = GameMap.ROAD;

                // 한밤중의 습격 퀘스트 진행중이라면 진행+1
                if (player.playerQuest_yet.contains(N_PersonType1.QUEST12) && map.mapName.equals(GameMap.VILLAGE)){
                    player.Q_item2_2++;
                }

                //변수 초기화
                player.archerSkill = 0;
                player.assassinSkill = 0;
                player.knightSkill = 0;

                monsterList.remove(monster);
                player.burning = 0;
                player.poison = 0;
                player.curse = 0;
                map.printMap();
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
                switch (monster.m_ATK()){
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

            // 몬스터 사망 (종료시 플레이어의 변수 증감(아이템, 퀘스트))
            if (monster.monsterHP <= 0) {
                System.out.println("\n\n몬스터 사망! 전투 종료!");
                monster.statUp_questLoot();
                map.mapArray[monster.monsterPlaceY][monster.monsterPlaceX] = GameMap.ROAD; // 출력맵에서 몬스터 삭제
            }

            System.out.println("\n\n\n----------------------------------------------------------");

            // 플레이어 사망
            if (player.playerHP <= 0) {
                System.out.println("HP가 0이 되었습니다.");
                break;
            }
        }
    }

    private void boss_battle(){
        /////보스 리스트 검사 - 밤일때만
        if (!day_Night_Thread.isDay){
            for (Monster monster : bossMonsterList){
                if (currentMap.metMonster(player,monster,currentMap)){

                    //밤낮 쓰레드 종료, 남은 시간 리턴 메소드
                    int leftTime = battle_stop_getLeftTime();

                    //전투음악 재생
                    MusicThread musicThread_battle = new MusicThread(BATTLE_MUSIC);
                    musicThread_battle.start();

                    //전투 메소드
                    battleMethod(player,monster,currentMap,bossMonsterList,this);

                    //전투음악 삭제
                    synchronized (musicThread_battle){
                        musicThread_battle.stop();
                        //System.out.println("전투음악 상태? "+musicThread_battle.getState());
                    }

                    //퀘스트 달성 여부 메소드
                    player.isQuestComplete();

                    //엔딩 퀘스트 달성시 출력
                    if (player.ending){

                        //엔딩 음악...
                        MusicThread musicThread_ending = new MusicThread(ENDING_MUSIC);
                        musicThread_ending.start();

                        return;
                    }else {
                        //엔딩 아닐때는 밤낮 실행

                        //밤낮 쓰레드 생성,실행
                        //밤, 중간부터 실행
                        day_Night_Thread = new DayOrNightThread(this,false,leftTime);
                        day_Night_Thread.start();

                        return;
                    }

                }
            }
        }
    }

    private void random_battle(Monster monsterRandom) {
        int ranBattle = random.nextInt(10); //5
        if (ranBattle == 0) {

            //밤낮 쓰레드 종료, 남은 시간 리턴 메소드
            int leftTime = battle_stop_getLeftTime();

            //sleep 일시정지시 남은 시간 변수
            //밤낮 쓰레드 삭제

            //전투 음악 실행
            MusicThread musicThread_battle = new MusicThread(BATTLE_MUSIC);
            musicThread_battle.start();

            ///////////////////////////랜덤 전투 실행
            // 몬스터 타입2와 전투발생
            battleMethod(player, monsterRandom, currentMap, monsterList, this);

            //랜덤 전투 종료

            //전투음악 삭제
            synchronized (musicThread_battle) {
                musicThread_battle.stop();
                //System.out.println("전투음악 상태? " + musicThread_battle.getState());
            }

            //퀘스트 달성 여부 메소드
            player.isQuestComplete();

            //밤낮 쓰레드 생성,실행
            synchronized (day_Night_Thread) {
                //밤, 중간부터 실행
                day_Night_Thread = new DayOrNightThread(this, false, leftTime);
                day_Night_Thread.start();
            }
        }
    }

    //전투 발생시 밤낮 정지, 남은 시간 리턴
    private int battle_stop_getLeftTime(){
        synchronized (this){
            synchronized (day_Night_Thread){
                //남은 시간 측정
                int leftTime = day_Night_Thread.stopMusic_getLeftTime();
                //밤낮 정지
                day_Night_Thread.stop();
                return leftTime;
            }
        }
    }

    //포탈 이동시 밤낮쓰레드 삭제, 재생성
    private void portal_dayNight_regenerate(Player player, boolean isDay){
        //엔딩시 밤낮 생성X
        if (!player.ending){
            //밤낮 쓰레드 삭제
            int leftTime = battle_stop_getLeftTime();

            //포탈 음악 - 걷는 소리? sleep?
            SoundThread portal_sound = new SoundThread(PORTAL_SOUND);
            try { Thread.sleep(200); } catch (InterruptedException ignored) {}

            //밤낮 쓰레드 재생성
            day_Night_Thread = new DayOrNightThread(this,isDay,leftTime);
            day_Night_Thread.start();
        }
    }


    void monster_regenerate(MainThread mainThread){
        //선언 전에 플레이어 대각선으로 치우기
        boolean player_move_aside = false;
        if (player.yPlace == 1 && player.xPlace == 6){
            player_move_aside = true;
        }
        if (player.yPlace == 1 && player.xPlace == 13){
            player_move_aside = true;
        }
        if (player.yPlace == 5 && player.xPlace == 6){
            player_move_aside = true;
        }
        if (player.yPlace == 5 && player.xPlace == 13){
            player_move_aside = true;
        }
        if (player.yPlace == 9 && player.xPlace == 6){
            player_move_aside = true;
        }
        if (player.yPlace == 9 && player.xPlace == 13){
            player_move_aside = true;
        }
        if (player.yPlace == 13 && player.xPlace == 6){
            player_move_aside = true;
        }
        if (player.yPlace == 13 && player.xPlace == 13){
            player_move_aside = true;
        }

        //랜덤 하게 대각선으로 플레이어 이동
        if (player_move_aside) {

            //플레이어 전 위치 Road
            currentMap.mapArray[player.yPlace][player.xPlace] = GameMap.ROAD;

            int ranNum1 = random.nextInt(2);
            if (ranNum1 == 0){
                player.yPlace--;
            }else {
                player.yPlace++;
            }
            ranNum1 = random.nextInt(2);
            if (ranNum1 == 0){
                player.xPlace++;
            }else {
                player.xPlace--;
            }
            //플레이어 좌표에 PLAYER 위치
            currentMap.mapArray[player.yPlace][player.xPlace] = GameMap.PLAYER;
        }


        //일반 몬스터 선언
        M_GeneralType1 monster1 = new M_GeneralType1(1, 6, mainThread);
        M_GeneralType1 monster2 = new M_GeneralType1(1,13, mainThread);
        M_GeneralType1 monster3 = new M_GeneralType1(5,6, mainThread);
        M_GeneralType1 monster4 = new M_GeneralType1(5,13, mainThread);

        M_GeneralType2 monster5 = new M_GeneralType2(9,6, mainThread);
        M_GeneralType2 monster6 = new M_GeneralType2(9,13, mainThread);
        M_GeneralType2 monster7 = new M_GeneralType2(13,6, mainThread);
        M_GeneralType2 monster8 = new M_GeneralType2(13,13, mainThread);

        monsterList.add(monster1);
        monsterList.add(monster2);
        monsterList.add(monster3);
        monsterList.add(monster4);

        monsterList.add(monster5);
        monsterList.add(monster6);
        monsterList.add(monster7);
        monsterList.add(monster8);
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




    //맵 구현 메소드("Road"로 모두 채우고 그 위에 "EndWay"로 지형지물 만들기)
    private static void makeVillageMap(String[][] mapArray){
        // Road 로 채움
        for (String[] strings : mapArray) {
            Arrays.fill(strings, GameMap.ROAD);
        }
        //EndWay
        mapArray[0][0] = GameMap.END_WAY;
        mapArray[0][1] = GameMap.END_WAY;
        mapArray[0][2] = GameMap.END_WAY;
        mapArray[0][3] = GameMap.END_WAY;
        mapArray[0][4] = GameMap.END_WAY;
        mapArray[0][5] = GameMap.END_WAY;
        mapArray[1][0] = GameMap.END_WAY;
        mapArray[1][1] = GameMap.END_WAY;
        mapArray[1][2] = GameMap.END_WAY;
        mapArray[2][0] = GameMap.END_WAY;

    }

    private static void makeForestMap(String[][] mapArray){

        // Road 로 채움
        for (String[] strings : mapArray) {
            Arrays.fill(strings, GameMap.ROAD);
        }
        //EndWay
        //1
        mapArray[0][21] = GameMap.END_WAY;
        mapArray[1][21] = GameMap.END_WAY;
        mapArray[2][21] = GameMap.END_WAY;
        mapArray[3][21] = GameMap.END_WAY;
        mapArray[4][21] = GameMap.END_WAY;
        mapArray[4][22] = GameMap.END_WAY;

        //2
        mapArray[4][23] = GameMap.END_WAY;
        mapArray[4][24] = GameMap.END_WAY;
        mapArray[4][25] = GameMap.END_WAY;
        mapArray[4][26] = GameMap.END_WAY;
        mapArray[4][27] = GameMap.END_WAY;
        mapArray[4][28] = GameMap.END_WAY;
        mapArray[4][29] = GameMap.END_WAY;

        //3
        mapArray[5][23] = GameMap.END_WAY;
        mapArray[6][23] = GameMap.END_WAY;
        mapArray[7][23] = GameMap.END_WAY;
        mapArray[8][23] = GameMap.END_WAY;
        mapArray[9][23] = GameMap.END_WAY;

        //4
        mapArray[10][23] = GameMap.END_WAY;
        mapArray[10][24] = GameMap.END_WAY;
        mapArray[10][25] = GameMap.END_WAY;
        mapArray[10][26] = GameMap.END_WAY;
        mapArray[10][27] = GameMap.END_WAY;
        mapArray[10][28] = GameMap.END_WAY;
        mapArray[10][29] = GameMap.END_WAY;

        //5
        mapArray[10][22] = GameMap.END_WAY;
        mapArray[10][21] = GameMap.END_WAY;
        mapArray[11][21] = GameMap.END_WAY;
        mapArray[12][21] = GameMap.END_WAY;
        mapArray[13][21] = GameMap.END_WAY;
        mapArray[14][21] = GameMap.END_WAY;


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
