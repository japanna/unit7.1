// File LinkedDequeue.java 
/** 
 *  This class represents a Dequeue  (“double-ended queue”) datatype implemented 
 *  using a singly-linked list with appropriate operations.
 *
 * @author:	Henry Leitner (and Anna Ntenta)
 * @version: Last modified on April 25, 2014
 * Implements a double-ended queue as a linked-list
 */

class DequeueUnderFlowException extends Exception {}

public class LinkedDequeue
{
    private QueueNode rear;
    private QueueNode front;
    private int count;
    
    /**
     *  The QueueNode class is an inner class implemented to model a queue node;
     *  it can contain an Object type of data, and also holds the link to the
     *  next node in the queue.  If there are no other nodes, the link will be null.
     */
     class QueueNode        // an inner class
     {
	   private Object item;
	   private QueueNode link;
     }

    /**
     *  This constructor for the class will set up the needed instance variables
     *  which begin with no nodes present and thus are set to null.
     */
    public LinkedDequeue ()
    {
	   rear = front = null;
	   count = 0;
    }

    /**
     *  This method will construct a new QueueNode and add it onto the head
     *  of the queue. If it is the first node added into
     *  the queue, both front and rear will reference it, otherwise it is added
     *  using the front variable.  The node counter is also updated.
     *
     *  @param   o     The Object to be added as part of a new QueueNode
     */
    public void headAdd (Object o)
    {
    	QueueNode temp = new QueueNode();
    	temp.item = o;
    	temp.link = null;
    	
    	if (front == null) front = rear = temp;
    	else
	   {
    	    temp.link = front;
    	    front = temp;
	   }
	   count++ ;
    }

    /**
     *  This method will return the value at the head of the queue.
     *
     *  @return     The value of the head node
     */
    public Object headPeek() throws DequeueUnderFlowException
    {
        if (isEmpty()) throw new DequeueUnderFlowException();
        return front.item;
    }

    /**
     *  This method will remove an item from the front of the queue.  
     *  In doing so, the queue variables are reset to detach the node,
     *  and the Object which it contains is then returned.  The queue
     *  counter is also updated to reflect the removal.
     *
     *  @return     The Object which was just removed from the queue.
     */
   public Object headRemove () throws DequeueUnderFlowException
   {
    if (isEmpty()) throw new DequeueUnderFlowException();
    else
    {
        Object tempItem = front.item;
        front = front.link;
        if (front == null)   rear = null;
        count -- ;
        return tempItem;
    }
   }

   /**
     *  This method will construct a new QueueNode and add it onto the rear
     *  of the queue (standard FIFO behavior). If it is the first node added into
     *  the queue, both front and rear will reference it, otherwise it is added
     *  using the rear variable.  The node counter is also updated.
     *
     *  @param   o     The Object to be added as part of a new QueueNode
     */
    public void tailAdd (Object o)
    {
        QueueNode temp = new QueueNode();
        temp.item = o;
        temp.link = null;
        
        if (rear == null) front = rear = temp;
        else
        {
            rear.link = temp;
            rear = temp;
        }
        count++ ;
    }

    /**
     *  This method will return the value at the tail of the queue.
     *
     *  @return     The value of the tail node
     */
    public Object tailPeek () throws DequeueUnderFlowException
    {
        if (isEmpty()) throw new DequeueUnderFlowException();
        return rear.item;
    }

    /**
     *  This method will remove an item from the end of the queue.  
     *  In doing so, the queue variables are reset to detach the node,
     *  and the Object which it contains is then returned.  The queue
     *  counter is also updated to reflect the removal.
     *
     *  @return     The Object which was just removed from the queue.
     */
    public Object tailRemove () throws DequeueUnderFlowException
    {
        if (isEmpty()) throw new DequeueUnderFlowException();
        // if we're removing the last element, make the queue empty
        if ( count == 1) {
            QueueNode temp = new QueueNode();
            temp = front;
            front = null;

            count --;

            return temp;
        }
        else {
            QueueNode current = front;
            while (current.link.link != null) {
                current = current.link;
            }
        
            QueueNode temp = new QueueNode();
            temp = rear;

            rear = current;
            rear.link = null;

            count -- ;
            return temp;
        }
    }

    /**
     *  Outputs all objects in the queue, from the head 
     *  through the tail, one per line
     *
     *  @return     a string representing all the objects in the queue
     */
    public String toString () {
        if (front == null) {
            return "[]";

        } else {
            String string = "" + front.item;
            QueueNode current = front.link;
            while (current != null) {
                string += "\n" + current.item;
                current = current.link;
            }
            return string;
        }
    }

    /**
     *  This method will test for an empty queue and return a boolean result.
     *
     *  @return     true for an empty list; false if the queue contains QueueNodes.
     */
    public boolean empty()
    {
	   return ( count == 0 );
    }

    /**
     *  This method returns true if Dequeue contains no elements; else it returns false
     *
     *  @return     true for an empty list; false if the queue contains QueueNodes.
     */
    public boolean isEmpty()
    {
        if (front == null) return true;
        else return false;
    }
   
    /**
     *  This method will evaluate and return the current size of the queue.
     *
     *  @return     An int describing the current number of nodes in the queue
     */
    public int size()
    {
	   return count;
    }


    public static void main (String [] args) 
    {
        try {
            LinkedDequeue dequeue = new LinkedDequeue();

            System.out.println("\n1) Lets add the words \"To be\" to the queue:\n");

            dequeue.headAdd("To");
            dequeue.tailAdd("be");

            System.out.println(dequeue + "\n");

            System.out.println("The head contains: \"" + dequeue.headPeek() + "\"\nNumber of elements in queue: " + dequeue.size() );

            String empty;
            if (dequeue.isEmpty()) {
                empty = "TRUE";
            }  else {
                empty = "FALSE";
            }

            System.out.println("It is " + empty + " that this queue is empty.\n");

            System.out.println("2) Let's remove the head.\n"); 
            dequeue.headRemove();

            System.out.println("The number of elements in the queue is now: " + dequeue.size() );

            System.out.println("The queue now contains the following:\n");
            System.out.println(dequeue + "\n");

            System.out.println("3) Let's peek at the tail: It is \"" + dequeue.tailPeek() + "\"");
            System.out.println("3) Let's peek at the head: It is \"" + dequeue.headPeek() + "\"\n");

            System.out.println("Let's remove the tail...");

            dequeue.tailRemove();

            System.out.println("Done! Now the queue contains the following: ");
            System.out.println(dequeue + "\n");
            if (dequeue.isEmpty()) {
                empty = "TRUE";
            }  else {
                empty = "FALSE";
            }
            System.out.println("It is " + empty + " that this queue is empty.\n");
        }
        catch(DequeueUnderFlowException e) {
             System.out.println("\n** ERROR ** Attempt to peek at or remove item from empty dequeue.\n");
        }
    }  
}