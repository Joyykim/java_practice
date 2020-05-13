package NPC;

import Player.*;

import java.util.ArrayList;
import java.util.Scanner;

public class N_Person extends NPC{
    // 리스트로 퀘스트를 보관 (신규, 진행중, 완료)
    // 플레이어가 퀘스트 선택시 player.playerQuest 에 추가하고
    Scanner scanner = new Scanner(System.in);

    ArrayList<String> npcQuest_Yet = new ArrayList<>();
    ArrayList<String> npcQuest_Done = new ArrayList<>();

    N_Person(int npcPlaceY, int npcPlaceX) {
        super(npcPlaceY, npcPlaceX);
    }

    @Override
    public void interact_N(){

        // 대화시 자동으로 퀘스트 달성
        // 미수락 퀘스트 출력
        // 퀘스트 선택, 수락

    }

}
