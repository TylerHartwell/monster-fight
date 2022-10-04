//Class1of3 and Encapsulation//
class Monster{
  private String monsterType;
  private int monsterHealth;


  //Constructor1of3
  public Monster(){
    this.monsterType = "troll";
    this.monsterHealth = 135;
  }

  //Constructor2of3 and Polymorphism through Overloading
  public Monster(String type, int hp){
    this.monsterType = type;
    this.monsterHealth = hp;

  }

  public String GetMonsterType(){
    return monsterType;
  }

  public int GetMonsterHealth(){
    return monsterHealth;
  }

  public void DamageMonster(int damage){
    monsterHealth -= damage;
  }
}