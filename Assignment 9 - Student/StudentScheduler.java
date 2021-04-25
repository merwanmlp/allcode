//Merwan M
//Computer Science III
//This program asks for the classes of two students and prints their schedules and what they have in common
//StudentScheduler.java
//01/14/2020

import java.util.*;

public class StudentScheduler
{
   /**
   *The maximum number of classes that could be taken
   */
   private int maxClasses;
   private String name;
   private String[] classes;
   
   public StudentScheduler(String name, String[] classes, int maxClasses)
   {
      this.name = name;
      this.classes = classes;
      this.this.maxClasses = this.maxClasses;
   }
   
   public void printArray()
   {
      System.out.print("Student " + this.name + " is taking courses [");
      for(int i = 0; i < this.maxClasses; i++)
      {
         if(i == this.maxClasses-1)
         {
            System.out.print(this.classes[i] + "]");
         }
         System.out.print(this.classes[i] + ", ");
      }
   }
   
   public String[] classes(Scanner in)
   {
      System.out.print("What is the student's name (firstname lastname)? ");
      String student = in.nextLine();
      int grade = MyUtils.getNumber(in, "What is the student's grade (9-12)? ", 9, 12);
      int numCourses = MyUtils.getNumber(in, "How many courses is this student taking (1-8)? ", 1, 8);
      
      String[] classes = new String[this.maxClasses + 1]; //To account for the student name
      classes[0] = student;
      
      for(int i = 1; i < this.maxClasses+1; i++)
      {
         classes[i] = "Free";
      }
      
      for(int i = 1; i < numCourses+1; i++) //Extra 1 added so it starts off with Course 1 and not Course 0
      {
         System.out.print("Course " + i + " name? ");
         String coursename = in.nextLine();
         int period = MyUtils.getNumber(in, "Period for " + coursename + " (1-8)? ", 1, 8);
         classes[period] = coursename;
      }
      System.out.println();
      return classes;
   }
   
   public void sameClasses(String[] arr1, String[] arr2)
   {
      int numClasses = 0;
      boolean together = false;
      int numTogether = 0;
      String[] same = new String[this.maxClasses];
      for(int i = 0; i < this.maxClasses; i++)
      {
         if(arr1[i].equals(arr2[i]))
         {
            same[i] = arr1[i];
            together = true;
            numTogether++;
         }
      }
      
      if(together)
      {
         if(numTogether == 1)
         {
            System.out.println("The students have the following class in common:");
         }
         
         else
         {
            System.out.println("The students have the following classes in common:");
         }
         
         for(int i = 0; i < same.length; i++)
         {
            if(same[i] != null)
            {
               System.out.println(i + " - " + same[i]);
            }
         }
      }
      
      else
      {
         System.out.println("The students have no shared classes.");
      }
   }
}