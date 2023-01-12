import java.util.NoSuchElementException;

public class OrderPriorityQueueTester {

  /**
   * Checks the correctness of the isEmpty method of OrderPriorityQueue.
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testIsEmpty() {
    Order.resetIDGenerator();
    OrderPriorityQueue tester = new OrderPriorityQueue(5);

    // Scenario One: empty queue
    if (!tester.isEmpty()) {
      System.out.println("Scenario 1 testIsEmpty failed. Queue was not empty.");
      return false;
    }

    // Scenario Two: add an only order in the queue
    Order one = new Order("Cheese Sandwich", 5);
    tester.insert(one);
    if (tester.isEmpty()) {
      System.out.println("Scenario 2 testIsEmpty failed. Queue was empty when it shouldn't have.");
      return false;
    }

    // Scenario Three: remove the only order in the queue
    tester.removeBest();
    if (!tester.isEmpty()) {
      System.out.println("Scenario 3 testIsEmpty failed. Queue was not empty.");
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testInsertBasic() {
    Order.resetIDGenerator();
    OrderPriorityQueue tester = new OrderPriorityQueue(5);

    Order one = new Order("Cheese Sandwich", 15);
    Order two = new Order("Ham Sandwich", 13);
    Order three = new Order("Mac & Cheese", 8);
    Order four = new Order("Pizza Rolls", 5);


    // Scenario One: adding 1 order to queue
    tester.insert(one);

    if (!tester.toString().equals("1001(15)")) {
      System.out.println("Scenario 1 testInsertBasic Failed.");
      return false;
    }

    // Scenario Two: adding 3 more orders to queue
    tester.insert(two);
    tester.insert(three);
    tester.insert(four);

    if (!tester.toString().equals("1001(15), 1002(13), 1003(8), 1004(5)")) {
      System.out.println("Scenario 2 testInsertBasic Failed.");
      return false;
    }

    return true; // included to prevent compiler errors
  }

  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testPercolateUp() {
    Order.resetIDGenerator();

    Order[] testerArray = new Order[5];
    Order[] expectedArray = new Order[5];

    Order one = new Order("Chicken Tenders", 18);
    Order two = new Order("Ham Sandwich", 13);
    Order three = new Order("Mac & Cheese", 8);
    Order four = new Order("Pizza Rolls", 5);
    Order five = new Order("Cheese Sandwich", 15);

    testerArray[0] = one;
    testerArray[1] = two;
    testerArray[2] = three;
    testerArray[3] = four;
    testerArray[4] = five;

    expectedArray[0] = one;
    expectedArray[1] = five;
    expectedArray[2] = three;
    expectedArray[3] = four;
    expectedArray[4] = two;

    OrderPriorityQueue.percolateUp(testerArray, 4);

    for (int i = 0; i < expectedArray.length; i++) {
      if (!testerArray[i].equals(expectedArray[i])) {
        System.out.println("Scenario 1 testPercolateUp failed.");
        return false;
      }
    }

    return true;
  }

  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testInsertAdvanced() {
    Order.resetIDGenerator();

    OrderPriorityQueue tester = new OrderPriorityQueue(6);

    Order three = new Order("Mac & Cheese", 8);
    Order two = new Order("Ham Sandwich", 13);
    Order four = new Order("Pizza Rolls", 5);
    Order five = new Order("Chicken Tenders", 18);
    Order six = new Order("Soft Stix", 14);
    Order one = new Order("Cheese Sandwich", 15);

    tester.insert(one);
    tester.insert(two);
    tester.insert(three);
    tester.insert(four);
    tester.insert(five);
    tester.insert(six);

    if (!tester.toString().equals("1004(18), 1006(15), 1005(14), 1003(5), 1002(13), 1001(8)")) {
      System.out.println("Scenario 1 testInsertAdvanced failed.");
      return false;
    }

    return true; // included to prevent compiler errors
  }

  /**
   * Checks the correctness of the insert method of OrderPriorityQueue.
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testPercolateDown() {
    Order.resetIDGenerator();

    Order[] testerArray = new Order[5];
    Order[] expectedArray = new Order[5];

    Order one = new Order("Pizza Rolls", 5);
    Order two = new Order("Chicken Tenders", 18);
    Order three = new Order("Ham Sandwich", 13);
    Order four = new Order("Mac & Cheese", 8);
    Order five = new Order("Cheese Sandwich", 15);

    testerArray[0] = one;
    testerArray[1] = two;
    testerArray[2] = three;
    testerArray[3] = four;
    testerArray[4] = five;

    expectedArray[0] = two;
    expectedArray[1] = five;
    expectedArray[2] = three;
    expectedArray[3] = four;
    expectedArray[4] = one;

    OrderPriorityQueue.percolateDown(testerArray, 0, 5);

    for (int i = 0; i < expectedArray.length; i++) {
      if (!testerArray[i].equals(expectedArray[i])) {
        System.out.println("Scenario 1 testPercolateUp failed.");
        return false;
      }
    }

    return true;
  }

  /**
   * Checks the correctness of the removeBest and peekBest methods of OrderPriorityQueue.
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testPeekRemove() {
    Order.resetIDGenerator();

    OrderPriorityQueue tester = new OrderPriorityQueue(6);

    Order three = new Order("Mac & Cheese", 8);
    Order two = new Order("Ham Sandwich", 13);
    Order four = new Order("Pizza Rolls", 5);
    Order five = new Order("Chicken Tenders", 18);
    Order six = new Order("Soft Stix", 14);
    Order one = new Order("Cheese Sandwich", 15);

    tester.insert(one);
    tester.insert(two);
    tester.insert(three);
    tester.insert(four);
    tester.insert(five);
    tester.insert(six);

    // Scenario 1: remove all but one order, and check if empty and proper order is remaining.
    if (tester.removeBest().getPrepTime() != 18) {
      System.out
          .println("Scenario 1 testPeekRemove failed. Best order didn't remove the correct one.");
      return false;
    }
    if (tester.removeBest().getPrepTime() != 15) {
      System.out
          .println("Scenario 1 testPeekRemove failed. Best order didn't remove the correct one.");
      return false;
    }
    if (tester.removeBest().getPrepTime() != 14) {
      System.out
          .println("Scenario 1 testPeekRemove failed. Best order didn't remove the correct one.");
      return false;
    }
    if (tester.removeBest().getPrepTime() != 13) {
      System.out
          .println("Scenario 1 testPeekRemove failed. Best order didn't remove the correct one.");
      return false;
    }
    if (tester.removeBest().getPrepTime() != 8) {
      System.out
          .println("Scenario 1 testPeekRemove failed. Best order didn't remove the correct one.");
      return false;
    }
    if (!tester.peekBest().equals(four)) {
      System.out.println("Scenario 1 testPeekRemove failed. Best order wasn't the correct one.");
      return false;
    }
    if (tester.isEmpty()) {
      System.out.println("Scenario 1 testPeekRemove failed. Tester was empty.");
      return false;
    }

    // Scenario 2: remove the final order
    if (tester.removeBest().getPrepTime() != 5) {
      System.out
          .println("Scenario 2 testPeekRemove failed. Best order didn't remove the correct one.");
      return false;
    }
    if (!tester.isEmpty()) {
      System.out.println("Scenario 2 testPeekRemove failed. Tester wasn't empty.");
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the removeBest and peekBest methods, as well as the constructor of
   * the OrderPriorityQueue class for erroneous inputs and/or states
   * 
   * @return true if and only if ALL tests pass
   */
  public static boolean testErrors() {
    Order.resetIDGenerator();
    try {
      // Scenario 1: invalid priorityQueue construction
      try {
        OrderPriorityQueue tester = new OrderPriorityQueue(-1);
        return false;
      } catch (IllegalArgumentException e) {
      }

      // Scenario 2: invalid peekBest call
      try {
        OrderPriorityQueue tester = new OrderPriorityQueue(2);
        tester.peekBest();
        return false;
      } catch (NoSuchElementException f) {
      }

      // Scenario 3: invalid removeBest call
      try {
        OrderPriorityQueue tester = new OrderPriorityQueue(2);
        tester.removeBest();
        return false;
      } catch (NoSuchElementException g) {
      }
    } catch (Exception h) {
      return false;
    }
    return true;
  }

  /**
   * Calls the test methods individually and displays their output
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("isEmpty: " + testIsEmpty());
    System.out.println("insert basic: " + testInsertBasic());
    System.out.println("percolate UP: " + testPercolateUp());
    System.out.println("insert advanced: " + testInsertAdvanced());
    System.out.println("percolate DOWN: " + testPercolateDown());
    System.out.println("peek/remove valid: " + testPeekRemove());
    System.out.println("error: " + testErrors());
  }

}
