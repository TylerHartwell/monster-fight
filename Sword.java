//Class3of3 and Inheritance
class Sword extends Weapon{
  
  //Constructor3of3
  public Sword(){
  weaponType = "sword";
  baseDamage = 15;
  bonusDamage = 0;
  totalDamage = baseDamage;
  }
  
  
  public void Attack(){
    totalDamage= baseDamage + bonusDamage;
    System.out.println("\nYou swing the " + weaponType + " and deal " + totalDamage + " damage!");
  }

  public void AddBonusDamage(){
    bonusDamage += 10; 
  }

  public void ResetBonusDamage(){
    bonusDamage = 0;
  }

  
  public void IncreaseBaseDamage(){
    baseDamage += 15;
  }
  
}