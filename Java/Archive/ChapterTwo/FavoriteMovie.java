// (Question 11) Program that takes in favorite movie as input and prints it
import java.util.Scanner;
public class FavoriteMovie
{
    // Main Function
    public static void main(String[] args)
    {
        Scanner kboard = new Scanner(System.in);
        System.out.println("What is your favorite movie? ");
        String favoriteMovie = kboard.nextLine();
        System.out.println("I think " + favoriteMovie + " is a terrible movie!");
        System.out.println("Just kidding! I like " + favoriteMovie + ", too.");
        kboard.close();
    }
}