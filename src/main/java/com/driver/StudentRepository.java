package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    private List<Student> studentlist;
    private List<Teacher> teacherlist;

    private Map<String,List<String>> hm;

    public StudentRepository() {
        studentlist = new ArrayList<>();
        teacherlist = new ArrayList<>();
        hm= new HashMap<>();
    }

    public String addStudent(Student student){

        studentlist.add(student);
        return "New student added successfully";
    }

    public String addTeacher(Teacher teacher){

       teacherlist.add(teacher);

       return "New teacher added successfully";

    }

    public String addStudentTeacherPair(String student, String teacher){

//          if(studentlist.contains(student) && teacherlist.contains(teacher)){
              if(!hm.containsKey(teacher)){
                  hm.put(teacher,new ArrayList<>());
              }
              hm.get(teacher).add(student);
//          }

         return "New student-teacher pair added successfully";
    }

    public Student getStudentByName(String name){

        for(Student student:studentlist){
            if(student.getName().equals(name)){
                return student;
            }
        }
        return null;
    }

    public Teacher getTeacherByName(String name){

        for(Teacher teacher: teacherlist){
            if(teacher.getName().equals(name)){
                return teacher;
            }
        }
        return null;
    }

    public List<String> getStudentsByTeacherName(String teacher){

        return hm.get(teacher);
    }

    public List<String> getAllStudents(){

        List<String> studentnamelist = new ArrayList<>();
       for(Student student:studentlist){
           studentnamelist.add(student.getName());
       }
       return studentnamelist;
    }

    public String deleteTeacherByName(String teacher){

        List<String> studentnamelist=hm.get(teacher);
        for(Student student:studentlist){
            if(studentnamelist.contains(student.getName())){
                studentlist.remove(student);
            }
        }
        hm.remove(teacher);
        teacherlist.remove(teacher);
        return " removed successfully";
    }

    public String deleteAllTeachers(){
         Set<String> hs = new HashSet<>();
        for(String teachername: hm.keySet()){
            List<String> li=hm.get(teachername);
            for(String s:li){
                hs.add(s);
            }
        }

        for(Student student:studentlist){
            if(hs.contains(student.getName())){
                studentlist.remove(student);
            }
        }
        hm.clear();
        teacherlist.clear();
        return "All teachers deleted successfully";
    }

}
