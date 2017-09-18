/*
 * MyGenealogy.java
 * Emily Van Laarhoven
 * CS230 Assign 8 Task 2b
 * Due: 11/21/16 11:59pm
 */

public class MyGenealogy {
  
  public static void main (String [] args) {
     GenealogyTree<String> family = new GenealogyTree<String>("me");
     family.setPater("me","dad");
     family.setMater("me","mom");
     family.setPater("dad","Pop");
     family.setMater("dad","Nanny Re");
     family.setPater("mom","Grandpa");
     family.setMater("mom","Grandma");
     family.setMater("Nanny Re","Lil");
     family.setPater("Nanny Re","Amby");
     family.setPater("Pop","Will");
     family.setMater("Pop","Jean");
     family.setPater("Grandma","John");
     family.setMater("Grandma","Julia");
     family.setMater("Grandpa","Ginny");
     family.setPater("Grandpa","Joe");
     System.out.println(family.toString());
     System.out.println("Who is at the root?");
     System.out.println(family.getCoTU());
     System.out.println("Who are my parents?");
     System.out.println(family.getPater("me"));
     System.out.println(family.getMater("me"));
     System.out.println("Who is mom's offspring?");
     System.out.println(family.getOffspring("dad"));
     System.out.println("Who is dad's offspring?");
     System.out.println(family.getOffspring("mom"));
     System.out.println("My maternal grandmother is "+family.getMater(family.getMater("me")));
     System.out.println("My maternal grandfather is "+family.getMater(family.getPater("me")));
     System.out.println("My paternal grandmother is "+family.getPater(family.getMater("me")));
     System.out.println("My paternal grandfather is "+family.getPater(family.getPater("me")));
     System.out.println("My maternal great-grandmother is "+family.getMater(family.getMater(family.getMater("me"))));
     System.out.println("My paternal great-grandmother is "+family.getMater(family.getMater(family.getPater("me"))));
     System.out.println("My maternal great-grandfather is "+family.getPater(family.getMater(family.getMater("me"))));
     System.out.println("My paternal great-grandfather is "+family.getPater(family.getMater(family.getPater("me"))));
     System.out.println("Are Pop and Grandma in-laws? (true)");
     System.out.println(family.inLaws("Pop","Grandma"));
     System.out.println("Are Amby and Ginny in-laws? (false)");
     System.out.println(family.inLaws("Amby","Ginny"));
     System.out.println("Are Nanny Re and Grandpa in-laws? (true)");
     System.out.println(family.inLaws("Nanny Re","Grandpa"));

    
  }
  
}