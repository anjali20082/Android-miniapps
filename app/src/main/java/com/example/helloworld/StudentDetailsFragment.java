package com.example.helloworld;

import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class StudentDetailsFragment extends Fragment {

    private EditText text1, text2, text3, text4;
    private Button button;
    View rootView;
    Student mStudent;
    String name;
//    private static final String ARG_CRIME_ID = "crime_id";

//    public static StudentDetailsFragment newInstance(UUID crimeId) {
//        Bundle args = new Bundle();
//
//        args.putSerializable(ARG_CRIME_ID, crimeId);
////        args.putIntExtra()
//        StudentDetailsFragment fragment = new StudentDetailsFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mStudent = new Student();
        UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(SecondActivity.EXTRA_CRIME_ID);
        mStudent = StudentList.get(getActivity()).getStudent(crimeId);
//         name = mStudent.getName();
        if(mStudent != null){
            Log.i("object","Object has  data");
        }
        else
        {
            Log.i("object","Object has no data");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.student_details, container,false);

        String name = mStudent.getName();
        String dept = mStudent.getDept();
        String mail = mStudent.getEmailid();
        String rollno =mStudent.getRollno();
        Log.i("name", mStudent.getName());
        Log.i("rollno", mStudent.getRollno());
        text1 = (EditText)  rootView.findViewById(R.id.textView1);
        text2 = (EditText)  rootView.findViewById(R.id.textView2);
        text3 = (EditText)  rootView.findViewById(R.id.textView3);
        text4 = (EditText)  rootView.findViewById(R.id.textView);
        button=(Button)rootView.findViewById(R.id.button);
            text1.setText(name);
            text2.setText(dept);
            text3.setText(mail);
            text4.setText(rollno);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

//                    CharSequence name = text4.getText().toString();
                    mStudent.setRollno(text4.getText().toString());
                   mStudent.setName(text1.getText().toString());
                    mStudent.setDept(text2.getText().toString());
                    mStudent.setEmailid(text3.getText().toString());
                }
            });

            return rootView;
//        return inflater.inflate(R.layout.student_details, container, false);

    }
}
