package com.example.helloworld;

import  androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class StudentListFragment  extends Fragment{

    private RecyclerView mStudentRecyclerView;
    private StudentAdapter mAdapter;
    public  Bundle bundle;
    StudentDetailsFragment stl = new StudentDetailsFragment ();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);

        mStudentRecyclerView = (RecyclerView) view.findViewById(R.id.student_recycler_view);
        mStudentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){

        StudentList stlist = StudentList.get(getActivity());
        List<Student> students = stlist.getStudents();

//        mAdapter = new StudentAdapter(students);
//        mStudentRecyclerView.setAdapter(mAdapter);
        if (mAdapter == null) {
            mAdapter = new StudentAdapter(students);
            mStudentRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private class StudentHolder extends RecyclerView.ViewHolder {
        private Student mStudent;

        private TextView nameTextView;
        private TextView rollnoTextView;

        public StudentHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_student, parent, false));
            itemView.setOnClickListener(this);

            rollnoTextView = (TextView) itemView.findViewById(R.id.rollno);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
        }

        public void bind(Student student) {
            mStudent = student;
            nameTextView.setText(mStudent.getName());
            rollnoTextView.setText(mStudent.getRollno());
        }

        //
        @Override
        public void onClick(View view) {
//
            Toast.makeText(getActivity(),
                    mStudent.getRollno() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
//            Intent intent = new Intent(getActivity(), mStudent.getRollno());

            Intent intent = SecondActivity.newIntent(getActivity(), mStudent.getId());
            Log.i("INTENT","StudentDetails sent");
            Log.i("Details", mStudent.getId().toString());
            startActivity(intent);
//

        }


    }

        private class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {

            private List<Student> mStudent;
//            private Student student;

            public StudentAdapter(List<Student> crimes) {
                mStudent = crimes;
            }

            @Override
            public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                return new StudentHolder(layoutInflater, parent);
            }

            @Override
            public void onBindViewHolder(StudentHolder holder, int position) {
                Student student = mStudent.get(position);
                holder.bind(student);
//            bundle = new Bundle();
//            Student currentstudent = mStudent.get(position);
//            holder.nameTextView.setText(currentstudent.getName());
//            holder.rollnoTextView.setText(currentstudent.getRollno().toString());
//
//            holder.rollnoTextView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(getContext(),SecondActivity.class);
////                      Intent intent = SecondActivity.CreatenewIntent(getActivity(), student.getRollno());
////                    Intent intent = new Intent(getActivity(), student.getRollno());
////                    intent.putExtra("rollno", currentstudent.getRollno());
//
////                    bundle.putString("name", currentstudent.getName());
////                    bundle.putString("dept", currentstudent.getDept());
////                    bundle.putString("mail", currentstudent.getEmailid());
//
//
////                    getContext().startActivity(intent);
//                    startActivity(intent);
//                    Log.i("INTENT","StudentDetails sent");
//                    Log.i("Details", currentstudent.getDept());
//
//                }
//            });
//
//            holder.nameTextView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    holder.nameTextView.setText(student.getName()+" Student has been clicked");
//                    Toast.makeText(v.getContext(),"Student with id "+student.getRollno()+" has been clicked",Toast.LENGTH_SHORT).show();
//                }
//            });

//                Student student = mStudent.get(position);

            }

            @Override
            public int getItemCount() {
                return mStudent.size();
            }
        }
    }

