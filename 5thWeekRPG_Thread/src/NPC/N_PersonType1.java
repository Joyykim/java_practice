package NPC;

import Player.GameMap;
import Player.Player;
import Thread.*;

public class N_PersonType1 extends N_Person {

    public N_PersonType1(int npcPlaceY, int npcPlaceX, MainThread mainThread) {
        super(npcPlaceY, npcPlaceX);
        this.mainThread = mainThread;
        this.mainThread.villageMap.mapArray[npcPlaceY][npcPlaceX] = GameMap.NPC1;
        npcQuest_Yet.add(QUEST11);
        npcQuest_Yet.add(QUEST12);
        npcQuest_Yet.add(QUEST13);
    }

    private MainThread mainThread;

    public static final String QUEST11 = "식량창고를 열어라!";
    public static final String QUEST12 = "의료도구 회수";
    public static final String QUEST13 = "첫 번째 악마";
    public static final String QUEST14 = "두 번째 악마";
    public static final String QUEST15 = "원흉";


    @Override
    public void interact_N(){

        // 대화시 완료한 모든 퀘스트 보상 지급
        if (!mainThread.player.playerQuest_done.isEmpty()){
            for (int i = 0; i < mainThread.player.playerQuest_done.size();i++){
                String quest = mainThread.player.playerQuest_done.get(i);
                switch (quest){

                    case QUEST11:
                        System.out.println("\n\n※SubQuest -"+QUEST11+"- 보상을 획득합니다");
                        //리스트 정리
                        mainThread.player.playerQuest_done.remove(QUEST11);
                        npcQuest_Yet.remove(QUEST11);
                        npcQuest_Done.add(QUEST11);

                        //보상 획득
                        mainThread.player.playerMaxHP += 50;
                        mainThread.player.playerHP = mainThread.player.playerMaxHP;
                        mainThread.player.playerMoney += 200;
                        mainThread.player.playerPotion2 += 3;

                        System.out.println("보상 획득:");
                        System.out.println("골드 +200");
                        System.out.println("플레이어 잔고:"+mainThread.player.playerMoney+"\n");
                        System.out.println("최대 체력 +50");
                        System.out.println("플레이어 최대체력:"+mainThread.player.playerMaxHP);
                        System.out.println("200회복포션 +3");
                        System.out.println("플레이어 200회복포션:"+mainThread.player.playerPotion2);
                        break;

                    case QUEST12:
                        System.out.println("\n\n※SubQuest -"+QUEST12+"- 보상을 획득합니다");
                        //리스트 정리
                        mainThread.player.playerQuest_done.remove(QUEST12);
                        npcQuest_Yet.remove(QUEST12);
                        npcQuest_Done.add(QUEST12);

                        //보상 획득
                        mainThread.player.playerATK += 50;
                        mainThread.player.playerMaxHP += 100;
                        mainThread.player.playerHP = mainThread.player.playerMaxHP;
                        mainThread.player.playerMoney += 300;

                        System.out.println("\n공격력 50증가");
                        System.out.println("최대HP 100증가");
                        System.out.println("잔고 300증가\n");

                        System.out.println("플레이어 공격력:"+mainThread.player.playerATK);
                        System.out.println("플레이어 최대HP:"+mainThread.player.playerMaxHP);
                        System.out.println("플레이어 잔고:"+mainThread.player.playerMoney+"\n");
                        break;

                    case QUEST13:
                        System.out.println("\n\n※MainQuest -"+QUEST13+"- 보상을 획득합니다");
                        //리스트 정리
                        mainThread.player.playerQuest_done.remove(QUEST13);
                        npcQuest_Yet.remove(QUEST13);
                        npcQuest_Done.add(QUEST13);

                        //메인퀘 추가
                        npcQuest_Yet.add(QUEST14);

                        //보상 획득
                        mainThread.player.playerATK += 50;
                        mainThread.player.playerMaxHP += 100;
                        mainThread.player.playerHP = mainThread.player.playerMaxHP;
                        mainThread.player.playerMoney += 300;

                        System.out.println("\n공격력 50증가");
                        System.out.println("최대HP 100증가");
                        System.out.println("잔고 300증가\n");

                        System.out.println("플레이어 공격력:"+mainThread.player.playerATK);
                        System.out.println("플레이어 최대HP:"+mainThread.player.playerMaxHP);
                        System.out.println("플레이어 잔고:"+mainThread.player.playerMoney+"\n");

                        System.out.println("\n\n※MainQuest -"+QUEST14+"- 진행 가능");
                        break;

                    case QUEST14:
                        System.out.println("\n\n※MainQuest -"+QUEST14+"- 보상을 획득합니다");
                        //리스트 정리
                        mainThread.player.playerQuest_done.remove(QUEST14);
                        npcQuest_Yet.remove(QUEST14);
                        npcQuest_Done.add(QUEST14);

                        //메인퀘 추가
                        npcQuest_Yet.add(QUEST15);

                        //보상 획득
                        mainThread.player.playerATK += 50;
                        mainThread.player.playerMaxHP += 100;
                        mainThread.player.playerHP = mainThread.player.playerMaxHP;
                        mainThread.player.playerMoney += 300;

                        System.out.println("\n공격력 50증가");
                        System.out.println("최대HP 100증가");
                        System.out.println("잔고 300증가\n");

                        System.out.println("플레이어 공격력:"+mainThread.player.playerATK);
                        System.out.println("플레이어 최대HP:"+mainThread.player.playerMaxHP);
                        System.out.println("플레이어 잔고:"+mainThread.player.playerMoney+"\n");

                        System.out.println("\n\n※MainQuest -"+QUEST15+"- 진행 가능");
                        break;

                    case QUEST15:
                        //엔딩!!!
                        ending(mainThread.player);
                        break;

                    default:
                }
            }
        }

        System.out.println("\n\n퀘스트를 수락한 상태에서 몬스터를 사냥하면 확률적으로 퀘스트 아이템을 획득할 수 있습니다");
        System.out.println("몬스터 당 하나의 퀘스트 아이템을 드랍합니다");
        System.out.println("퀘스트를 완료하고 NPC와 대화를 하면 자동으로 완료가 되고 보상을 획득합니다");
        System.out.println("보상은 공격력, 최대HP 증가, 보상금 등이 있습니다.");

        loop:
        while (true){
            // 미수락 퀘스트 출력, 안내
            System.out.println("\n수락할 퀘스트를 선택하세요\n");
            for (String quest : npcQuest_Yet) {
                switch (quest) {
                    case QUEST11:
                        System.out.print("1. ※SubQuest - ");
                        System.out.println(quest);
                        break;
                    case QUEST12:
                        System.out.print("2. ※SubQuest - ");
                        System.out.println(quest);
                        break;
                    case QUEST13:
                        System.out.print("M. ※MainQuest - ");
                        System.out.println(quest);
                        break;
                    case QUEST14:
                        //QUEST13완료
                        if (npcQuest_Done.contains(QUEST13)) {
                            System.out.print("M. ※MainQuest - ");
                            System.out.println(quest);
                            break;
                        }
                    case QUEST15:
                        //QUEST14완료
                        if (npcQuest_Done.contains(QUEST14)) {
                            System.out.print("M. ※MainQuest - ");
                            System.out.println(quest);
                            break;
                        }
                    default:
                }
            }
            System.out.println("q. 완료한 퀘스트 목록 보기");
            System.out.println("z. 대화 끝내기");

            // 숫자 입력으로 퀘스트 선택
            String questSelect = scanner.next();
            switch (questSelect){
                case "1":
                    if (npcQuest_Yet.contains(QUEST11)){ //리스트에 퀘스트 있음
                        //퀘스트 설명
                        System.out.println("숲에 있는 괴물 중 하나가 마을 식량창고의 열쇠를 가지고 있다네.");
                        System.out.println("그 열쇠가 없으면 사람들에게 식량을 배급하지 못해. 많은 사람들이 굶을 걸세.");
                        System.out.println("그 괴물을 처치하고 열쇠를 가져와주게.");
                        System.out.println();
                        System.out.println("목표:");
                        System.out.println("숲의 괴물이 랜덤으로 드랍하는 식량창고 열쇠 회수.");
                        System.out.println();
                        System.out.println("보상:");
                        System.out.println("골드 +300");
                        System.out.println("최대 체력 +50");
                        System.out.println("200회복포션 +3");
                        System.out.println();
                        System.out.println("1. 수락\n2. 취소");
                        //퀘스트 수락 결정
                        while (true){
                            String selectSure = scanner.next();
                            switch (selectSure){
                                case "1": //수락
                                    System.out.println(QUEST11+"를 수락했습니다");
                                    mainThread.player.playerQuest_yet.add(QUEST11); // 플레이어 진행중 리스트에 추가
                                    npcQuest_Yet.remove(QUEST11); //NPC 미수락 리스트에서 삭제
                                    break loop;
                                case "2": //뒤로가기
                                    System.out.println("수락을 취소하셨습니다. 다시 퀘스트를 선택해주세요");
                                    continue loop;
                                default:
                                    System.out.println("다시 골라주세요");
                            }
                        }
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다. ");
                        continue loop;
                    }
                case "2":
                    if (npcQuest_Yet.contains(QUEST12)){ //리스트에 퀘스트 있음
                        //퀘스트 설명
                        System.out.println("우리 마을로 오던 의료진이 괴물에게 습격을 당했네.");
                        System.out.println("그들은 목숨을 잃었지만 남은 사람들에겐 의료도구가 필요하네.");
                        System.out.println("괴물들을 처치해서 의료도구를 가져와주게.");
                        System.out.println();
                        System.out.println("목표:");
                        System.out.println("숲의 괴물들을 처치하고 의료도구 3개 회수.");
                        System.out.println();
                        System.out.println("보상:");
                        System.out.println("골드 +200");
                        System.out.println("최대 체력 +50");
                        System.out.println("200회복포션 +3");
                        System.out.println();
                        System.out.println("1. 수락\n2. 취소");

                        //퀘스트 수락 결정
                        while (true){
                            String selectSure = scanner.next();
                            switch (selectSure){
                                case "1": //수락
                                    System.out.println(QUEST12+"를 수락했습니다");
                                    mainThread.player.playerQuest_yet.add(QUEST12); // 플레이어 진행중 리스트에 추가
                                    npcQuest_Yet.remove(QUEST12); //NPC 미수락 리스트에서 삭제
                                    break loop;
                                case "2": //뒤로가기
                                    System.out.println("수락을 취소하셨습니다. 다시 퀘스트를 선택해주세요");
                                    continue loop;
                                default:
                                    System.out.println("다시 골라주세요");
                            }
                        }
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다. ");
                        continue loop;
                    }
                case "m":
                case "M":
                    if (npcQuest_Yet.contains(QUEST13) && !mainThread.player.playerQuest_yet.contains(QUEST13) && !mainThread.player.playerQuest_done.contains(QUEST13)){
                        //npc 리스트에 존재, 플레이어 미수락, 미완료

                        //퀘스트 설명
                        System.out.println("첫번째 악마 처치");
                        System.out.println("화염을 내뿜는 악마.");
                        System.out.println("모든 일의 원흉인 타락한 마법사가 소환한 첫번째 악마를 처치하게.");
                        System.out.println("그 놈이 내뿜는 불은 쉽게 꺼지지 않아. 조심하게, 힘든 싸움이 될테니.");
                        System.out.println();
                        System.out.println("목표:");
                        System.out.println("숲의 북쪽 끝 늪지대에 숨은 첫번째 악마를 처치하세요");
                        System.out.println();
                        System.out.println("보상:");
                        System.out.println("골드 +3000");
                        System.out.println("최대 체력 +500");
                        System.out.println("공격력 +100");
                        System.out.println("200회복포션 +5");
                        System.out.println("스킬 포인트 +10");
                        System.out.println();
                        System.out.println("1. 수락\n2. 취소");

                        //퀘스트 수락 결정
                        while (true){
                            String selectSure = scanner.next();
                            switch (selectSure){
                                case "1": //수락
                                    System.out.println(QUEST13+"를 수락했습니다");
                                    mainThread.player.playerQuest_yet.add(QUEST13); // 플레이어 진행중 리스트에 추가
                                    npcQuest_Yet.remove(QUEST13); //NPC 미수락 리스트에서 삭제
                                    break loop;
                                case "2": //뒤로가기
                                    System.out.println("수락을 취소하셨습니다. 다시 퀘스트를 선택해주세요");
                                    continue loop;
                                default:
                                    System.out.println("다시 골라주세요");
                            }
                        }
                    }else if (npcQuest_Yet.contains(QUEST14) && !mainThread.player.playerQuest_yet.contains(QUEST14) && !mainThread.player.playerQuest_done.contains(QUEST14)){
                        //npc 리스트에 존재, 플레이어 미수락, 미완료

                        //퀘스트 설명
                        System.out.println("두번째 악마 처치");
                        System.out.println("화염을 내뿜는 악마.");
                        System.out.println("모든 일의 원흉인 타락한 마법사가 소환한 두번째 악마를 처치하게.");
                        System.out.println("그 놈이 내뿜는 불은 쉽게 꺼지지 않아. 조심하게, 힘든 싸움이 될테니.");
                        System.out.println();
                        System.out.println("목표:");
                        System.out.println("숲의 남쪽 끝 동굴에 숨은 두번째 악마를 처치하세요");
                        System.out.println();
                        System.out.println("보상:");
                        System.out.println("골드 +3000");
                        System.out.println("최대 체력 +500");
                        System.out.println("공격력 +100");
                        System.out.println("200회복포션 +5");
                        System.out.println("스킬 포인트 +10");
                        System.out.println();
                        System.out.println("1. 수락\n2. 취소");

                        //퀘스트 수락 결정
                        while (true){
                            String selectSure = scanner.next();
                            switch (selectSure){
                                case "1": //수락
                                    System.out.println(QUEST14+"를 수락했습니다");
                                    mainThread.player.playerQuest_yet.add(QUEST14); // 플레이어 진행중 리스트에 추가
                                    npcQuest_Yet.remove(QUEST14); //NPC 미수락 리스트에서 삭제
                                    break loop;
                                case "2": //뒤로가기
                                    System.out.println("수락을 취소하셨습니다. 다시 퀘스트를 선택해주세요");
                                    continue loop;
                                default:
                                    System.out.println("다시 골라주세요");
                            }
                        }
                    }else if (npcQuest_Yet.contains(QUEST15) && !mainThread.player.playerQuest_yet.contains(QUEST15) && !mainThread.player.playerQuest_done.contains(QUEST15)){
                        //npc 리스트에 존재, 플레이어 미수락, 미완료

                        //퀘스트 설명
                        System.out.println("타락한 마법사 처치");
                        System.out.println("이 모든 비극...");
                        System.out.println("그 원흉인 타락한 마법사를 처치하게.");
                        System.out.println("그 자는 금지된 흑마법을 구사하네. 다양한 기술을 쓰니 부디 조심하게.");
                        System.out.println("이제 이 악몽을 끝낼 때가 왔군...");
                        System.out.println();
                        System.out.println("목표:");
                        System.out.println("가장 깊은 숲에 숨은 타락한 마법사를 처치하세요");
                        System.out.println();
                        System.out.println("타락한 마법사를 처치하고 퀘스트를 완료하면 게임 승리로 끝납니다");
                        System.out.println();
                        System.out.println("1. 수락\n2. 취소");
                        //퀘스트 수락 결정
                        while (true){
                            String selectSure = scanner.next();
                            switch (selectSure){
                                case "1": //수락
                                    System.out.println(QUEST15+"를 수락했습니다");
                                    mainThread.player.playerQuest_yet.add(QUEST15); // 플레이어 진행중 리스트에 추가
                                    npcQuest_Yet.remove(QUEST15); //NPC 미수락 리스트에서 삭제
                                    break loop;
                                case "2": //뒤로가기
                                    System.out.println("수락을 취소하셨습니다. 다시 퀘스트를 선택해주세요");
                                    continue loop;
                                default:
                                    System.out.println("다시 골라주세요");
                            }
                        }
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다.");
                        continue loop;
                    }
                case "q":
                case "Q":
                    // NPC 완료한 퀘스트 목록 출력
                    for (String questDone:npcQuest_Done){
                        System.out.println(questDone);
                    }
                    continue loop;
                case "z":
                case "Z":
                    System.out.println("\n\n\n\n\n\n\n대화를 마칩니다");
                    break loop;
                default:
                    System.out.println("잘못 선택하셨습니다.");
            }

        }


    }

    //엔딩 메소드
    private static void ending(Player player) {
        int sleepNum = 3;
        System.out.print("\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n타락한 마법사 쓰러뜨리고 마을엔 평화가 찾아왔습니다\n.\n.\n.\n.\n.\n.");
        //3초 지연 쓰레드
        for(int i=0; i < sleepNum; i++){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\n.\n.\n.\n.\n.\n.\n감사합니다 "+player.playerID+"님\n.\n.\n.\n.\n.\n.");
        //3초 지연 쓰레드
        for(int i=0; i < sleepNum; i++){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\n.\n.\n.\n.\n.\n.\n기획 & 개발 - 김주원\n.\n.\n.\n.\n.\n.");
        //3초 지연 쓰레드
        for(int i=0; i < sleepNum; i++){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\n.\n.\n.\n.\n.\n.\nThank you for playing\n.\n.\n.\n.\n.\n.");
        for(int i=0; i < sleepNum; i++){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }

}
