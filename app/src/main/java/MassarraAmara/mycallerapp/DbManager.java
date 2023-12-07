package MassarraAmara.mycallerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DbManager {
    SQLiteDatabase db=null;
    Context con;

    public DbManager(Context c) {
        this.con = c;
    }
    public void ouvrir(){
        DbHelper c = new DbHelper(con,"myDB", null, 1);
        //ouvrir la base
        db=c.getWritableDatabase();
    }
    public long ajout(String nom,String prenom,String num){
        long x=0;
        ContentValues values= new ContentValues();
        values.put(DbHelper.col_nom,nom);
        values.put(DbHelper.col_num,num);
        values.put(DbHelper.col_prenom,prenom);
        x=db.insert(DbHelper.contact_table,null,values);
        return x;
    }
    public ArrayList<Contact> showAll(){
        ArrayList <Contact> x =new ArrayList<Contact>();
        Cursor c=db.query(DbHelper.contact_table,
                new String[]{DbHelper.col_num,DbHelper.col_nom,DbHelper.col_prenom},
                null,
                null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            String i1=c.getString(0);
            Log.i("","nom"+c.getString(0)+"prenom"+c.getString(1)+"tel"+c.getString(2));
            String i2=c.getString(1);
            String i3=c.getString(2);
            x.add(new Contact(i1,i2,i3));
            c.moveToNext();
        }

        return x;
    }

    public ArrayList<Contact> find(String s){
        ArrayList <Contact> x =new ArrayList<Contact>();
        Cursor c=db.query(
                DbHelper.contact_table,
                new String[]{DbHelper.col_num,DbHelper.col_nom,DbHelper.col_prenom},
                null,
                null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            String i1=c.getString(0);
            //Log.i("","nom"+c.getString(0)+"prenom"+c.getString(1)+"tel"+c.getString(2));
            String i2=c.getString(1);
            String i3=c.getString(2);
            if(i2.contains(s)||i3.contains(s)){
                x.add(new Contact(i1,i2,i3));
            }
            c.moveToNext();
        }
        return x;

    }
    public void supprimer(String num){
        Log.i("ss","num"+num);
        try{
            db.delete(DbHelper.contact_table,DbHelper.col_num+" =?", new String[]{num});
        } catch(Exception e){
            Log.i("ba3d supp","num"+num);
        }
    }

    public void modifier(Contact c){
        ContentValues values = new ContentValues();
        values.put(DbHelper.col_nom, c.nom);
        values.put(DbHelper.col_prenom, c.prenom);
        db.update(DbHelper.contact_table,
                values,
                DbHelper.col_num+" =?",
                new String[]{c.numero});

    }
    public void fermer(){

    }
}
