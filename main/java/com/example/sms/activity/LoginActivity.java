package com.example.sms.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.sms.R;
import com.example.sms.component.HttpUtil;
import com.example.sms.entity.Student;
import com.example.sms.entity.Teacher;
import com.google.gson.Gson;

import static com.example.sms.component.CONST.STUDENT;
import static com.example.sms.component.CONST.TEACHER;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText account;
    private EditText pwd;
    private RadioButton isStudent;
    private TextView wait;
    private Intent studentActivity;
    private Intent teacherActivity;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        setListenMethod();
    }

    private void setListenMethod() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable accountText = account.getText();
                Editable pwdText = pwd.getText();
                boolean isStudentChecked = isStudent.isChecked();
                String service, pam;
                int id = Integer.parseInt(String.valueOf(accountText));
                String password = String.valueOf(pwdText);
                if (isStudentChecked) {
                    Student student = new Student(id, password);
                    service = STUDENT + "/student/login";
                    pam = "pam=" + gson.toJson(student);
                } else {
                    Teacher teacher = new Teacher(id, password);
                    service = TEACHER + "/teacher/login";
                    pam = "pam=" + gson.toJson(teacher);
                }

                new HttpUtil(wait, service, pam) {
                    @Override
                    public void handle(String result) {
                        if ("success".equals(result)) {
                            if (isStudentChecked) {//打开学生窗口
                                studentActivity.putExtra("id",id);
                                startActivity(studentActivity);
                            } else {               //打开教师窗口
                                teacherActivity.putExtra("id",id);
                                startActivity(teacherActivity);
                            }
                            finish();
                        } else if (("failure".equals(result))) {
                            wait.setText(result);
                        } else {
                            wait.setText("登录失败");
                        }
                    }
                };
            }
        });
    }

    private void init() {
        studentActivity =new Intent(LoginActivity.this,StudentActivity.class);
        teacherActivity =new Intent(LoginActivity.this,TeacherActivity.class);
        login = findViewById(R.id.button);
        account = findViewById(R.id.editTextTextPersonName);
        pwd = findViewById(R.id.editTextTextPassword);
        isStudent = findViewById(R.id.radioButton);
        wait = findViewById(R.id.textView);
    }
}