package Player;

import Monster.Monster;


public class P_Warrior extends Player {

    public P_Warrior(int yPlace, int xPlace) {
        super(yPlace, xPlace);
    }

    @Override
    public void setPlayerATK(double playerAttack_Warrior) {
        super.playerATK = (int) (this.playerATK * 1.2); // 전사 공격력 증가
        super.playerAttack_Warrior = playerAttack_Warrior;
    }

    @Override
    public int skill_P(Player player, Monster monster){

        player.playerSkillPoint--;
        System.out.println("전사 스킬 발동!!");
        System.out.println("두배의 데미지를 주고 준 피해의 절반 만큼 체력을 회복합니다");

        int damage = (int)(player.playerATK * player.playerAttack_Warrior*2); // 전사 스탯 * 공격력을 정수로 변환

        monster.monsterHP -= damage;
        player.playerHP += damage/2;

        System.out.println("\n데미지-"+damage+"\n몬스터체력:"+monster.monsterHP);
        System.out.println("플레이어 체력을 "+damage/2+"회복\n");

        return 0;
    }
}
