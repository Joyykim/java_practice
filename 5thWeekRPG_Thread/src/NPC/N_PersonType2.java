package NPC;

import Player.GameMap;
import Thread.*;

public class N_PersonType2 extends N_Person {

    public N_PersonType2(int npcPlaceY, int npcPlaceX, MainThread mainThread) {
        super(npcPlaceY, npcPlaceX);
        this.mainThread = mainThread;
        this.mainThread.villageMap.mapArray[npcPlaceY][npcPlaceX] = GameMap.NPC2;
        super.npcQuest_Yet.add(QUEST21);
        super.npcQuest_Yet.add(QUEST22);
        super.npcQuest_Yet.add(QUEST23);
    }

    private MainThread mainThread;

    public static final String QUEST21 = "목걸이 회수";
    public static final String QUEST22 = "한밤중의 습격";
    public static final String QUEST23 = "괴물 퇴치";


    @Override
    public void interact_N(){

        //현상금 게시판
        System.out.println();

        // 대화시 자동으로 퀘스트 달성 메소드
        if (!mainThread.player.playerQuest_done.isEmpty()){
            for (int i = 0; i < mainThread.player.playerQuest_done.size(); i++){
                String quest = mainThread.player.playerQuest_done.get(i);
                switch (quest){
                    case QUEST21:
                        System.out.println("\n\n※SubQuest -"+QUEST21+"- 보상을 획득합니다");
                        //리스트 정리
                        mainThread.player.playerQuest_done.remove(QUEST21);
                        super.npcQuest_Yet.remove(QUEST21);
                        super.npcQuest_Done.add(QUEST21);

                        //보상 획득
                        mainThread.player.playerMaxHP += 50;
                        mainThread.player.playerHP += 50;
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

                    case QUEST22:
                        System.out.println("\n\n※SubQuest -"+QUEST22+"- 보상을 획득합니다");
                        //리스트 정리
                        mainThread.player.playerQuest_done.remove(QUEST22);
                        super.npcQuest_Yet.remove(QUEST22);
                        super.npcQuest_Done.add(QUEST22);

                        //보상 획득
                        mainThread.player.playerATK += 50;
                        mainThread.player.playerMaxHP += 100;
                        mainThread.player.playerHP += 100;
                        mainThread.player.playerMoney += 300;

                        System.out.println("\n공격력 50증가");
                        System.out.println("최대HP 100증가");
                        System.out.println("잔고 300증가\n");

                        System.out.println("플레이어 공격력:"+mainThread.player.playerATK);
                        System.out.println("플레이어 최대HP:"+mainThread.player.playerMaxHP);
                        System.out.println("플레이어 잔고:"+mainThread.player.playerMoney+"\n");
                        break;

                    case QUEST23:
                        System.out.println("\n\n※SubQuest -"+QUEST23+"- 보상을 획득합니다");
                        //리스트 정리
                        mainThread.player.playerQuest_done.remove(QUEST23);
                        super.npcQuest_Yet.remove(QUEST23);
                        super.npcQuest_Done.add(QUEST23);

                        //보상 획득
                        mainThread.player.playerATK += 50;
                        mainThread.player.playerMaxHP += 100;
                        mainThread.player.playerHP += 100;
                        mainThread.player.playerMoney += 300;

                        System.out.println("\n공격력 50증가");
                        System.out.println("최대HP 100증가");
                        System.out.println("잔고 300증가\n");

                        System.out.println("플레이어 공격력:"+mainThread.player.playerATK);
                        System.out.println("플레이어 최대HP:"+mainThread.player.playerMaxHP);
                        System.out.println("플레이어 잔고:"+mainThread.player.playerMoney+"\n");
                        break;

                    default:
                }
            }
        }

        System.out.println("\n\n퀘스트를 수락한 상태에서 몬스터를 사냥하면 확률적으로 퀘스트 아이템을 획득할 수 있습니다");
        System.out.println("몬스터 당 하나의 퀘스트 아이템을 드랍합니다");
        System.out.println("퀘스트를 완료하고 NPC와 대화를 하면 자동으로 완료가 되고 보상을 획득합니다");
        System.out.println("보상은 공격력, 최대HP 증가 그리고 보상금입니다");
        loop:
        while (true){
            // 미수락 퀘스트 출력, 안내
            System.out.println("\n수락할 퀘스트를 선택하세요\n");
            for (String quest : npcQuest_Yet) {
                switch (quest) {
                    case QUEST21:
                        System.out.print("1. ※SubQuest - ");
                        System.out.println(quest);
                        break;
                    case QUEST22:
                        System.out.print("2. ※SubQuest - ");
                        System.out.println(quest);
                        break;
                    case QUEST23:
                        System.out.print("3. ※SubQuest - ");
                        System.out.println(quest);
                        break;
                    default:
                }
            }
            System.out.println("q. 완료한 퀘스트 목록 보기");
            System.out.println("z. 대화 끝내기");

            // 숫자 입력으로 퀘스트 선택
            String questSelect = scanner.next();
            switch (questSelect){
                case "1":
                    if (npcQuest_Yet.contains(QUEST21)){ //리스트에 퀘스트 있음
                        //퀘스트 설명
                        System.out.println("현상금 공지");
                        System.out.println("임무 - 다이아몬드 목걸이를 소지한 괴물을 처치하고 목걸이 회수.");
                        System.out.println("주의 - 목걸이를 안전하게 가져와야만 보상금 지급.");
                        System.out.println();
                        System.out.println("목표:");
                        System.out.println("숲의 괴물이 랜덤으로 드랍하는 다이아몬드 목걸이 1개 회수.");
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
                                    System.out.println(QUEST21+"를 수락했습니다");
                                    mainThread.player.playerQuest_yet.add(QUEST21); // 플레이어 진행중 리스트에 추가
                                    npcQuest_Yet.remove(QUEST21); //NPC 미수락 리스트에서 삭제
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
                    if (npcQuest_Yet.contains(QUEST22)){ //리스트에 퀘스트 있음
                        //퀘스트 설명
                        System.out.println("현상금 공지");
                        System.out.println("임무 - 밤 중에 마을에 출몰하는 괴물들을 처치.");
                        System.out.println("주의 - .");
                        System.out.println();
                        System.out.println("목표:");
                        System.out.println("밤 중에 마을에 출몰한 괴물 5마리 처치.");
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
                                    System.out.println(QUEST22+"를 수락했습니다");
                                    mainThread.player.playerQuest_yet.add(QUEST22); // 플레이어 진행중 리스트에 추가
                                    npcQuest_Yet.remove(QUEST22); //NPC 미수락 리스트에서 삭제
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
                case "3":
                    if (npcQuest_Yet.contains(QUEST23)){ //리스트에 퀘스트 있음
                        //퀘스트 설명
                        System.out.println("이젠 괴물들이 마을 입구까지 출몰한다더군.");
                        System.out.println("괴물을 여럿 처치하면 그 녀석들도 겁에 질려 도망가겠지.");
                        System.out.println("5마리 정도면 적당할 듯 하군.");
                        System.out.println();
                        System.out.println("목표:");
                        System.out.println("숲의 괴물 5마리 처치.");
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
                                    System.out.println(QUEST23+"를 수락했습니다");
                                    mainThread.player.playerQuest_yet.add(QUEST23); // 플레이어 진행중 리스트에 추가
                                    npcQuest_Yet.remove(QUEST23); //NPC 미수락 리스트에서 삭제
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
                    System.out.println("잘못 선택하셨습니다. ");
            }

        }


    }
}
