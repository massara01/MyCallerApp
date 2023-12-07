package MassarraAmara.mycallerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Acceuil extends AppCompatActivity {
    private TextView tvusername;

    private ImageView add,list;
    public  static ArrayList<Contact>data=new ArrayList<Contact>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);


        tvusername=findViewById(R.id.tvuser_acc);

        add=findViewById(R.id.IVAdd_acc);
        list=findViewById(R.id.IVList_acc);





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Acceuil.this, Ajout.class);
                startActivity(i);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j =new Intent(Acceuil.this, Affiche.class);
                startActivity(j);
            }
        });
    }
}