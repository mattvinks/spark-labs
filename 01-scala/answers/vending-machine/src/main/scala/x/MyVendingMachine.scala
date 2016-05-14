package x

import scala.collection.mutable
import scala.collection.mutable.{Map, HashMap}

class MyVendingMachine extends VendingMachine {

  val store: mutable.Map[String, Stock] = Map()
  var myStash = 0
  
  override def addStockItem(item: String, price: Int, qty: Int): Int = {
    if (store.contains(item)) {
      // TODO should we be able to set the new price?
      val newStock = store(item).add(qty)
      store.put(item, newStock)
      newStock.getQty
    } else {
      store.put(item, new Stock(item, price, qty))
      return qty
    }
   }

  override def checkPrice(item: String): Int = {
    if (store.contains(item)) {       
      store(item).getPrice()
    } else {
      // 0 price on absent items
      0
    }
  }

  override def buy(item: String): ReturnCode = {
    if (store.contains(item)) {
      // TODO what happens when we are out of stock?
      store(item).add(-1)
      Success
    } else {
      ItemNotInStock
    }
  }

  override def balance(): Int = {
    myStash
  }

  override def deposit(amount: Int): Int = {
    myStash += amount
    myStash
  }
}

class Stock(item: String, price: Int, qty: Int) {
  def getItem(): String = {
    item
  }
  def getPrice(): Int = {
    price
  }
  def getQty: Int = {
    qty
  }
  def add(addQty: Int): Stock = {
    new Stock(item, price, qty + addQty)
  }
}
