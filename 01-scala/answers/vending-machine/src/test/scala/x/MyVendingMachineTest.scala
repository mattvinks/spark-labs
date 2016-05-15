package x

import org.specs2.mutable.Specification

class MyVendingMachineTest extends Specification {


  "x.MyVendingMachineTest" should {
    "accept initial inventory" in {
      new MyVendingMachine().addStockItem("candy", 20, 5) mustEqual 5
    }
  }
  "accept additinal inventory" in {
    val machine = new MyVendingMachine
    machine.addStockItem("candy", 20, 5)
    machine.addStockItem("candy", 20, 2) mustEqual 7
  }
  "check item price" in {
    val machine = new MyVendingMachine
    machine.addStockItem("candy", 20, 5)
    machine.checkPrice("candy") mustEqual 20
  }
  "accept deposit" in {
    val machine = new MyVendingMachine
    machine.deposit(100) mustEqual 100
    machine.deposit(100) mustEqual 200
    machine.deposit(0) mustEqual 200
  }
  
  "dispence item" in {
    val machine = new MyVendingMachine
    machine.deposit(100)
    machine.addStockItem("candy", 20, 5)
    machine.buy("candy")
    machine.balance mustEqual 80
    machine.addStockItem("candy", 20, 5) mustEqual 9
  }
}
