package com.example.rootulassignment2;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchGrade extends Fragment {
    View v;
    List<Student> students = new ArrayList<>();
    RecyclerView recyclerView;
    DBHelper dbh;
    EditText stu_id;
    int totalMarks;
    String User_Input;
    ListAdapter mAdapter;
    Button search_Button;

    public SearchGrade() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search_grade, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView1);
        dbh = new DBHelper(getActivity());
        search_Button = (Button) v.findViewById(R.id.SearchStudentButton);
        stu_id = v.findViewById(R.id.Student_ID3);
        //Method for onclick of search button
        search_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.removeAllViewsInLayout();
                //validation of student id
                if ((!stu_id.getText().toString().matches("-?\\d+")))
                {
                    Toast.makeText(getActivity(), "Only Digits allowed", Toast.LENGTH_LONG).show();
                }
                else {
                    Cursor cursor1 = dbh.searchGrade(Integer.parseInt(stu_id.getText().toString().trim()));
                    if (cursor1.getCount() == 0) {
                        Toast.makeText(getActivity(), "No student records found", Toast.LENGTH_LONG).show();
                    } else {
                        cursor1.moveToFirst();
                        do {
                            Student student = new Student();
                            student.setStudent_ID(cursor1.getInt(0));
                            student.setStudentName(cursor1.getString(1));
                            student.setProgram(cursor1.getString(2));
                            student.setCourse1(cursor1.getInt(3));
                            student.setCourse2(cursor1.getInt(4));
                            student.setCourse3(cursor1.getInt(5));
                            student.setCourse4(cursor1.getInt(6));
                            students.add(student);
                        } while (cursor1.moveToNext());
                        cursor1.close();
                        dbh.close();
                        BindAdapter();

                    }
                }

            }
        });
        return v;
    }
    //bindadapter for setting up the searching the grade using student id
    private void BindAdapter () {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListAdapter(students, getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}