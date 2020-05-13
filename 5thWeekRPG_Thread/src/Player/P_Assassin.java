package Player;

import Monster.Monster;
import Thread.*;

public class P_Assassin extends Player {
    public P_Assassin(int yPlace, int xPlace, GameMap map) {
        super(yPlace, xPlace,map);
    }

    @Override
    public void setPlayerCritical_Assassin(int playerCritical_Assassin) {
        super.setPlayerCritical_Assassin(playerCritical_Assassin);
    }

    @Override
    public int skill_P(Player player, Monster monster){
        String KNIFE_SOUND = "c:/a_project_sound/knife.wav";
        SoundThread knife_sound = new SoundThread(KNIFE_SOUND);
        knife_sound.start();
        player.playerSkillPoint--;
        monster.playerPoison += 4;
        System.out.println("암살자 스킬 발동!!");
        System.out.println("칼날에 독을 발라 4턴 동안 데미지를 입히면서 몬스터가 회복하지 못하게 만듭니다");
        return 3;
    }
}
