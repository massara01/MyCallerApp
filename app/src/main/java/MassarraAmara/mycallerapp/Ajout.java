package MassarraAmara.mycallerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Ajout extends AppCompatActivity {

    private ImageView val,cancel;

    private EditText nom,prenom,numero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        nom=findViewById(R.id.ednom_ajout);
        prenom=findViewById(R.id.edprenom_ajout);
        numero=findViewById(R.id.ednum_ajout);
        val=findViewById(R.id.IVval_ajout);
        cancel=findViewById(R.id.IVcan_ajout);



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        val.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent i = new Intent(Ajout.this, ListView1.class);
                i.putExtra("nom",nom.getText().toString());
                i.putExtra("prenom",prenom.getText().toString());
                i.putExtra("num",numero.getText().toString());

                startActivity(i);*/
                String nom1=nom.getText().toString();
                String prenom1=prenom.getText().toString();
                String tel=numero.getText().toString();
                //Contact c= new Contact(nom,prenom,tel);

                Log.i("aajjhhggfddsd","");
                long ajout = MainActivity.dm.ajout( nom1, prenom1,tel);
                Log.i("aaa",":"+ajout);

            }
        });

    }
}