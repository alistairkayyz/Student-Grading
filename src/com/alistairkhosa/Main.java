package com.alistairkhosa;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[][] gradesArray = { {54, 52, 88},
                                {84, 52, 48},
                                {85, 100, 54},
                                {90, 98, 84},
                                {84, 52, 41}};
        GradeBook myGradeBook = new GradeBook("Introduction to Java Programming", gradesArray);

        System.out.printf("Welcome to the grade book for: %n%s%n%n", myGradeBook.getCourseName());
        myGradeBook.processGrades();
    }
}
class GradeBook{
    private String courseName; // name of the course this book represents
    private final int[][] grades; // array of student grades

    public  GradeBook(String courseName, int[][] grades){
        this.courseName = courseName;
        this.grades = grades;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void outputGrades(){
        System.out.printf("The Grades are:%n%n");
        System.out.print("\t\t\t  ");

        for (int test = 0; test < grades[0].length; test++) {
            System.out.printf("Test %d  ", test + 1);
        }
        System.out.println("Average");

        for (int student = 0; student < grades.length; student++) {
            System.out.printf("Student %2d", student + 1);
            for(int test : grades[student])
                System.out.printf("%8d", test);

            double average = getAverage(grades[student]); // pass current row of grades
            System.out.printf("%9.2f%n", average);
        }
    }
    public double getAverage(int[] setOfGrades){
        int total = 0;

        // sum grades for one student
        for (int grade: setOfGrades) {
            total += grade;
        }

        return (double) total / setOfGrades.length;
    }
    public int getMinimum(){
        int minGrade = grades[0][0]; // assume grade[0] is the smallest

        // loop through the array grades
        for (int[] studentGrades : grades ) {
            for (int grade : studentGrades) {
                // if grade is lower than minGrade, assign it to minGrade
                if (grade < minGrade)
                    minGrade = grade; // new lowest grade
            }
        }
        return minGrade;
    }
    public int getMaximum(){
        int maxGrade = grades[0][0]; // assume grade[0] is the largest grade

        // loop through the array grades
        for (int[] studentGrades : grades ) {
            for (int grade : studentGrades) {
                // if grade is lower than minGrade, assign it to minGrade
                if (grade > maxGrade)
                    maxGrade = grade; // new lowest grade
            }
        }
        return maxGrade;
    }
    public void outputBarChart(){
        System.out.println("Grade distribution:");

        // stores frequency of grades in each range of 10 grades
        int[] frequency = new int[11];

        for(int[] studentGrades : grades){
            for (int grade : studentGrades) {
                ++frequency[grade/10];
            }
        }

        for (int count = 0; count < frequency.length; count++) {
            if(count == 10)
                System.out.printf("%5d: ", 100);
            else
                System.out.printf("%02d-%02d: ", count * 10, count * 10 + 9);

            for (int stars = 0; stars < frequency[count]; stars++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public void processGrades(){
        // output grades array
        outputGrades();

        // call method getMinimum and getMaximum
        System.out.printf("Lowest grade is %d%nHighest grade is %d%n%n", getMinimum(), getMaximum());

        // call to print grade distribution chart
        outputBarChart();
    }
}