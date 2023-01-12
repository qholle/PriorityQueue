import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A max-heap implementation of a priority queue for Orders, where the Order with the LONGEST prep
 * time is returned first instead of the strict first-in-first-out queue as in P08.
 * 
 */
public class OrderPriorityQueue implements PriorityQueueADT<Order> {

  private Order[] queueHeap;
  private int size;

  /**
   * Constructs a PriorityQueue for Orders with the given capacity
   * 
   * @param capacity the initial capacity for the queue
   * @throws IllegalArgumentException if the given capacity is 0 or negative
   */
  public OrderPriorityQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException();
    }
    queueHeap = new Order[capacity];
  }

  /**
   * Inserts a new Order into the queue in the appropriate position using a heap's add logic.
   * 
   * @param newOrder the Order to be added to the queue
   */
  @Override
  public void insert(Order newOrder) {
    if (this.isEmpty()) {
      queueHeap[0] = newOrder;
      size++;
      return;
    }

    if (size == queueHeap.length) {
      queueHeap = Arrays.copyOf(queueHeap, queueHeap.length * 2);
    }

    queueHeap[size] = newOrder;
    percolateUp(queueHeap, size);
    size++;
  }

  /**
   * @param heap       an array containing the Order values to be percolated into a valid heap
   * @param orderIndex the index of the Order to be percolated up
   */
  protected static void percolateUp(Order[] heap, int orderIndex) {
    //if the parent is bigger than the child, do nothing
    if (heap[orderIndex].compareTo(heap[(orderIndex - 1) / 2]) <= 0) {
      return;
    }
    //while the parent of the child is less than the child, swap values
    while (heap[orderIndex].compareTo(heap[(orderIndex - 1) / 2]) > 0) { 
      Order temp = heap[(orderIndex - 1) / 2];
      heap[(orderIndex - 1) / 2] = heap[orderIndex];
      heap[orderIndex] = temp;
      orderIndex = (orderIndex - 1) / 2;
    }
  }

  /**
   * Return the Order with the longest prep time from the queue and adjust the queue accordingly
   * 
   * @return the Order with the current longest prep time from the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Order removeBest() {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }
    Order prevRoot = queueHeap[0];

    queueHeap[0] = null;
    queueHeap[0] = queueHeap[size-1];
    
    percolateDown(queueHeap, 0, size);

    size--;
    return prevRoot;
  }

  /**
   * @param heap       an array containing the Order values to be percolated into a valid heap
   * @param orderIndex the index of the Order to be percolated down
   * @param size       the number of initialized elements in the heap
   */
  protected static void percolateDown(Order[] heap, int orderIndex, int size) {
    int childIndex = 2 * orderIndex + 1;
    Order currOrder = heap[orderIndex];
    
    //iterate through the tree to locate out of place values
    while ((childIndex < size)) {
      Order maxOrder = currOrder;
      int maxIndex = -1;
      for (int i = 0; i < 2 && i + childIndex < size; i++) { //check both children
        if (heap[i + childIndex].compareTo(maxOrder) > 0) {
          maxOrder = heap[i + childIndex];
          maxIndex = i + childIndex;
        }
      }
      
      if (maxOrder.compareTo(currOrder) == 0) {
        return;
      }
      else {
        Order temp = heap[orderIndex];
        heap[orderIndex] = heap[maxIndex];
        heap[maxIndex] = temp;
        orderIndex = maxIndex;
        childIndex = 2 * orderIndex + 1;
      }
    }
  }

  /**
   * Return the Order with the highest prep time from the queue without altering the queue
   * 
   * @return the Order with the current longest prep time from the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Order peekBest() {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }
    return queueHeap[0];
  }

  /**
   * Returns true if the queue contains no Orders, false otherwise
   * 
   * @return true if the queue contains no Orders, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Returns the number of elements currently in the queue
   * 
   * @return the number of elements currently in the queue
   */
  public int size() {
    return size;
  }

  /**
   * Creates a String representation of this PriorityQueue. 
   * 
   * @return the String representation of this PriorityQueue, primarily for testing purposes
   */
  public String toString() {
    String toReturn = "";
    for (int i = 0; i < this.size; i++) {
      toReturn += queueHeap[i].getID() + "(" + queueHeap[i].getPrepTime() + ")";
      if (i < this.size - 1)
        toReturn += ", ";
    }
    return toReturn;
  }

}