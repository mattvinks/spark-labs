package x

import scala.collection.mutable
import scala.collection.mutable.{Map, HashMap}

// class to represent a 'StockedItem'
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

// simple implementation of Vending Machine
class MyVendingMachine extends VendingMachine {

  val store: mutable.Map[String, Stock] = Map()
  var myStash = 0

  override def addStockItem(item: String, price: Int, qty: Int): Int = {
    if (store.contains(item)) {
      // adding an item with a new price automatically re-prices existing stock
      val newStock = store(item).add(price, qty)

      // TODO-1 : put new stock in 'store'
      //store.put(item, ???)
      newStock.getQty
    } else {
      store.put(item, new Stock(item, price, qty))
      // TODO-2 : return qty
      return 0 // fix this
    }
  }

  override def checkPrice(item: String): Int = {
    if (store.contains(item)) {
      // TODO-3 return price
      return 0  // replace with following line
      // store(item).???
    } else {
      throw new Exception("checking price on item not in stock: " + item)
    }
  }

  override def checkStock(item: String) : Int = {
    if (store.contains(item)) {
     // TODO-4 : return stock
      0   // replace with  below
      // store(item).???
    } else {
      0  // error and zero are OK
    }
  }

  override def balance(): Int = {
    myStash
  }

  override def deposit(amount: Int): Int = {
    // TODO-5  : update myStash + amount
    // myStash += ???
    myStash
  }

  override def buy(item: String): ReturnCode = {
    if (store.contains(item)) {
      val myItem = store(item)

      if (myItem.getQty < 1)
        return SoldOut

      // TODO-6 : handle not enough money
      if (myStash  < myItem.getPrice())
        return Success // this is wrong return type

      // good, go ahead and buy
      store.put(item, myItem.buy)
      return Success
    } else {
      // TODO-7  : return ItemNotInStock
      Success
    }
  }




}


