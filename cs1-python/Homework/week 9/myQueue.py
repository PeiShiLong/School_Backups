from myNode import *

class Queue():
    __slots__ = ( 'front', 'back', 'size' )
    
def mkQueue():
    q = Queue()
    q.front = NONE_NODE
    q.back = NONE_NODE
    q.size = 0
    return q



def enqueue(queue, element):
    """Insert an element into the back of the queue"""
    newnode = mkNode(element, NONE_NODE)
    if emptyQueue(queue):
        queue.front = newnode
    else:
        queue.back.next = newnode
    queue.back = newnode

    
    queue.size += 1





    
def dequeue(queue):
    """Remove the front element from the queue (returns None)"""
    if emptyQueue(queue):
        raise IndexError("dequeue on empty queue") 
    queue.front = queue.front.next
    if emptyQueue(queue):
        queue.back = NONE_NODE
    queue.size -= 1
    
def front(queue):
    """Access and return the first element in the queue without removing it"""
    if emptyQueue(queue):
        raise IndexError("front on empty queue") 
    return queue.front.data
    
def back(queue):
    """Access and return the last element in the queue without removing it"""
    if emptyQueue(queue):
        raise IndexError("back on empty queue") 
    return queue.back.data
    
def emptyQueue(queue):
    """Is the queue empty?"""
    return queue.front == NONE_NODE 
