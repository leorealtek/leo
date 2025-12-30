package leo.Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner s = new Scanner(System.in);
    private static final float HEALTH_PLAYER = 100;
    private static final float HEALTH_ENEMY = 100;

    public static Weapon chooseWeapon(Entity entity) {
        boolean choose = false;
        Weapon weaponPlayer = new Weapon(null, -1, -1);
        

        while(!choose){
            System.out.println("Dimmi con che arma vuoi far giocare " + entity.getName());
            System.out.println("1) Mani");
            System.out.println("2) Spada di legno");
            System.out.println("3) Spada di ferro");
            System.out.println("4) Spada laser");

            switch (s.nextInt()) {
                case 1:
                    weaponPlayer = Armory.getMani();
                    choose = true;        
                    break;
                case 2:
                    weaponPlayer = Armory.getSpadaDiLegno(); 
                    choose = true;               
                    break;
                case 3:
                    weaponPlayer = Armory.getSpadaDiFerro();
                    choose = true;
                    break;
                case 4:
                    weaponPlayer = Armory.getSpadaLaser();   
                    choose = true;             
                    break;
                default:
                    System.out.println("Hai scelto un arma che non esiste. Riprova");
                    try {
                        Thread.sleep(700);
                    } catch (Exception e) {}
                    break;
            }
        }
        return weaponPlayer;
    }
    
    public static void main(String[] args) throws InterruptedException {

        System.out.print("Dimmi il nome del giocatore: ");
        String namePlayer = s.nextLine();
        Thread.sleep(300);

        System.out.println("Dimmi l'età del giocatore " + namePlayer);
        int agePlayer = s.nextInt();
        Thread.sleep(300);
        
        Player player = new Player(namePlayer, HEALTH_PLAYER, null, agePlayer, new ArrayList<Item>());
        player.setWeapon(chooseWeapon(player));

        System.out.print("Dimmi il nome dell'avversario: ");
        s.nextLine();
        String nameEnemy = s.nextLine();

        Enemy enemy = new Enemy(nameEnemy, HEALTH_ENEMY, null);
        enemy.setWeapon(chooseWeapon(enemy));

        HealPotion pozione = new HealPotion("Pozione di cura", 10.0f, 2);
        player.addItemToInventory(pozione);

        StrenghtPotion pozioneForza = new StrenghtPotion("Pozione di forza", 3, 5);
        player.addItemToInventory(pozioneForza);

        player.attack(enemy);
        enemy.attack(player);

        Entity[] allEntities = {player, enemy};
        for (Entity entity : allEntities) {
            System.out.println(entity);
        }
    }
}