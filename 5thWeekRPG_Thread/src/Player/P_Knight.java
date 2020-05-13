package Player;

import Monster.Monster;

public class P_Knight extends Player{

    public P_Knight(int yPlace, int xPlace,GameMap map) {
        super(yPlace, xPlace, map);
    }

    @Override
    public void setPlayerDefence_Knight(int playerDefence_Knight) {
        super.setPlayerDefence_Knight(playerDefence_Knight);
        super.playerMaxHP = (int) (super.playerMaxHP * 1.2);
        super.playerHP = super.playerMaxHP;
    }


    @Override
    public int skill_P(Player player, Monster monster){
        player.playerSkillPoint--;
        player.knightSkill++;
        System.out.println("기사 스킬 발동!!");
        System.out.println("반격 자세를 취해 다음 공격을 방어하고 더 큰 데미지를 돌려줍니다");
        return 2;
    }
}
