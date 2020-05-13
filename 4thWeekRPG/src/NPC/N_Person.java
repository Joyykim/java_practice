package NPC;

import Player.*;

import java.util.ArrayList;
import java.util.Scanner;

public class N_Person extends NPC{
    // 리스트로 퀘스트를 보관 (신규, 진행중, 완료)
    // 플레이어가 퀘스트 선택시 player.playerQuest 에 추가하고
    Scanner scanner = new Scanner(System.in);

    public ArrayList<String> npcQuest_Yet = new ArrayList<>();
    public ArrayList<String> npcQuest_Done = new ArrayList<>();

    public N_Person(int npcPlaceY, int npcPlaceX, GameMap map) {
        super(npcPlaceY, npcPlaceX, map);
        map.mapArray[npcPlaceY][npcPlaceX] = "NPC_Person";
    }

    @Override
    public void interact_N(Player player, NPC npc){

        // 대화시 자동으로 퀘스트 달성 메소드


        loop:
        while (true){
            // 미수락 퀘스트 출력, 안내
            System.out.println("수락할 퀘스트를 선택하세요");
            for (String quest: npcQuest_Yet){
                System.out.println(quest);
            }
            System.out.println("q를 입력하면 이미 완료한 퀘스트 목록을 볼수 있습니다");
            // 숫자 입력으로 퀘스트 수락
            String questSelect = scanner.next();
            switch (questSelect){
                case "1":
                    if (!npcQuest_Yet.get(0).isEmpty()){ //리스트에 퀘스트 있음
                        System.out.println(npcQuest_Yet.get(0)+"퀘스트를 수락했습니다");
                        player.playerQuest_yet.add(npcQuest_Yet.get(0)); // 플레이어 진행중 리스트에 추가
                        npcQuest_Yet.remove(0); //NPC 미수락 리스트에서 삭제
                        break loop;
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다. ");
                        continue loop;
                    }
                case "2":
                    if (!npcQuest_Yet.get(1).isEmpty()){ //리스트에 퀘스트 있음
                        System.out.println(npcQuest_Yet.get(1)+"퀘스트를 수락했습니다");
                        player.playerQuest_yet.add(npcQuest_Yet.get(1)); // 플레이어 진행중 리스트에 추가
                        npcQuest_Yet.remove(1); //NPC 미수락 리스트에서 삭제
                        break loop;
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다. ");
                        continue loop;
                    }
                case "3":
                    if (!npcQuest_Yet.get(2).isEmpty()){ //리스트에 퀘스트 있음
                        System.out.println(npcQuest_Yet.get(2)+"퀘스트를 수락했습니다");
                        player.playerQuest_yet.add(npcQuest_Yet.get(2)); // 플레이어 진행중 리스트에 추가
                        npcQuest_Yet.remove(2); //NPC 미수락 리스트에서 삭제
                        break loop;
                    }else { //리스트에 퀘스트 없음
                        System.out.println("잘못 선택하셨습니다. ");
                        continue loop;
                    }
                case "q":
                    // NCP 완료한 퀘스트 목록 출력
                    for (String questDone:npcQuest_Done){
                        System.out.println(questDone);
                    }
                    break loop;
            }

        }

        
    }

}
