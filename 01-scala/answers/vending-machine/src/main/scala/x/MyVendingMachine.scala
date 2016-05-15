package x

import scala.collection.mutable
import scala.collection.mutable.{Map, HashMap}

/**
  * Trying to use val instead of var whenever possible
  */
class MyVendingMachine extends VendingMachine {

  val store: mutable.Map[String, Stock] = Map()
  var myStash = 0

  override def addStockItem(item: String, price: Int, qty: Int): Int = {
    if (store.contains(item)) {
      // adding an item with a new price automatically re-prices existing stock
      val newStock = store(item).add(price, qty)
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
      sys.error("checking price on item not in stock: " + item)
    }
  }

  override def buy(item: String): ReturnCode = {
    if (store.contains(item)) {
      val myItem = store(item)
      val trialNewBalance = myStash - 1 * myItem.getPrice()
      if (trialNewBalance < 0) sys.error("Not enough money") else myStash = trialNewBalance
      // TODO what happens when we are out of stock? 
      store.put(item, myItem.buy)
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

  def buy(): Stock = {
    new Stock(item, price, qty - 1)
  }

  def add(newPrice: Int, addQty: Int): Stock = {
    new Stock(item, newPrice, qty + addQty)
  }
}
