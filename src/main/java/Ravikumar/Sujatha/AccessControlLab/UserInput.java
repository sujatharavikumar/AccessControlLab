package Ravikumar.Sujatha.AccessControlLab;

import java.util.Scanner;

/**
 * Created by sujatharavikumar on 9/14/16.
 */
public class UserInput {

        Scanner input = new Scanner(System.in);


        public int getMenuOption(){
            return input.nextInt();
        }

        public String getName(){
            return input.nextLine();
        }

        public double getAmount(){
            return input.nextDouble();
        }

        public int getInt(){
            return input.nextInt();
        }

}
