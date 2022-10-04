//Class2of3 and Abstraction
abstract class Weapon{
  String weaponType;
  int baseDamage;
  int bonusDamage;
  int totalDamage;
  

  public abstract void Attack();
  public abstract void AddBonusDamage();
}