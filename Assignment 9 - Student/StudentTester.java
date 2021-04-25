//Merwan Malakapalli
//Computer Science III
//This program tests the student program
//StudentTester.java
//02/06/2020

public class StudentTester
{
   /**
   * Runs all tests, and outputs whether each test passed or failed
   *
   * @param args console input
   */
   public static void main(String[] args)
   {
      System.out.println(constructorTestStandard() ?
                        "Constructor: Standard Case Test Passed" : 
                        "Constructor: Standard Case Test FAILED");
      System.out.println(constructorTestEdge() ?
                        "Constructor: Edge Case Test Passed" : 
                        "Constructor: Edge Case Test FAILED");
                        
      System.out.println(getFirstNameTestStandard() ?
                        "getFirstName: Standard Case Test Passed" : 
                        "getFirstName: Standard Case Test FAILED");
      System.out.println(getFirstNameTestEdge() ?
                        "getFirstName: Edge Case Test Passed" : 
                        "getFirstName: Edge Case Test FAILED");
                        
      System.out.println(getLastNameTestStandard() ?
                        "getLastName: Standard Case Test Passed" : 
                        "getLastName: Standard Case Test FAILED");
      System.out.println(getLastNameTestEdge() ?
                        "getLastName: Edge Case Test Passed" : 
                        "getLastName: Edge Case Test FAILED");
                        
      System.out.println(getFullNameTestStandard() ?
                        "getFullName: Standard Case Test Passed" : 
                        "getFullName: Standard Case Test FAILED");
      System.out.println(getFullNameTestEdge() ?
                        "getFullName: Edge Case Test Passed" : 
                        "getFullName: Edge Case Test FAILED");
                        
      System.out.println(getGradeTestStandard() ?
                        "getGrade: Standard Case Test Passed" : 
                        "getGrade: Standard Case Test FAILED");
      System.out.println(getGradeTestEdge() ?
                        "getGrade: Edge Case Test Passed" : 
                        "getGrade: Edge Case Test FAILED");
                        
      System.out.println(getCoursesTestStandard() ?
                        "getCourses: Standard Case Test Passed" : 
                        "getCourses: Standard Case Test FAILED");
      System.out.println(getCoursesTestEdge() ?
                        "getCourses: Edge Case Test Passed" : 
                        "getCourses: Edge Case Test FAILED");
                        
      System.out.println(getCourseCountTestStandard() ?
                        "getCourseCount: Standard Case Test Passed" : 
                        "getCourseCount: Standard Case Test FAILED");
      System.out.println(getCourseCountTestEdge() ?
                        "getCourseCount: Edge Case Test Passed" : 
                        "getCourseCount: Edge Case Test FAILED");
      
      System.out.println(setGradeTestStandard() ?
                        "setGrade: Standard Case Test Passed" : 
                        "setGrade: Standard Case Test FAILED");
      System.out.println(setGradeTestEdge() ?
                        "setGrade: Edge Case Test Passed" : 
                        "setGrade: Edge Case Test FAILED");
                        
      System.out.println(addCourseTestStandard() ?
                        "addCourse: Standard Case Test Passed" : 
                        "addCourse: Standard Case Test FAILED");
      System.out.println(addCourseTestEdge() ?
                        "addCourse: Edge Case Test Passed" : 
                        "addCourse: Edge Case Test FAILED");
                        
      System.out.println(toStringTestStandard() ?
                        "toString: Standard Case Test Passed" : 
                        "toString: Standard Case Test FAILED");
      System.out.println(toStringTestEdge() ?
                        "toString: Edge Case Test Passed" : 
                        "toString: Edge Case Test FAILED");
                        
      System.out.println(equalsTestStandard() ?
                        "equals: Standard Case Test Passed" : 
                        "equals: Standard Case Test FAILED");
      System.out.println(equalsTestEdge() ?
                        "equals: Edge Case Test Passed" : 
                        "equals: Edge Case Test FAILED");
   }
   
   /**
   * Helper method which creates a student named Amanda O'Neal in 10th grade with 8 classes.
   *
   * @return the Student object for testing purposes
   */
   private static Student createStudent()
   {
      return new Student("Amanda", "O'Neal", 10, 8);
   }
   
