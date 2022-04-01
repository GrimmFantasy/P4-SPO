#include <stdlib.h>
#include <stdio.h>
#include <string.h>

struct Student {
    char name[50];
    int age;
    float GPA;
    char grade[10];
    

};

struct Student writeStudent(char name[], int age, float GPA, char grade[]){
    struct Student student;
    strcpy(student.name, name);
    student.age = age;
    strcpy(student.grade, grade);
    student.GPA = GPA;
    return student;
}

int main(void) 
{
    struct Student Student1 = writeStudent("Martin Bernth", 23, 5.0, "Freshmen");
    struct Student Student2 = writeStudent("DJ", 20, 7.0, "Senoir");
    
    struct Student Students[10];

    Students[0] = Student1;
    Students[1] = Student2;

    printf("Student: %d\n", Students[0].age);

    char Sname[50][50];
    int Sage[50];
    float SGPA[50];
    char Sgrade[50][50];

    strcpy(Sname[0], Students[0].name);
    Sage[0]=Students[0].age;
    SGPA[0]=Students[0].GPA;
    strcpy(Sgrade[0], Students[0].grade);
    printf("Sgrade: %s\n", Sgrade[0]);

    return 0;
}