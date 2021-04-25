//Merwan M
//Computer Science III
//This program creates a student
//Student.java
//02/06/2020

import java.util.*;

public class Student
{
   private String firstName;
   private String lastName;
   private int grade;
   private int max;
   private String[] courses;
   
   /**
   *The default value if there is no class in a period
   */
   public static String FREE = "Free";
   
   /**
   *The lowest possible grade that a student could be in
   */
   public static int lowestGrade = 1;
   
   /**
   *The highest possible grade that a student could be in
   */
   public static int highestGrade = 12;
   
   /**
   *Constructs the Student object
   *
   *@param first the first name of student
   *@param last the last name of student
   *@param grade the grade of student
   *@param max the max number of courses that could be taken
   */
   public Student(String first, String last, int grade, int max)
   {
      if(first == null || last == null || grade < lowestGrade || grade > highestGrade || max <= 0)
      {
         throw new IllegalArgumentException();
      }
      
      this.firstName = first;
      this.lastName = last;
      this.grade = grade;
      this.max = max;
      courses = new String[max];
      for(int i = 0; i < max; i++)
      {
         courses[i] = FREE;
      }
   }
   
   /**
   *Gets the first name of the student
   *
   *@return the first name of the student
   */
   public String getFirstName()
   {
      return this.firstName;
   }
   
   /**
   *Gets the last name of the student
   *
   *@return the last name of the student
   */
   public String getLastName()
   {
      return this.lastName;
   }
   
   /**
   *Gets the full name of the student
   *
   *@return the full name of the student
   */
   public String getFullName()
   {
      if(this.firstName.equals(""))
      {
         return this.lastName;
      }
      
      else if(this.lastName.equals(""))
      {
         return this.firstName;
      }
      
      return this.firstName + " " + this.lastName;
   }
   
   /**
   *Gets the grade of the student
   *
   *@return the grade of the student
   */
   public int getGrade()
   {
      return this.grade;
   }
   
   /**
   *Sets the grade of the student to any desired grade
   *
   *@param grade the new grade of the student
   */
   public void setGrade(int grade)
   {
      if(grade <= 0)
      {
         throw new IllegalArgumentException();
      }
      this.grade = grade;
   }
   
   /**
   *Gets the courses of the student
   *
   *@return the array representing the courses that the student is taking
   */
   public String[] getCourses()
   {
      return this.courses;
   }
   
   /**
   *Gets the number of courses of the student
   *
   *@return the number of courses of the student
   */
   public int getCourseCount()
   {
      int totalCourses = 0;
      for(int i = 0; i < max; i++)
      {
         if(!this.courses[i].equals("Free"))
         {
            totalCourses++;
         }
      }
      return totalCourses;
   }
   
   @Override
   public String toString()
   {
      return this.grade + ": " + getFullName() + " " + Arrays.toString(this.courses);
   }
   
   @Override
   public boolean equals(Object o)
   {
      if(o instanceof Student)
      {
         Student newStud = (Student) o;
         
         return this.firstName.equals(newStud.firstName) && this.lastName.equals(newStud.lastName) && this.grade == newStud.grade && Arrays.equals(this.courses, newStud.courses);
      }
      return false;
   }
   
   /**
   *Adds a course to the student's courselist
   *
   *@param course the name of the course to add
   *@param period the period to add the course to
   *@return whether the course was successfully added
   */
   public boolean addCourse(String course, int period)
   {
      for(int i = 0; i < this.max; i++)
      {
         //Index starts at 0
         if(i == period-1 && this.courses[i].equals(FREE) && course != null && !course.equals("") && period >= 1 && period <= max)
         {
            this.courses[i] = course;
            return true;
         }
      }
      return false;
   }
}