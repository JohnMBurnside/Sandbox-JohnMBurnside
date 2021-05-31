// (Question 13) Program that takes an integer as an input, then takes the integer and times its value by 2
import java.util.Scanner;
public class IntegerInput
{
    public static void main(String[] args)
    {
        Scanner kboard = new Scanner(System.in);
        System.out.println("Enter an integer to double: ");
        int num = kboard.nextInt();
        System.out.println("2 * " + num + " = " + (num + num));
        kboard.close();
    }
}