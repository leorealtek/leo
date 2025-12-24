package leo.Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner s = new Scanner(System.in);
    private static final float HEALTH_PLAYER = 100;
    private static final float HEALTH_ENEMY = 100;

    public static void main(String[] args) throws InterruptedException {

        System.out.print("Dimmi il nome del giocatore: ");
        String namePlayer = s.nextLine();
        Thread.sleep(300);

        System.out.println("Dimmi l'età del giocatore " + namePlayer);
        int agePlayer = s.nextInt();
        Thread.sleep(300);

        
        Weapon weaponPlayer = new Weapon(null, -1, -1);
        boolean choose = false;

        while(!choose){
            System.out.println("Dimmi con che arma vuoi far giocare " + namePlayer);
            System.out.println("1) Mani");
            System.out.println("2) Spada di legno");
            System.out.println("3) Spada di ferro");
            System.out.println("4) Spada laser");

            switch (s.nextInt()) {
                case 1:
                    weaponPlayer = new Weapon("Mani", 2, -1);
                    choose = true;        
                    break;
                case 2:
                    weaponPlayer = new Weapon("Spada di legno", 5, 10); 
                    choose = true;               
                    break;
                case 3:
                    weaponPlayer = new Weapon("Spada di ferro", 8, 17);
                    choose = true;
                    break;
                case 4:
                    weaponPlayer = new Weapon("Spada laser", 15, 35);   
                    choose = true;             
                    break;
                default:
                    System.out.println("Hai scelto un arma che non esiste. Riprova");
                    Thread.sleep(700);
                    break;
            }
        }
        s.nextLine();

        Player player = new Player(namePlayer, HEALTH_PLAYER, weaponPlayer, agePlayer, new ArrayList<Item>());
        System.out.print("Dimmi il nome dell'avversario: ");
        String nameEnemy = s.nextLine();

        Enemy enemy = new Enemy(nameEnemy, HEALTH_ENEMY, null);
        System.out.println(player.toString());
        System.out.println(enemy.toString());
    }
}
