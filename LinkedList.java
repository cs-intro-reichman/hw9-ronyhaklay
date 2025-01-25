/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) 
			throw new IllegalArgumentException("index must be between 0 and size");
		Node temp = this.first;
		for(int i = 0; i < index ; i++)
			if (temp != null) 
			    temp = temp.next;
		return temp;
	}
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if(index < 0 || index > size)
			throw new IllegalArgumentException("index must be between 0 and size");
		Node AnotherNode = new Node(block);
		if (index == 0) 
			addFirst(block);
		else if (index == size) 
			addLast(block);
		else
		{
			Node prev = getNode(index - 1);
			AnotherNode.next = prev.next;
			prev.next = AnotherNode; 
			this.size ++;
		}
	}
	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		if (block == null)
			return;
		if (this.last == null)
		{
			last = new Node(block);
			first = last;
			size++;
			return;
		}
		last.next = new Node(block);
		last = last.next;
		size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node AnotherNode = new Node(block);
		if (size == 0) {
			first = AnotherNode;
			last = AnotherNode;
		} 	
		else {
			AnotherNode.next = first;
			first = AnotherNode;
		}
		size ++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index >= this.size || 0 > index)
			throw new IllegalArgumentException("index must be between 0 and size");
		Node temp = this.first;
		for (int i = 0; i < index; i++)
			temp = temp.next;
		return temp.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		int index = -1;
		for (int i = 0; i < this.size; i++) {
			if (block == getBlock(i)) {
				index = i;
				break;
			}
		}
		return index;
	}
	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if (node == null) 
			throw new NullPointerException("Node to remove cannot be null");
		if (this.first == node) { 
			this.first = this.first.next;
			if (this.first == null) 
				this.last = null;
		} 
		else {
			Node temp = this.first;
			while (temp != null && temp.next != node) 
				temp = temp.next;
			if (temp != null) {
				temp.next = node.next;
				if (temp.next == null) 
					this.last = temp;
			}
		}
		this.size--;
	}
	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index >= size) 
			throw new IllegalArgumentException("index must be between 0 and size");
		remove(getNode(index));
	}
	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		if (block == null) 
			throw new IllegalArgumentException("index must be between 0 and size");
		int indexOf = this.indexOf(block);
		if (indexOf == -1)
			throw new IllegalArgumentException("index must be between 0 and size");
		this.remove(indexOf);
	}
	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
			StringBuilder result = new StringBuilder();
			Node temp = this.first;
			while (temp != null) {
				result.append(temp.block.toString()).append(" ");
				temp = temp.next;
			}
		return result.toString();
	}
}