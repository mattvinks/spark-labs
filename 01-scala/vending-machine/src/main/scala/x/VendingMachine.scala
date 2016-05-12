package x



sealed trait ReturnCode
case object Success extends ReturnCode
case object NotEnoughMoney extends ReturnCode
case object SoldOut extends ReturnCode

trait VendingMachine {


  // admin actions
  def stockItem(item : String , price: Int, qty : Int) : Int

  // user actions
  def deposit(amount : Int) : Int
  def checkPrice(item : String) : Int
  def buy(item : String) : ReturnCode
  def balance() : Int

}
