package com.example.rootulassignment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EnterGrades extends Fragment {

    View v;
    EditText Student_Name;
    EditText Student_ID;
    EditText Program;
    EditText Course1_Marks;
    EditText Course2_Marks;
    EditText Course3_Marks;
    EditText Course4_Marks;
    Button submit;
    DBHelper dbh;
    Boolean insertStatus;
    public EnterGrades() {
        // Required empty public constructor
    }
    //initializing the student object using createstudent function
    public Student CreateStudent(){
        Student student = new Student();
        int sId = 0;
        student.setStudent_ID(sId);
        student.setStudentName(Student_Name.getText().toString().trim());
        student.setProgram(Program.getText().toString().trim());

        student.setCourse1(Integer.parseInt(String.valueOf(Course1_Marks.getText())));
        student.setCourse2(Integer.parseInt(String.valueOf(Course2_Marks.getText())));
        student.setCourse3(Integer.parseInt(String.valueOf(Course3_Marks.getText())));
        student.setCourse4(Integer.parseInt(String.valueOf(Course4_Marks.getText())));

        return student;
    }
    //Data Validation using regex and also checking if the inserting marks are between 0 and 100
    public boolean ValidateData(){
        if((!Course1_Marks.getText().toString().matches("-?\\d+"))
                ||(!Course2_Marks.getText().toString().matches("-?\\d+"))
                ||(!Course3_Marks.getText().toString().matches("-?\\d+"))
                ||(!Course4_Marks.getText().toString().matches("-?\\d+"))
                ||(!Student_Name.getText().toString().matches("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$"))
                ||(!Program.getText().toString().matches("^[a-zA-Z ]*$")))
        {
            return false;
        }
        else if((Student_Name.getText().toString().equals(""))){
            return false;
        }else if((Program.getText().toString().equals(""))){
            return false;
        }else if((Course1_Marks.getText().toString().equals("")) ||
                (Integer.parseInt(String.valueOf(Course1_Marks.getText())) > 100
                        || (Integer.parseInt(String.valueOf(Course1_Marks.getText())) < 0)) ){
            return false;
        }else if((Course2_Marks.getText().toString().equals("")) ||
                (Integer.parseInt(String.valueOf(Course2_Marks.getText())) > 100
                        || (Integer.parseInt(String.valueOf(Course1_Marks.getText())) < 0))){
            return false;
        }else if((Course3_Marks.getText().toString().equals("")) ||
                (Integer.parseInt(String.valueOf(Course3_Marks.getText())) > 100
                        || (Integer.parseInt(String.valueOf(Course1_Marks.getText())) < 0))){
            return false;
        }else if((Course4_Marks.getText().toString().equals("")) ||
                (Integer.parseInt(String.valueOf(Course4_Marks.getText())) > 100
                        || (Integer.parseInt(String.valueOf(Course1_Marks.getText())) < 0))){
            return false;
        }
        return  true;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_enter_grades, container, false);
        Student_Name = v.findViewById(R.id.tbStudentName);
        Student_ID = v.findViewById(R.id.tbStudentID);
        Program = v.findViewById(R.id.Student_Program);
        Course1_Marks = v.findViewById(R.id.Course1_Marks);
        Course2_Marks = v.findViewById(R.id.Course2_Marks);
        Course3_Marks = v.findViewById(R.id.Course3_Marks);
        Course4_Marks = v.findViewById(R.id.Course4_Marks);
        submit = v.findViewById(R.id.submitMarksButton);
        dbh = new DBHelper(getActivity());
        Student_ID.setVisibility(v.GONE);
        //creating method for onclick of submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking for validation using function validateData()
                boolean createStatus = ValidateData();
                if(createStatus){
                    Student student = CreateStudent();
                    //Inserting the data inside school table using InsertGrades method in dbhelper class
                    insertStatus = dbh.InsertGrades(student);
                    if (insertStatus){
                        Toast.makeText(getActivity(),"Record Added Successfully inside the database", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getActivity(),"Record Insertion Failed",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(),"Records are not filled properly ",Toast.LENGTH_LONG).show();

                }

            }
        });


        return v;

    }

}