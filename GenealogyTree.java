/*
 * GenealogyTree.java
 * Emily Van Laarhoven
 * CS230 PSet 8 Task 2
 * Due: 11/21/16 11:59pm
 */

import java.util.Iterator;
import javafoundations.ArrayIterator;
import javafoundations.exceptions.*;


public class GenealogyTree<T> implements AncestryTree<T>, Iterable<T> {
  
  //instance variables
  private int DEFAULT_CAPACITY = 10;
  private T[] nodes;  //array of type T for computed links 
  private int count; //total number of nodes in tree
  private int current;  //index of the current position (in iteration)
   
   /*
    * helper method -
    * returns the index of the target element; if not found, returns -1
    */
   private int findIndex(T target) {
     for (int i=0; i<count; i++) {
       if (nodes[i].equals(target)) {
         return i;
       }
     }
     return -1;
   }
   
   //helper method - double size of array
   private void expandCapacity() {
      T[] larger = (T []) (new Object[nodes.length*2]);
      for (int i=0; i<nodes.length; i++) {
         larger[i] = nodes[i];
      }
      nodes = larger;
   }
   
  //----------------Ancestry tree interface--------------------------
  
  // Create a 1-argument constructor that sets the root of a new tree
   public GenealogyTree (T root) {
     nodes = (T[]) new Object[DEFAULT_CAPACITY];
     nodes[0] = root;
     count =1;
     current=0;
   }
  
  //  Returns the element stored in the root (aka CoTU) of the tree.
   public T getCoTU(){ //Center of The Universe
     return nodes[0];     
   }
  
  //  Returns the member that is the offspring of target.
  //  Returns null as the offspring of the root.
   public T getOffspring(T target){ // This is "parent" in tree terminology
     int targetIndex = findIndex(target); //assuming that target is actually in tree
     if (!target.equals(this.getCoTU()) && targetIndex>-1) {
     T offspring = null;
     if (targetIndex%2==0) { //if target index is even target is right node
       offspring = nodes[(targetIndex-2)/2];
     } else if (targetIndex%2==1) { //if target index is odd target is left node
       offspring = nodes[(targetIndex-1)/2];
     } 
     return offspring;
     } else throw new ElementNotFoundException("This parent has no offspring");
   }
  
  //  Returns the member that is the left child of target.
  //  Returns null if the left child does not exist.
   public T getPater(T target){ // Paternal ancestors are on the left
     int targetIndex = findIndex(target);
     return nodes[2*targetIndex+1];
   }
  
  //  Sets the left child of the tree target to lchild.
  //  It throws an exception if target is not already in the tree
   public void setPater(T target, T lchild) {
     int targetIndex = findIndex(target);
     int childIndex = 2*targetIndex+1;
     if (childIndex>=nodes.length) {
       expandCapacity();
     }
     nodes[childIndex] = lchild;
     count++;
   }
  
  //  Returns the element that is the right child of target.
  //  Returns null if the right child does not exist
  public T getMater(T target){ // Maternal ancestors are on the right
    int targetIndex = findIndex(target);
    return nodes[2*targetIndex+2];
  }
  
  //  Sets the right child of target to rchild.
  //  It throws an exception if target is not already in the tree
  public void setMater(T target, T rchild) {
    int targetIndex = findIndex(target);
    int childIndex = 2*targetIndex+2;
    if (childIndex>=nodes.length) {
      expandCapacity();
    }
    nodes[childIndex] = rchild;
    count++;
   }

  //  Returns true if the tree contains an element that
  //  matches the specified target element and false otherwise.
  public boolean appears (T target) {
    return (!(findIndex(target)==-1));
  }
    
  // Returns true if the two members share a grandchild,
  // and false if they are not or if a shared grandchild does not exist
  // Two grandparents that share a grandchild are "inLaws"
  public boolean inLaws(T gp1, T gp2){
    T gchild1 = getOffspring(getOffspring(gp1)); //get grandchildren
    T gchild2 = getOffspring(getOffspring(gp2));
    return (gchild1.equals(gchild2)); //same grandchild?
  }
    
  //  Returns the number of members in this ancestral tree.
  public int size() {
    return count;
  }
  
  //  Returns the string representation of the binary tree, one line per level.
  public String toString(){
    String s = "My genealogy contains "+count+" members: \n";
    Iterator <T> iter = this.byGenerations(); //uses byGenerations() so need to make that work!
    while (iter.hasNext()) {
      s+=iter.next()+"\n";
    }
    return s;
  }
  
  //  Returns an iterator that contains a level-order traversal
  // on the ancestral tree.
  public Iterator<T> byGenerations() {
    ArrayIterator<T> iter = new ArrayIterator<T>();
    while (current < count) {  
          if (!nodes[current].equals(null)){
            iter.add(nodes[current]);
          }
          current++;
    }
    return iter;
  }

    // this class needs to implement Iterable<T> so needs iterator() method - how to do?
    public Iterator<T> iterator() {
      return this.byGenerations();
    }
   
   public static void main (String [] args) {
     GenealogyTree<String> test1 = new GenealogyTree<String>("me");
     System.out.println("Who is the center of the universe?");
     System.out.println(test1.getCoTU());
     test1.setPater("me","dad");
     test1.setMater("me","mom");
     System.out.println("Who are my parents?");
     System.out.println(test1.getPater("me"));
     System.out.println(test1.getMater("me"));
     System.out.println("Who is mom's offspring?");
     System.out.println(test1.getOffspring("dad"));
     System.out.println("Who is dad's offspring?");
     System.out.println(test1.getOffspring("mom"));
     test1.setPater("dad","Pop");
     test1.setMater("dad","Nanny Re");
     test1.setPater("mom","Grandpa");
     test1.setMater("mom","Grandma");
     System.out.println("Are Pop and Grandma in-laws? (true)");
     System.out.println(test1.inLaws("Pop","Grandma"));
     System.out.println("Are Nanny Re and Grandpa in-laws? (true)");
     System.out.println(test1.inLaws("Nanny Re","Grandpa"));
//     System.out.println("Are Grandpa and I in-laws? (false)");
//     System.out.println(test1.inLaws("me","Grandpa")); //throws exception
     System.out.println(test1.toString());
   
   }
   
}