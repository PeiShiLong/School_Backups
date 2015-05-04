#!/usr/local/bin/python3

"""
Author: Jon O'Brien
Due Date: 11/9/13
Assignment: linked list homework


File: testList.py
Author: Sean Strout <sps@cs.rit.edu>
Language: Python 3
Description:  A test module for our linked list data structure, MyList.
"""

# change to myListIter to test iterative version
from myListRec import *

def testAppendAndToString():
    print("Testing append and toString...")
    lstA = mkMyList()
    print(lstA.size == 0)
    print(toString(lstA) == '[]')
    append(lstA, 'a')
    print(lstA.size == 1)    
    print(toString(lstA) == '[a]')
    append(lstA, 'b')
    print(toString(lstA) == '[a, b]')
    print(lstA.size == 2)    
    append(lstA, 'c')
    print(toString(lstA) == '[a, b, c]')
    
def testClear():
    print("Testing clear...")
    lstA = mkMyList()
    clear(lstA)
    print(lstA.size == 0)
    print(toString(lstA) == '[]')
         


def testingInsert():
    print("Testing insert...")
    lstA = mkMyList()
    try:
        insertAt(lstA, -1, 'z')
    except IndexError:
        pass
    insertAt(lstA, 0, 'b')
    print(lstA.size == 1)
    print(toString(lstA) == '[b]')
    insertAt(lstA, 1, 'd')
    print(lstA.size == 2)
    print(toString(lstA) == '[b, d]')
    insertAt(lstA, 1, 'c') 
    print(lstA.size == 3)
    print(toString(lstA) == '[b, c, d]')
    insertAt(lstA, 0, 'a')
    print(lstA.size == 4)
    print(toString(lstA) == '[a, b, c, d]')
    insertAt(lstA, 4, 'e')
    print(lstA.size == 5)
    print(toString(lstA) == '[a, b, c, d, e]') 
    try:
        insertAt(lstA, 6, 'z')
    except IndexError:
        pass    
        
def testingGet():
    print("Testing get...")
    lstA = mkMyList()
    try:
        get(lstA, 0)
    except:
        pass
    print(lstA.size == 0)
    for ch in ['a','b','c','d']:
        append(lstA, ch)
    print(get(lstA, 0) == 'a')
    print(get(lstA, 1) == 'b')
    print(get(lstA, 2) == 'c')
    print(get(lstA, 3) == 'd')
    print(lstA.size == 4)
    try:
        get(lstA, 4)
    except:
        pass    
           
def testingSet():
    print("Testing set...")
    lstA = mkMyList()
    try:
        set(lstA, 0, 'z')
    except IndexError:
        pass
    append(lstA, 'a')
    set(lstA, 0, 'z')
    print(lstA.size == 1)
    print(toString(lstA) == '[z]')
    lstA = mkMyList()
    for ch in ['a','b','c','d','e','f']:
        append(lstA, ch)        
    set(lstA, 0, 'x')
    set(lstA, 2, 'y')
    set(lstA, 5, 'z')
    print(lstA.size == 6)
    print(toString(lstA) == '[x, b, y, d, e, z]')     
    try:
        set(lstA, 6, 'z')
    except IndexError:
        pass
    
def testingPop():
    print("Testing pop...")
    lstA = mkMyList()
    try:
        pop(lstA, 0)
    except IndexError:
        pass
    append(lstA, 'a')
    print(pop(lstA, 0) == 'a')
    print(lstA.size == 0)
    for ch in ['a','b','c','d','e','f']:
        append(lstA, ch)
    print(pop(lstA, 0) == 'a')
    print(lstA.size == 5)
    print(toString(lstA) == '[b, c, d, e, f]')
    print(pop(lstA, 1) == 'c')           
    print(lstA.size == 4)
    print(toString(lstA) == '[b, d, e, f]')
    print(pop(lstA, 3) == 'f') 
    print(lstA.size == 3)
    print(toString(lstA) == '[b, d, e]')
    try:
        pop(lstA, 3)
    except IndexError:
        pass
          
def testingIndex():
    print("Testing index...")
    lstA = mkMyList()
    try:
        index(lstA, 0)
    except:
        pass
    append(lstA, 'a')
    print(index(lstA, 'a') == 0)
    print(lstA.size == 1)
    for ch in ['b','c','d','a','b']:
        append(lstA, ch)
    print(index(lstA, 'a') == 0)
    print(index(lstA, 'b') == 1)
    print(index(lstA, 'c') == 2)
    print(index(lstA, 'd') == 3)
    print(lstA.size == 6)
    try:
        index(lstA, 6)
    except:
        pass
    
def testingPyListToMyList():
    print("Testing pyListToMyList...")
    print(toString(pyListToMyList([])) == '[]')
    print(toString(pyListToMyList(['a'])) == '[a]')
    print(toString(pyListToMyList(['a','b'])) == '[a, b]')
    print(toString(pyListToMyList(['a','b','c','d'])) == '[a, b, c, d]')

def testingMyListToPyList():
    print("Testing myListToPyList...")
    lstA = mkMyList()
    print(myListToPyList(lstA) == [])
    append(lstA, 'a')
    print(myListToPyList(lstA) == ['a'])
    append(lstA, 'b')
    print(myListToPyList(lstA) == ['a', 'b'])
    for ch in ['c','d','e','f']:
        append(lstA, ch)
    print(myListToPyList(lstA) == ['a', 'b', 'c', 'd', 'e', 'f'])
  
def testingCursor():
    print("Testing cursor functions: reset, hasNext, next...")
    lstA = mkMyList()
    reset(lstA)
    print(hasNext(lstA) == False)
    try:
        next(lstA)
    except IndexError:
        pass
    append(lstA, 'a')
    reset(lstA)
    print(hasNext(lstA) == True)
    print(next(lstA) == 'a')
    print(hasNext(lstA) == False)
    try:
        next(lstA)
    except IndexError:
        pass 
    append(lstA, 'b')
    append(lstA, 'c')
    reset(lstA)
    next(lstA)
    remove(lstA, 'b')
    print(hasNext(lstA) == False)
    reset(lstA)
    pop(lstA, 0)
    print(hasNext(lstA) == False)
    clear(lstA)
    for ch in ['a','b','c','d']:
        append(lstA, ch)
    reset(lstA)
    set(lstA, 0, 'x')
    print(next(lstA) == 'x')

def main():
    testAppendAndToString()
    testClear()
    testingInsert()
    testingGet()
    testingSet()
    testingPop()
    testingIndex()
    testingPyListToMyList()
    testingMyListToPyList()
    testingCursor()    
    
main()