   /**
   * Tests a standard case for the constructor.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean constructorTestStandard()
   {
      Student s = createStudent();
      
      boolean eightClasses = !s.addCourse("ThisNinthClassShouldReturnFalse", 9);
      
      return (s.getFullName().equals("Amanda O'Neal") 
            && s.getGrade() == 10
            && eightClasses);
   }
   
   /**
   * Tests an edge case for the constructor.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean constructorTestEdge()
   {
      try
      {
         Student s = new Student("Amanda", null, 10, 8);
         return false;
      }
      catch (IllegalArgumentException e)
      {
         return true;
      }
   }
   
   /**
   * Tests a standard case for the method getFirstName.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getFirstNameTestStandard()
   {
      Student s = createStudent();
      
      return s.getFirstName().equals("Amanda");
   }
   
   /**
   * Tests an edge case for the method getFirstName.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getFirstNameTestEdge()
   {
      Student s = new Student("", "Starr", 10, 8);
      return s.getFirstName().equals("");
   }
   
   /**
   * Tests a standard case for the method getLastName.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getLastNameTestStandard()
   {
      Student s = createStudent();
      
      return s.getLastName().equals("O'Neal");
   }
   
   /**
   * Tests an edge case for the method getLastName.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getLastNameTestEdge()
   {
      return new Student("Hello", "", 10, 8).getLastName().equals("");
   }
   
   /**
   * Tests a standard case for the method getFullName.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getFullNameTestStandard()
   {
      Student s = createStudent();
      
      return s.getFullName().equals("Amanda O'Neal");
   }
     
   /**
   * Tests an edge case for the method getFullName.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getFullNameTestEdge()
   {
      return new Student("", "", 10, 8).getFullName().equals("");
   }
   
   /**
   * Tests a standard case for the method getGrade.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getGradeTestStandard()
   {
      Student s = createStudent();
      
      return s.getGrade() == 10;
   }
   
   /**
   * Tests an edge case for the method getGrade.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getGradeTestEdge()
   {
      Student s = new Student("Merlin", "Barkley", 1, 8);
      
      return s.getGrade() == 1;
   }
   
   /**
   * Tests a standard case for the method getCourses.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getCoursesTestStandard()
   {
      Student s = createStudent();
      
      s.addCourse("Math", 1);
      s.addCourse("Science", 4);
      
      return java.util.Arrays.toString(s.getCourses()).equals("[Math, Free, Free, Science, Free, Free, Free, Free]");
   }
   
   /**
   * Tests an edge case for the method getCourses.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getCoursesTestEdge()
   {
      Student s = createStudent();
      s.addCourse("Free", 1);
      return java.util.Arrays.toString(s.getCourses()).equals("[Free, Free, Free, Free, Free, Free, Free, Free]");
   }
   
   /**
   * Tests a standard case for the method getCourseCount.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getCourseCountTestStandard()
   {
      Student s = createStudent();
      
      s.addCourse("Math", 2);
      s.addCourse("Science", 4);
      s.addCourse("Psychology", 6);
      return s.getCourseCount() == 3;
   }
   
   /**
   * Tests an edge case for the method getCourseCount.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean getCourseCountTestEdge()
   {
      Student s = createStudent();
      s.addCourse("Free", 1);
      return s.getCourseCount() == 0;
   }
   
   /**
   * Tests a standard case for the method setGrade.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean setGradeTestStandard()
   {
      Student s = createStudent();
      
      s.setGrade(9);
      
      return s.getGrade() == 9;
   }
   
   /**
   * Tests an edge case for the method setGrade.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean setGradeTestEdge()
   {
      Student s = createStudent();
      try
      {
         s.setGrade(-1);
      }
      
      catch(IllegalArgumentException e)
      {
         return true;
      }
      return false;
   }
   
   /**
   * Tests a standard case for the method addCourse.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean addCourseTestStandard()
   {
      Student s = createStudent();
      
      s.addCourse("Math", 1);
      
      if (!java.util.Arrays.toString(s.getCourses()).equals("[Math, Free, Free, Free, Free, Free, Free, Free]"))
      {
         return false;
      }
      
      s.addCourse("Science", 2);
      
      if (!java.util.Arrays.toString(s.getCourses()).equals("[Math, Science, Free, Free, Free, Free, Free, Free]"))
      {
         return false;
      }

      return true;
   }
   
   /**
   * Tests an edge case for the method addCourse.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean addCourseTestEdge()
   {
      Student s = createStudent();
      s.addCourse("Free", 8);
      if(!java.util.Arrays.toString(s.getCourses()).equals("[Free, Free, Free, Free, Free, Free, Free, Free]"))
      {
         return false;
      }
      
      s.addCourse("Free", 4);
      if(!java.util.Arrays.toString(s.getCourses()).equals("[Free, Free, Free, Free, Free, Free, Free, Free]"))
      {
         return false;
      }
      return true;
   }
   
   /**
   * Tests a standard case for the method toString.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean toStringTestStandard()
   {
      Student s = createStudent();
      
      return s.toString().equals("10: Amanda O'Neal [Free, Free, Free, Free, Free, Free, Free, Free]");
   }
   
   /**
   * Tests an edge case for the method toString.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean toStringTestEdge()
   {
      Student s = new Student("", "", 10, 5);
      return s.toString().equals("10:  [Free, Free, Free, Free, Free]");
   }
   
   /**
   * Tests a standard case for the method equals.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean equalsTestStandard()
   {
      Student student1 = createStudent();
      Student student2 = createStudent();
      Student student3 = createStudent();
      student3.setGrade(11);
      
      return (student1.equals(student2)
               && !student1.equals(student3));
   }
   
   /**
   * Tests an edge case for the method equals.
   *
   * @return true if the test passed, otherwise false
   */
   public static boolean equalsTestEdge()
   {
      Student student1 = createStudent();
      String notAStudent = student1.toString();
      
      return !student1.equals(notAStudent);
   }
}