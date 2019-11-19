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
import java.util.NoSuchElementException;


/**
 * This hash table store the data effectively.
 * @author JOE
 *
 * @param <K> comparable data
 * @param <V> value data
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	
	private Object[] keyArray;				//Array that will store key
	private Object[] valueArray;			//Array that will store value
	private boolean[] existingArray;		//Array that will store existing value
	
	private int numKeys;					//Number of key
	private int arrayCapacity;				//The array(hashTable) size
	private double lFThreshold;				//Load factor threshold
	private double lF;						//Load factor
	
		
	
	/**
	 * This constructor get no argument then initiate hashtable
	 */
	public HashTable() {
		//Assign table size
		this.arrayCapacity = 7;
		//Set threshold
		this.lFThreshold=0.6;
		//Set loadfactor
		this.lF=0;
		//Set each array
		this.keyArray= new Object[arrayCapacity];
		this.valueArray= new Object[arrayCapacity];
		this.existingArray= new boolean[arrayCapacity];
		//Set the all element in the existing array false
		for(int index =0;index<arrayCapacity;index++)
		{
			existingArray[index]=false;
		}
		//Set number of keys
		numKeys=0;
	}
	
	/**
	 * This method get initial capacity and threshold of hash table
	 * and then initiate the class
	 * @param initialCapacity the size of hash table
	 * @param loadFactorThreshold threshold of hash table
	 */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		//Set the array capacity and threshold
		this.arrayCapacity= initialCapacity;
		this.lFThreshold=loadFactorThreshold;
		//Set the loadfactor zero
		this.lF=0;
		//Set each array
		this.keyArray= new Object[arrayCapacity];
		this.valueArray= new Object[arrayCapacity];
		this.existingArray= new boolean[arrayCapacity];
		for(int index =0;index<arrayCapacity;index++)
		{
			existingArray[index]=false;
		}
		//Set the number of key
		numKeys=0;

	}
	/**
	 * This method get key and then return hash index
	 * @param key key data
	 * @return hash index
	 */
	private int getHashIndex(K key) 
	{
		//Get integer from hashCode method
		int hashCode=Math.abs(key.hashCode());
		//Return mod hashtable size
		return hashCode%arrayCapacity;
	}
	/**
	 * check whether key is exist in the hashtable
	 * @param key data
	 * @return true when exist false otherwise
	 */
	public boolean contain(K key) 
	{
		//Get hash index
		int hashIndex=getHashIndex(key);
		//Integer for quadratic probing
		int n=1;
		//while the hashtable element is empty
		while(keyArray[hashIndex]!=null)
		{
			//If we find the element
			if(((K)keyArray[hashIndex]).compareTo(key)==0&&existingArray[hashIndex]==true)
			{
				//REturn true
				return true;
			}
			//Otherwise quadratic probing
			hashIndex=hashIndex+(int)Math.pow(n,2);
			hashIndex=hashIndex%this.arrayCapacity;
			//Increment the integer for probing
			n=n+1;
		}
		//while loop fail to find
		return false;
	}
	/**
	 * put key and data into hash table
	 * @param key data
	 * @param value data
	 * @throw IllegalNullKeyException when key is null
	 * @throw DuplicateKeyException when same key is already in hashtable
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException 
	{
		//When key is null
		if(key==null)
		{
			//Throw right exception
			throw new IllegalNullKeyException();
		}
		//Get hashindex
		int hashIndex=getHashIndex(key);
		//Check whether key already exist
		if(this.contain(key))
		{
			//Throw right exception
			throw new DuplicateKeyException();
		}
		//Check loadfactor
		this.lF=(double)numKeys/(double)arrayCapacity;
		//When loadfactor reach the threshold
		if(lF>=lFThreshold)
		{
			//Do rehash
			reHash();
		}
		//When hashtable position in hashindex is empty
		if(this.keyArray[hashIndex]==null)
		{
			//Assign key and value pair
			keyArray[hashIndex]=key;
			valueArray[hashIndex]=value;
			//SEt the array element true
			existingArray[hashIndex]=true;
		}
		//When collision occur
		else
		{
			//Set the integer to quadratic probing
			int n = 1;
			//Until while loop find empty element
			while(keyArray[hashIndex]!=null&&existingArray[hashIndex]==true)
			{
				//Quadratic probing
				hashIndex=hashIndex+(int)Math.pow(n,2);
				hashIndex=hashIndex%this.arrayCapacity;
				//Increment integer for probing
				n=n+1;
			}
			//Set the key value pair
			keyArray[hashIndex]=key;
			valueArray[hashIndex]=value;
			//SEt the arrayelement true
			existingArray[hashIndex]=true;
		}
		//Increment the number of keys
		this.numKeys++;
	}
	/**
	 * Rehash the hash table
	 */
	private void reHash()
	{
		//Store previous table size
		int prevCapacity=this.arrayCapacity;
		//Assign extended table size
		int newArrayCapacity=2*arrayCapacity+1;
		this.arrayCapacity=newArrayCapacity;
		//Assign new load factor
		this.lF=(double)numKeys/(double)arrayCapacity;
		//Initiate new arrays
		Object[] newKeyArray= new Object[arrayCapacity];
		Object[] newValueArray= new Object[arrayCapacity];
		boolean[] newExistingArray=new boolean[arrayCapacity];
		//For loop to set all elements in new existing array false
		for(int existingIndex=0;existingIndex<arrayCapacity;existingIndex++)
		{
			newExistingArray[existingIndex]=false;
		}
		//For loop to traverse previous hash table
		for(int index=0;index<prevCapacity;index++)
		{
			//If hashtable element is not empty and really exist
			if(this.keyArray[index]!=null&&this.existingArray[index]==true)
			{
				//Get new hashIndex 
				int newHashIndex=getHashIndex((K)keyArray[index]);
				//If new key array element is empty
				if(newKeyArray[newHashIndex]==null)
				{
					//Assign values
					newKeyArray[newHashIndex]=keyArray[index];
					newValueArray[newHashIndex]=valueArray[index];
					newExistingArray[newHashIndex]=existingArray[index];
				}
				//Otherwise
				else
				{
					//Set integer for quadratic probing
					int n=1;
					//While it meet empty element
					while(newKeyArray[newHashIndex]!=null)
					{
						//Do quadratic probing
						newHashIndex=newHashIndex+(int)Math.pow(n,2);
						newHashIndex=newHashIndex%newArrayCapacity;
						//Increment for probing
						n=n+1;
					}
					//Assing values
					newKeyArray[newHashIndex]=keyArray[index];
					newValueArray[newHashIndex]=valueArray[index];
					newExistingArray[newHashIndex]=existingArray[index];
				}
			}
			
		}
		//Assign new arrays to instance fields
		this.keyArray = newKeyArray;
		this.valueArray= newValueArray;	
		this.existingArray=newExistingArray;
		
	}
	/**
	 * remove key value data from hash table
	 * @param key data
	 * @throw IllegalNullKeyException when key is null
	 * @return true when it success to remove false otherwise
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		//When key is null
		if(key==null)
		{
			//Throw right exception
			throw new IllegalNullKeyException();
		}
		//When the key is not exist
		if(!(this.contain(key)))
		{
			//Return false
			return false;
		}
		//Get hash index
		int hashIndex=getHashIndex(key);
		//Set integer for probing
		int n = 1;
		//While it meet same key with real existence
		while(((K) this.keyArray[hashIndex]).compareTo(key)!=0||existingArray[hashIndex]==false)
		{
			//Qudatic probing
			hashIndex=hashIndex+(int)Math.pow(n,2);
			hashIndex=hashIndex%arrayCapacity;
			//Increment the integer for probing
			n=n+1;
		}
		//Set the existence false
		existingArray[hashIndex]=false;
		//Decrement the number of keys
		this.numKeys--;
		//Return true
		return true;
	}

	
	/**
	 * Get key parameter then the return the value for key
	 * @param key data
	 * @throw IllegalNullKeyException When key is null
	 * @throw KeyNotFoundException When key is not exist
	 * @return value data
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		//When key is null
		if(key==null)
		{
			//Throw right exception
			throw new IllegalNullKeyException();
		}
		//When key is not exist in hash table
		if(!(this.contain(key)))
		{
			//Throw right exception
			throw new KeyNotFoundException();
		}
		//Get hash index
		int hashIndex=getHashIndex(key);
		//SEt integer for probing
		int n=1;
		//while it found key with true existence
		while(((K) this.keyArray[hashIndex]).compareTo(key)!=0||existingArray[hashIndex]==false)
		{
			//Quadratic probing
			hashIndex=hashIndex+(int)Math.pow(n,2);
			hashIndex=hashIndex%arrayCapacity;
			//Increment for probing
			n=n+1;
		}
		//Return the value
		return (V) valueArray[hashIndex];
	}
	/**
	 * Return number of keys
	 * @return number of keys
	 */
	@Override
	public int numKeys() {
		// TODO Auto-generated method stub
		return this.numKeys;
	}
	/**
	 * Getter for load factor threshold
	 * @return load factor threshold
	 */
	@Override
	public double getLoadFactorThreshold() {
		// TODO Auto-generated method stub
		return this.lFThreshold;
	}
	/**
	 * Getter for load factor
	 * @return load factor
	 */
	@Override
	public double getLoadFactor() {
		// TODO Auto-generated method stub
		return (double)numKeys/(double)arrayCapacity;
	}

	/**
	 * Getter for array capacity
	 * @return the array capacity
	 */
	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return arrayCapacity;
	}
	/**
	 * Show what strategy i chose
	 * @return the strategy number
	 */
	@Override
	public int getCollisionResolution() {
		// TODO Auto-generated method stub
		return 2;
	}

		
}

