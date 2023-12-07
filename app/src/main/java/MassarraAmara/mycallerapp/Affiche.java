package MassarraAmara.mycallerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class Affiche extends AppCompatActivity {

  Button rech;
    EditText SearchV;
    RecyclerView RV;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);

       SearchV=findViewById(R.id.ed_Search_aff);
       rech=findViewById(R.id.btn_rech_aff);
       RV=findViewById(R.id.RV_aff);



        Acceuil.data=MainActivity.dm.showAll();
        MyContactRecyclerAdapter ad1 =new MyContactRecyclerAdapter(Affiche.this,Acceuil.data);

        GridLayoutManager manager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL, false);
        RV.setLayoutManager(manager);
        RV.setAdapter(ad1);
       rech.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ArrayList<Contact> res = MainActivity.dm.find(SearchV.getText().toString());
               if (!res.isEmpty()){
                   Acceuil.data=res;
                   MyContactRecyclerAdapter ad =new MyContactRecyclerAdapter(Affiche.this,Acceuil.data);
                   RV.setAdapter(ad);
               }
           }
       });






    }
}