import java.util.Scanner;

public class ADifferentProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n1, n2;
        while(scanner.hasNext()){
            n1 = scanner.nextLong();
            n2 = scanner.nextLong();
            System.out.println(Math.abs(n1 - n2));
        }
    }
}
