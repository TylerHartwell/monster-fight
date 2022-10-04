import java.util.*;

class Main {
    static int difficulty;
    static int playerHealth = 100;
    static int wargHealth = 65;
    static int orcHealth = 125;
    static String yourWeapon = null;
    static String yourAction = null;
    static Bow bow = null;
    static Sword sword = null;
    static int nextEnemyCountdown = 4;
    static ArrayList<Monster> monsters;
    static boolean usingBow = false;
    static boolean usingSword = false;
    static boolean hasAttacked = false;
    static int restAmount = 35 -5*difficulty;
    
    
  public static void main(String[] args) {
    monsters =  new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    ChooseDifficulty(scan);
    SpawnMonster();
    Scanner scan2 = new Scanner(System.in);
    ChooseWeapon(scan2); 
    while(playerHealth>0 && monsters.size()>0){
      if(nextEnemyCountdown<=0){
        SpawnMonster();
        nextEnemyCountdown = 5;
      }
      System.out.println("Your health is now " + playerHealth + " and there are " + monsters.size() + " monsters left\n");
      Scanner scan3 = new Scanner(System.in);
      ChooseAction(scan3);
      //traversing collection and modifying
      for(Monster monster: monsters){
        if(hasAttacked && usingBow){
        monster.DamageMonster(bow.totalDamage);
        }else if(hasAttacked && usingSword){
        monster.DamageMonster(sword.totalDamage);
        }
      }
      for(Iterator<Monster> it =  monsters.iterator(); it.hasNext();){
        Monster monster = it.next();
        if(monster.GetMonsterHealth()<=0){
          System.out.println("A monster is defeated!");
          it.remove();
        }
      }
      for(Monster monsterRemaining: monsters){
        System.out.println("The " + monsterRemaining.GetMonsterType() + " with " + monsterRemaining.GetMonsterHealth() + " health left attacks!");
        playerHealth -= 20 + 5*difficulty;
        if(usingSword && monsterRemaining.GetMonsterType()== "troll"){
          if(hasAttacked){
            playerHealth -= 10;
            sword.IncreaseBaseDamage();
          }else{
          playerHealth += 30;
          nextEnemyCountdown -= 3;
          }  
        }
      }
      nextEnemyCountdown -= 1;
    }
    FinalResult();
    scan.close();
  }

////////////////////////////////////////////////////////////////
  static void FinalResult(){
    if(playerHealth<=0){
      System.out.println("You died");
    }else{
      System.out.println("You win");
    }
    System.out.println("End of program");
  }
  
///////////////////////////////////////////////////////////////
  static void ChooseAction(Scanner scan3){
    System.out.println("Will you attack or rest?");
    yourAction = scan3.nextLine();

    //if resting
    if(!yourAction.equals("attack")){
      hasAttacked = false;
      System.out.println("\nYou gain some health");
      playerHealth += restAmount;
      nextEnemyCountdown -= 2;
      if(usingBow){
        System.out.println("And you improve the poison for your arrows");
        bow.AddBonusDamage();
      }else{
        System.out.println("And you lose momentum");
        sword.ResetBonusDamage();
        nextEnemyCountdown -= 1;
      }
    }else{
      //if attacking
      if(usingBow){
        bow.Attack();
        hasAttacked = true;
      }else{
        sword.Attack();
        hasAttacked = true;
        sword.AddBonusDamage();
        System.out.println("You gain some momentum for another attack\n");

      }

    }
  }
//////////////////////////////////////////////////////////////
  static void ChooseWeapon(Scanner scan2){
    System.out.println("Will you use your bow or sword?");
    yourWeapon = scan2.nextLine();
   // System.out.println("Your weapon choice is " + yourWeapon);
    if(!yourWeapon.equals("bow")){
      System.out.println("\nYou only have enough time to pull out your sword anyway!");
      sword = new Sword();
      usingSword = true;
    }else{
      System.out.println("\nYou back up and ready your bow");
      bow = new Bow();
      usingBow = true;
    }
    
    
  }
  
 /////////////////////////////////////////////////////////////////// 
  static void SpawnMonster(){
    //switch statement
    switch(difficulty){
      case 1: Monster monster1 = new Monster("warg", wargHealth);
        monsters.add(monster1);
        System.out.println("\nA wild " + monster1.GetMonsterType() + " appears!\n");
        wargHealth += 10;
        break;
      case 2: Monster monster2 = new Monster("orc", orcHealth);
        monsters.add(monster2);
        System.out.println("\nA cunning " + monster2.GetMonsterType() + " sneaks up!\n");
        difficulty = 1;
        break;
      case 3: Monster monster3 = new Monster();
        monsters.add(monster3);
        System.out.println("\nA giant " + monster3.GetMonsterType() + " crashes in!\n");
        System.out.println("Arrows might need more preperation and getting close might be volatile\n");
        nextEnemyCountdown = 7;   
        difficulty = 1;
        break;
      default: System.out.println("This default case shouldn't be possible because of the if block in ChooseDifficulty");
        break;
    }
    
  }
 //////////////////////////////////////////////////////////// 
  static void ChooseDifficulty(Scanner scan){
    
    System.out.println("Welcome!");
    System.out.println("What is your name, warrior?");
    String yourName = scan.nextLine();
    System.out.println(yourName + "! Pick a number, 1 through 3, how difficult your fight will be...");

    //exception handling
    try{
      difficulty = scan.nextInt();
    }
    catch(Exception e){
      System.out.println("That wasn't even a number! You must want a real challenge.");
      difficulty = 3;
    }
    
    if(difficulty < 1 || difficulty >3 ){
      System.out.println("That's not an option, how about you just try an easy one");
      difficulty = 1;
    }
  }
////////////////////////////////////////////////////////// 
}