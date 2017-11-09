//Student: Xingcheng Zhang
//Email: zhangxingcheng5@gmail.com
//EmpId: 23173328

package wuguituzi;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author jason
 */
public class CSC221XingchengZhangTortoiseAndHareGame {
    private int race= 75; //default race 75, can be change
    private static final Random randomNumbers=new Random();
    private int Tposition=1;
    private int Hposition=1;
    private static int clock=1;
    boolean Arrival = false;

    public void resetRace() {//method to reset race.
        boolean repeat = true;
        while (repeat) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Reset Race: ");
                int answer = input.nextInt();
                if(answer>1){
                    race = answer;
                }else {
                    throw new IllegalArgumentException("distance cannot be less than 1");
                }
                repeat = false;
            } catch (InputMismatchException e) {
                System.out.println("Error!!!!!");
                System.out.print(e +" You must enter integers, please try again.\n");
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException);
            }
        }
        
        Tposition = 1;
        Hposition = 1;
        clock = 1;
        Arrival = false;
    }

    public boolean IsContinue() {//Continue the game and it is able to reset the race
        String answer = " ";
        while (Arrival == true) {
            System.out.println();
            System.out.println("Do you want to continue the game and Reset the race");
            System.out.println("1. Enter Yes for continue! ");
            System.out.println("2. Enter No for quit the game! ");
            Scanner input = new Scanner(System.in);
            try {
                answer = input.nextLine();
                if (answer.equals("Yes")) {
                    resetRace();
                    WhoArrival();
                } else if (answer.equals("No")) {
                    return false;
                } else {
                    throw new IllegalArgumentException("Enter either \"Yes\" or \"No\"");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }

        }
        return false;

    }

    public void TortoisePosition() {//probability to get Tortoise's position
        int T = 1 + randomNumbers.nextInt(10);
        if (T <= 5) {
            Tposition = Tposition + 3;
            System.out.print("Tortoise Fast plod (50% chance), 3 squares to the right");
        }

        if (T >= 6 && T <= 7) {
            Tposition = Tposition - 6;
            System.out.print("Tortoise slip (20%), 6 squares to the left");
        }

        if (T >= 8 && T <= 10) {
            Tposition = Tposition + 1;
            System.out.print("Tortoise slow plod (30%), 1 square to the right");
        }
        if (Tposition < 1) {
            Tposition = 1;
        }

        if (Tposition > race) {
            Tposition = race;
        }

        System.out.printf("\nPosition Tortoise： " + "%d\n", Tposition);
        System.out.println();

    }

    public void HarePosition() {//Probablity to get Hare's position
        int H = randomNumbers.nextInt(10);
        if (H <= 2) {
            Hposition = Hposition;
            System.out.print("Hare sleep (10% chance), stay!");
        }

        if (H >= 3 && H <= 4) {
            Hposition = Hposition + 9;
            System.out.print("Hare Big hop (20% chance), 9 squares to the right");
        }

        if (H == 5) {
            Hposition = Hposition - 12;
            System.out.print("Hare Big slip (10% chance), 12 squares to the left");
        }

        if (H >= 6 && H <= 8) {
            Hposition = Hposition + 1;
            System.out.print("Hare small hop (30% chance), 1 square to the right");
        }

        if (H >= 9 && H <= 10) {
            Hposition = Hposition - 2;
            System.out.print("Hare small slip (20% chance), 2 squares to the left ");
        }

        if (Hposition < 1) {
            Hposition = 1;
        }

        if (Hposition > race) {
            Hposition = race;
        }

        System.out.printf("\nPosition Hare： " + "%d\n", Hposition);
        System.out.println();
    }

    public void AtTheStartPoint() {//At first second, the race start, and doesn't print OUCH!
        if (clock < 2) {
            if (Tposition == 1 && Hposition == 1) {
                System.out.println("BANG!!!!!");
                System.out.println("AND THEY'RE OFF");
                int i = 1;
                while (i <= race) {
                    if (i == Tposition && i == Hposition) {
                        System.out.println("T");
                        System.out.print("H");

                    } else {
                        System.out.print("=");
                    }

                    i++;

                }

                System.out.println();
                System.out.println("They are both at position 1 at beginning");
                System.out.println();

            }
        }
    }

    public void WhoAhead() { // Compare Tortoise's position with Hare's position, see who is ahead
        if (Tposition == Hposition) {
            if (Tposition > 1 && Hposition > 1) {

                System.out.println("OUCH!! \n");
            }
        } else {
            if (Tposition <= Hposition) {
                System.out.println("Right now：Hare ahead！\n");
            }
            if (Tposition >= Hposition) {
                System.out.println("Right now：Tortoise ahead！\n");
            }
        }
        System.out.println("------------------------------------------");
        System.out.printf("clock(s): %d%s\n", clock, "s");
    }

    public void Race() { //method to replace T or H with i, if their position are equal, print OUCH!

        int i = 1;
        while (i <= race) {
            if (i == Tposition && i == Hposition) {
                System.out.print("OUTH!");
            } else if (Tposition == i) {
                System.out.print("T");
            } else if (Hposition == i) {
                System.out.print("H");
            } else {
                System.out.print("=");
            }

            i++;
        }
        System.out.println();

    }
    public void WhoWin() {//determine who is the winner
        if (Tposition >= race) {
            System.out.println("TORTOISE WINS!!！YAY!!!");
            System.out.println();
            Arrival = true;
        }

        if (Hposition >= race) {
            System.out.println("Hare WIN！ YUCH!!");
            System.out.println();
            Arrival = true;
        }
        if (Tposition == race && Hposition == race) {
            System.out.println("It's a tie");
            System.out.println();
            Arrival = true;
        }
        clock++;
    }
    public boolean WhoArrival() {
        while (Arrival == false) {
            AtTheStartPoint();
            System.out.println();
            WhoAhead();
            TortoisePosition();
            HarePosition();
            Race();
            System.out.println();
            WhoWin();
            if (Arrival == true) {
                break;
            }
        }
        return true;
    }
    
}
    

