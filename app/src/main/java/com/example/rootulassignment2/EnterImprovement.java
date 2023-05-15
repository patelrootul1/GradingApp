package com.example.rootulassignment2;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class EnterImprovement extends Fragment implements AdapterView.OnItemSelectedListener {
    View v;
    Spinner spinner;
    EditText student_ID;
    EditText improvement_Marks;
    Button submit;
    DBHelper dbh;
    Boolean insertStatus;

    public EnterImprovement() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_enter_improvement, container, false);
        spinner = v.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        dbh = new DBHelper(getActivity());
        //Dropdown list
        String[] courses_list = {"Course1","Course2","Course3","Course4"};

        //adapter for the spinner
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,courses_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        student_ID = v.findViewById(R.id.Student_ID2);
        improvement_Marks = v.findViewById(R.id.Improvement_Marks);
        submit = v.findViewById(R.id.submitImprovementButton);
        //On click method on button click
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Student student =  dbh.findStudent(Integer.parseInt(student_ID.getText().toString()));
                //validation of checking only integers using regular expression
                if ((!student_ID.getText().toString().matches("-?\\d+")) || (!improvement_Marks.getText().toString().matches("-?\\d+")) ) {
                    Toast.makeText(getActivity(), "Only integers accepted", Toast.LENGTH_LONG).show();
                }
                else {
                    //fetching the cursor output from db helper class
                    Cursor cursor1 = dbh.searchGrade(Integer.parseInt(student_ID.getText().toString().trim()));
                    //checking if cursor is returning any queries
                    if (cursor1.getCount() == 0) {
                        Toast.makeText(getActivity(), "No student records found", Toast.LENGTH_LONG).show();
                    } else {
                        //implementing the imrpovemarks query
                        dbh.improveMarks(Integer.parseInt(student_ID.getText().toString()), spinner.getSelectedItem().toString(), Integer.parseInt(improvement_Marks.getText().toString()));
                        //checking the insert status into the improvement table
                       insertStatus = dbh.InsertImprovments(Integer.parseInt(student_ID.getText().toString()),spinner.getSelectedItem().toString(),Integer.parseInt(improvement_Marks.getText().toString()));
                        if (insertStatus){
                            Toast.makeText(getActivity(),"Record Added Successfully inside the  improvement database", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getActivity(),"Record Insertion Failed in improvement database",Toast.LENGTH_LONG).show();
                        }
                    }
                }


            }
        });

        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}