// Greetings Program that takes in input
import java.util.Scanner;
public class GreetingsInput
{
    // Main Function
    public static void main(String[] args)
    {
        Scanner kboard = new Scanner(System.in);
        System.out.println("Enter your first name: ");
        String firstName = kboard.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = kboard.nextLine();
        System.out.println("Hello " + firstName + " " + lastName);
        kboard.close();
    }
}