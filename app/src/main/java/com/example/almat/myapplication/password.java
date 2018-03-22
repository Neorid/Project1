package com.example.almat.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class password extends AppCompatActivity {
    // Объявляем об использовании следующих объектов:
    private EditText username;
    private EditText passwordd;
    private Button login;
    private TextView loginLocked;
    private TextView attempts;
    private TextView numberOfAttempts;

    //Число для подсчета попыток залогиниться
    int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //Связываемся с элементами интерфейса:

        username = (EditText) findViewById(R.id.edit_user);
        passwordd = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.button_login);
        loginLocked = (TextView) findViewById(R.id.login_locked);
        attempts = (TextView) findViewById(R.id.attempts);
        numberOfAttempts = (TextView) findViewById(R.id.number_of_attempts);
        numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));
    }

    // Оброботчик нажатия кнопки "Войти" ;
    public void Login(View view) {
        //Если вводимыми данными будут на логин admin, а на пароль 123
        //то показываем сообщение об успешной авторизации:
        if (username.getText().toString().equals("admin") && passwordd.getText().toString().equals("123")) {
            Toast.makeText(getApplicationContext(), "Вход выполнен", Toast.LENGTH_LONG).show();

            //Выполнение перехода на другой Atctivity
            Intent intent = new Intent(password.this, ActivityThree.class);
            startActivity(intent);
        }
        //В другом случае выдаем сообщение об ошибки
        else {
            Toast.makeText(getApplicationContext(), "Неправильно введеные данные", Toast.LENGTH_LONG).show();
            numberOfRemainingLoginAttempts--;

            //Вывод текстового сообщение показывающего на количество оставшихся попыток
            attempts.setVisibility(View.VISIBLE);
            numberOfAttempts.setVisibility(View.VISIBLE);
            numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));

            //Когда выполнено 3 безуспешных попытки авторизации
            //делаем видимым текствое поле с надпись что все пропало и выстовляем
            //button найстроку невозможного нажанития через setEnabled(false)
            if (numberOfRemainingLoginAttempts == 0) {
                login.setEnabled(false);
                loginLocked.setVisibility(View.VISIBLE);
                loginLocked.setBackgroundColor(Color.RED);
                loginLocked.setText("Вход заблокирован");
            }
        }
    }
}

