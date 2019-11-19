//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (Quiz Generator)
// Files:           (DataStructureADT.java,DuplicateKeyException.java,HashTable.java
//					HashTableADT.java,IllegalNullKeyException.java,KeyNotFoundException.java,
//					Main.java,QuestionNode.java,QuizControl.java)
// Course:          (CS400-S19-Lec001)
//
// Author:          (Hyung Rae Cho(X60), Gerrrard Kim(X54),Jeong Heo(X34),SangHyung Lee(X05))
// Email:           (hcho223@wisc.edu,hkim624@wisc.edu,jheo8@wisc.edu,lee866@wisc.edu)
// Lecturer's Name: (Debra Deppeler)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    ()
// Partner Email:   ()
// Partner Lecturer's Name: ()
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


package application;
/**
 * Filename:   HashTableADT.java
 * Project:    p3-201901
 * Course:     cs400
 * Authors:    Debra Deppeler
 * 
 * May use any of these Java's built-in Java collection types:
 * Arrays, List, ArrayList, LinkedList, Stack, Queue (interface), PriorityQueue, Deque 
 * 
 * May not use HashTable, TreeMap, HashMap, etc.
 * May not add any public members to ADT or your implementation.
 *
 * DO NOT EDIT THIS INTERFACE           
 */
public interface HashTableADT<K extends Comparable<K>, V> extends DataStructureADT<K,V> {

     // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
     public double getLoadFactorThreshold() ;

     // Returns the current load factor for this hash table
     // load factor = number of items / current table size 
     public double getLoadFactor() ;

     // Return the current Capacity (table size)
     // of the hash table array.
     //
     // The initial capacity must be a positive integer, 1 or greater
     // and is specified in the constructor.
     // 
     // REQUIRED: When the load factor threshold is reached, 
     // the capacity must increase to: 2 * capacity + 1
     //
     // Once increased, the capacity never decreases
     public int getCapacity() ;
    

     // Returns the collision resolution scheme used for this hash table.
     // Implement with one of the following collision resolution strategies.
     // Define this method to return an integer to indicate which strategy.
     //
      // 1 OPEN ADDRESSING: linear probe
      // 2 OPEN ADDRESSING: quadratic probe
      // 3 OPEN ADDRESSING: double hashing
      // 4 CHAINED BUCKET: array of arrays 
      // 5 CHAINED BUCKET: array of linked nodes
      // 6 CHAINED BUCKET: array of search trees
      // 7 CHAINED BUCKET: linked nodes of arrays
      // 8 CHAINED BUCKET: linked nodes of linked node
      // 9 CHAINED BUCKET: linked nodes of search trees
     public int getCollisionResolution() ;

}
