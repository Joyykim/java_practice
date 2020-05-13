import java.util.Scanner;

public class Circle {
    public static void circle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("원의 크기 입력");
        double scNum = scanner.nextInt() + 20;

        //원의 중심좌표
        int a = (int)scNum/2;

        for (int y = 0; y < scNum; y++){
            for (int x = 0; x < scNum; x++){
                double radius = scNum/2;

                boolean c = radius-1 > Math.sqrt(Math.pow((x-a),2) + Math.pow((y-a),2));
                if (c){
                    System.out.print("***");
                }else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
}
