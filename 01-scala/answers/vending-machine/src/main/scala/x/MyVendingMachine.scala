package x

import scala.collection.mutable

class MyVendingMachine extends VendingMachine {

  val stock : (String -> (Int, Int)) = mutable.HashMap() // item -> (price, qty)

  var money = 0

  override def stockItem(item: String, price: Int, qty: Int) : Int = {
    println("".format("stockItem (%s, %d, %d)" , item, price,  qty))
    stock(item) = (price, qty)
    qty
  }

  def checkPrice(item : String) : Int ={
    stock.get(item)._1
  }

  override def buy(item: String) : ReturnCode = {
    println ("buy(" + item + ")")

    val stockedItem = stocks.get(item)
    val itemPrice = stockedItem._1
    // TODO

    return Success
  }

  override def balance() : Int = {
    println ("balance()")
    return money
  }

  override def deposit(amount: Int)  : Int = {
    println ("deposit(" + amount + ")")
    money = money + amount
    money
  }
}
