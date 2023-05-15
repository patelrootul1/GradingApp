package com.example.rootulassignment2;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListStudents extends Fragment {
    //ArrayList<Student> students;
    List<Student> students = new ArrayList<>();
    RecyclerView recyclerView;
    DBHelper dbh;
    View v;
    ListAdapter mAdapter;

    public ListStudents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list_students, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        dbh = new DBHelper(getActivity());

        Cursor cursor1 = dbh.listStudent();
        if (cursor1.getCount()==0) {
            Toast.makeText(getActivity(), "No student records found", Toast.LENGTH_LONG).show();
            return v;
        }
        else {
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
            return v;
       }
   }
        private void BindAdapter () {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            mAdapter = new ListAdapter(students, getContext());
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

