class Bow extends Weapon{
  
  
  public Bow(){
  weaponType = "bow";
  baseDamage = 10;
  bonusDamage = 0;
  totalDamage = baseDamage + bonusDamage;
  }
  
  
  public void Attack(){
    totalDamage= baseDamage + bonusDamage;
    System.out.println("\nYou shoot with the " + weaponType + " and deal " + totalDamage + " damage!");
  }

  public void AddBonusDamage(){
    bonusDamage += 15; 
  }
  
}