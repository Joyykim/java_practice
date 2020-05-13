package Player;

import Monster.Monster;

public class P_Archer extends Player {

    public P_Archer(int yPlace, int xPlace) {
        super(yPlace, xPlace);
    }

    @Override
    public void setPlayerDodge_Archer(int playerDodge_Archer) {
        super.setPlayerDodge_Archer(playerDodge_Archer);
    }

    @Override
    public int skill_P(Player player, Monster monster){
        player.playerSkillPoint--;
        player.archerSkill = 1;
        System.out.println("궁수 스킬 발동!!");
        System.out.println("거리를 벌려 공격을 회피하고 화살을 두개 발사합니다");
        monster.monsterHP -= player.playerATK;
        System.out.println("\n데미지-"+player.playerATK+"\n몬스터체력:"+monster.monsterHP);
        monster.monsterHP -= player.playerATK;
        System.out.println("데미지-"+player.playerATK+"\n몬스터체력:"+monster.monsterHP);
        return 1;
    }
}
