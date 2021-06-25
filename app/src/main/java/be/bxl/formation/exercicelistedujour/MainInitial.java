package be.bxl.formation.exercicelistedujour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import be.bxl.formation.exercicelistedujour.asynctasks.CounterTask;

public class MainInitial extends AppCompatActivity {
TextView tvCoucou;
ImageView ivimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_initial);
        tvCoucou = findViewById(R.id.tv_init_salut);
        ivimage = findViewById(R.id.iv_init_img);


    }

    @Override
    protected void onResume() {
        super.onResume();
        CounterTask counterTask = new CounterTask();

        counterTask.setEventCounter(count -> {
            switch (count){
                case 0: Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case 1: tvCoucou.setText("Salut mon Createur !!"); break;
                case 2: tvCoucou.setText("Salut mon Dieu !!");
                    ivimage.setImageResource(R.drawable.dieu);break;
                case 3: tvCoucou.setText("Salut mon nounours !!"); break;
                case 4: tvCoucou.setText("Salut mon amours !!"); break;
                case 5: tvCoucou.setText("Salut mon pote !!"); break;
                case 6: tvCoucou.setText("Salut mon ami !!");
                    ivimage.setImageResource(R.drawable.ami);
                    break;
                case 7: tvCoucou.setText("Salut mon copain !!"); break;
                case 8: tvCoucou.setText("Salut mon bel inconnu !!");

                break;
                case 9: tvCoucou.setText("Salut mon inconnu !!");
                ivimage.setImageResource(R.drawable.inconnu);

                break;
                case 10: tvCoucou.setText("Salut mon gars !!"); break;

            }

        });
        counterTask.execute(20);
    }
}