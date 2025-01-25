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
		Node temp = first;
		for(int i = 0; i < index ; i++)
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
		if(index == size || size == 0)
		{
			this.addLast(block);
			return;
		}
		if(index == 0)
		{
			this.addFirst(block);
			return;
		}
		Node add = new Node(block);
		Node temp = this.first;
		for(int i = 0; i < index-1 ; i++)
			temp = temp.next;
		add.next = temp.next;
		temp.next = add;
		size++;
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
		Node add = new Node(block);
		add.next = first;
		first = add;
		size++;
		if (last == null)
			last = first;
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
		Node temp = this.first;
		int indexOf = 0;
		while (temp != null) {
			if (temp.block.equals(block)) 
				return indexOf;
			temp = temp.next;
			indexOf++;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		int indexOf = this.indexOf(node.block);
		if (indexOf == -1) {
			return;
		}
		this.remove(indexOf);
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index >= this.size || 0 > index) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		if (index == 0) {
			this.first = this.first.next;
			if (this.size == 1) {
				this.last = null;
			}
			this.size--;
			return;
		}
		Node temp = this.first;
		for (int i = 0; i < index - 1; i++) {
			temp = temp.next;
		}
		temp.next = temp.next.next; 
		this.size--;
		if (index == this.size)
			this.last = temp;
		return;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int indexOf = this.indexOf(block);
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
		String returnUser = "";
		Node temp = this.first;
		while (temp != null) {
			returnUser += temp.block.toString() + " ";
			temp = temp.next;
		}
		return returnUser;
	}
}