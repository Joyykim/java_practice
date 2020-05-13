import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int scNum;

        System.out.println("1.마름모 2.원 3.별\n그 외 숫자 입력 시 종료");
        Scanner scanner = new Scanner(System.in);
        scNum = scanner.nextInt();
        switch (scNum){
            case 1:
                Dia.dia();
                break;
            case 2:
                Circle.circle();
                break;
            case 3:
                Star.star();
                break;
            default:
                break;
        }
    }
}
