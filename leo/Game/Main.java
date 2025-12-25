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
        Weapon mani = new Weapon("Mani", 2, -1);
        Weapon spadaDiLegno = new Weapon("Spada di legno", 5, 10);
        Weapon spadaDiFerro = new Weapon("Spada di ferro", 8, 17);
        Weapon spadaLaser = new Weapon("Spada laser", 15, 35); 
        
        boolean choose = false;

        while(!choose){
            System.out.println("Dimmi con che arma vuoi far giocare " + namePlayer);
            System.out.println("1) Mani");
            System.out.println("2) Spada di legno");
            System.out.println("3) Spada di ferro");
            System.out.println("4) Spada laser");

            switch (s.nextInt()) {
                case 1:
                    weaponPlayer = mani;
                    choose = true;        
                    break;
                case 2:
                    weaponPlayer = spadaDiLegno; 
                    choose = true;               
                    break;
                case 3:
                    weaponPlayer = spadaDiFerro;
                    choose = true;
                    break;
                case 4:
                    weaponPlayer = spadaLaser;   
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

        choose = false;

        Weapon weaponEnemy = new Weapon(null, -1, -1);


        while(!choose){
            System.out.println("Dimmi con che arma vuoi far giocare " + nameEnemy);
            System.out.println("1) Mani");
            System.out.println("2) Spada di legno");
            System.out.println("3) Spada di ferro");
            System.out.println("4) Spada laser");

            switch (s.nextInt()) {
                case 1:
                    weaponEnemy = mani;
                    choose = true;        
                    break;
                case 2:
                    weaponEnemy = spadaDiLegno; 
                    choose = true;               
                    break;
                case 3:
                    weaponEnemy = spadaDiFerro;
                    choose = true;
                    break;
                case 4:
                    weaponEnemy = spadaLaser;   
                    choose = true;             
                    break;
                default:
                    System.out.println("Hai scelto un arma che non esiste. Riprova");
                    Thread.sleep(700);
                    break;
            }
        }

        Enemy enemy = new Enemy(nameEnemy, HEALTH_ENEMY, weaponEnemy);
        
        player.attack(enemy);
        enemy.attack(player);
        System.out.println(player);
        System.out.println(enemy);

        HealPotion pozione = new HealPotion("Pozione di cura", 10.0f, 2);

        player.addItemToInventory(pozione);
        player.useItem(pozione);

        System.out.println(player.getInventory());

        player.useItem(pozione);

        System.out.println(player.getHealth());
    }
}