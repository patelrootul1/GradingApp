package com.example.rootulassignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Student> studentList;

    public ListAdapter(List<Student> studentList, Context context){
        super();
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView sId,sName, sProgram, sMarks1, sMarks2, sMarks3, sMarks4, sTotalMarks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sId = (TextView) itemView.findViewById(R.id.txtSID);
            sName = (TextView) itemView.findViewById(R.id.txtSName);
            sProgram = (TextView) itemView.findViewById(R.id.txtSProgram);
            sMarks1 = (TextView) itemView.findViewById(R.id.txtSCourse1);
            sMarks2 = (TextView) itemView.findViewById(R.id.txtSCourse2);
            sMarks3 = (TextView) itemView.findViewById(R.id.txtSCourse3);
            sMarks4 = (TextView) itemView.findViewById(R.id.txtSCourse4);
            sTotalMarks = (TextView) itemView.findViewById(R.id.txtSTotalMarks);


        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Student student = studentList.get(position);
        ((ViewHolder) holder).sId.setText(String.valueOf(student.getStudent_ID()));
        ((ViewHolder) holder).sName.setText(String.valueOf(student.getStudentName()));
        ((ViewHolder) holder).sProgram.setText(String.valueOf(student.getProgram()));
        ((ViewHolder) holder).sMarks1.setText(String.valueOf(student.getCourse1()));
        ((ViewHolder) holder).sMarks2.setText(String.valueOf(student.getCourse2()));
        ((ViewHolder) holder).sMarks3.setText(String.valueOf(student.getCourse3()));
        ((ViewHolder) holder).sMarks4.setText(String.valueOf(student.getCourse4()));
        ((ViewHolder) holder).sTotalMarks.setText(String.valueOf(student.getTotalMarks()));

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


}
