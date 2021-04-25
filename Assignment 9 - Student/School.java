//Merwan Malakapalli
//Computer Science III
//This program creates schedules for two students and checks their shared classes
//School.java
//02/06/2020

import java.util.*;

public class School
{
   /**
   *The maximum amount of courses that can be taken
   */
   public static final int MAX_COURSES = 8;
   
   /**
   *Creates and compares the schedules of two students
   */
   public static void main(String[] theory)
   {
      Scanner input = new Scanner(System.in);
      Student s1 = getStudent(input);
      add(s1, input);
      System.out.println();
      Student s2 = getStudent(input);
      add(s2, input);
      System.out.println();
      
      System.out.println("Student " + s1.getFullName() + " is taking courses " + Arrays.toString(s1.getCourses()));
      System.out.println("Student " + s2.getFullName() + " is taking courses " + Arrays.toString(s2.getCourses()));
      
      String[] shared = sharedClasses(s1, s2);
      int together = Integer.parseInt(shared[shared.length-1]);
      
      if(together == 1)
      {
         System.out.println("The students have the following class in common:");
      }
      
      else if(together > 1)
      {
         System.out.println("The students have the following classes in common:");
      }
      
      else
      {
         System.out.println("The students have no shared classes.");
      }
      for(int i = 0; i < shared.length-1; i++)
      {
         if(shared[i] != null)
         {
            int period = i+1;
            System.out.println("   " + period + " - " + shared[i]);
         }
      }
   }
   
   /**
   *Constructs one new student with user input
   *
   *@param input the scanner to take user input
   *@return the newly constructed Student
   */
   public static Student getStudent(Scanner input)
   {  
      String name = MyUtils.getString(input, "What is the student's name (firstname lastname)? ");
      String firstName = "";
      String lastName = "";
      //If there are no spaces in the name 
      if(name.indexOf(" ") == -1)
      {
         firstName = name;
      }
      
      else
      {
         firstName = name.substring(0, name.indexOf(" "));
         lastName = name.substring(name.indexOf(" ") + 1, name.length());
      }
      
      //9 and 12 are lowest and highest grades in a high school
      int grade = MyUtils.getNumber(input, "What is the student's grade (9-12)? ", 9, 12);  
      return new Student(firstName, lastName, grade, MAX_COURSES);
   }
   
   /**
   *Adds courses to the student's schedule based on user input
   *
   *@param s the student to add courses to
   *@param input the scanner to take user input
   */
   public static void add(Student s, Scanner input)
   {
      int coursesTaking = MyUtils.getNumber(input, "How many courses is this student taking (1-8)? ", 1, 8);
      
      for(int i = 0; i < coursesTaking; i++)
      {
         while(true)
         {
            int currentPeriod = i+1;
            String course = MyUtils.getString(input, "Course " + currentPeriod + " name? ");
            int period = MyUtils.getNumber(input, "Period for " + course + " (1-8)? ", 1, 8);
            
            if(s.addCourse(course, period))
            {
               break;
            }
            System.out.println(s.getFullName() + " is already taking a course in period " + period);
         }
         
      }      
   }
   
   /**
   *Finds shared classes between two students
   *
   *@param stud1 the first student
   *@param stud2 the second student
   *@return the shared classes in the form of an array
   */
   public static String[] sharedClasses(Student stud1, Student stud2)
   {
      int numTogether = 0;
      //Plus one because I will also store the number of classes that they have together
      String[] same = new String[MAX_COURSES + 1]; 

      for(int i = 0; i < MAX_COURSES; i++)
      {
         if(stud1.getCourses()[i].equals(stud2.getCourses()[i]))
         {
            same[i] = stud1.getCourses()[i];
            numTogether++;
         }
      }
      same[MAX_COURSES] = Integer.toString(numTogether);
      return same;     
   }
}