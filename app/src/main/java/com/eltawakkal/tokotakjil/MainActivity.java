package com.eltawakkal.tokotakjil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    RecyclerAdapter adapter;
    String[] nama, harga;

    ImageView imgProfile, imgCart;

    BottomNavigationView bottomNavigationView;

    int[] imgFood = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4,
            R.drawable.food5, R.drawable.food6, R.drawable.food7, R.drawable.food8, R.drawable.food9,
            R.drawable.food10};

    int[] imgDrink = {R.drawable.drink1, R.drawable.drink2, R.drawable.drink3, R.drawable.drink4,
            R.drawable.drink5, R.drawable.drink6, R.drawable.drink7, R.drawable.drink8, R.drawable.drink9,
            R.drawable.drink10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFood();

        imgProfile = (ImageView) findViewById(R.id.imgPro);
        imgCart = (ImageView) findViewById(R.id.imgCart);

        adapter = new RecyclerAdapter(nama, harga, imgFood, getApplicationContext());

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.menuBottom);
        mRecycler = (RecyclerView) findViewById(R.id.mRecycler);

        mRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mRecycler.setAdapter(adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_food:

                        addFood();

                        adapter = new RecyclerAdapter(nama, harga, imgFood, getApplicationContext());
                        mRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                        mRecycler.setAdapter(adapter);

                        item.setChecked(true);

                        break;
                    case R.id.action_drink:

                        addDrink();

                        adapter = new RecyclerAdapter(nama, harga, imgDrink, getApplicationContext());
                        mRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                        mRecycler.setAdapter(adapter);

                        item.setChecked(true);

                        break;

                    case R.id.action_reset:
                        DataHelper.dataBarang.clear();
                        DataHelper.totalHarga=0;

                        Toast.makeText(MainActivity.this, "Keranjang Telah Dikosongkan", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (DataHelper.totalHarga!=0){
                    String[] barang = new String[DataHelper.dataBarang.size()];

                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.layout_bayar, null);

                    for (int i=0; i<DataHelper.dataBarang.size(); i++){
                        barang[i] = DataHelper.dataBarang.get(i);
                    }

                    ListView mListBarang = (ListView) view.findViewById(R.id.mListBarang);
                    final TextView txtTotalHarga = (TextView) view.findViewById(R.id.txtTotalH);
                    final EditText editHarga = (EditText) view.findViewById(R.id.txtBayar);

                    Toast.makeText(MainActivity.this, ""+DataHelper.dataBarang.get(2), Toast.LENGTH_SHORT).show();

                    mListBarang.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.list_text_black, R.id.list_content, barang));
                    txtTotalHarga.setText("Total = Rp." + DataHelper.totalHarga);
                    AlertDialog.Builder pesan = new AlertDialog.Builder(MainActivity.this);

                    pesan
                            .setView(view)
                            .setPositiveButton("Proses", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (editHarga.getText()!=null){
                                        if (DataHelper.totalHarga > Integer.parseInt(editHarga.getText().toString())){
                                            Toast.makeText(MainActivity.this, "Uang Anda Kurang", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Silahkan Ke Bangku Anda, Makanan Akan Segera Di Antar", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "Masukkan Uang Anda :)", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).create().show();
                } else {
                    Toast.makeText(MainActivity.this, "Keranjang Masih Kosong :)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder pesan = new AlertDialog.Builder(MainActivity.this);

                pesan
                        .setIcon(R.drawable.logo)
                        .setTitle("About Me")
                        .setMessage("Nama : Hening Minarti\nNim : 14131917\nMakul : Pemrograman Bergerak")
                        .setPositiveButton("Oke", null)
                        .create().show();
            }
        });

    }

    public void addDrink(){
        nama = new String[10];
        harga = new String[10];

        nama[0] = "Pizza Fat";
        nama[1] = "Donat Gembira";
        nama[2] = "Junk Food";
        nama[3] = "Burger Additional";
        nama[4] = "Vegetarian";
        nama[5] = "Tumis Udang";
        nama[6] = "Shusi Original";
        nama[7] = "Lobster Tumis";
        nama[8] = "Risols Gendeng";
        nama[9] = "Steak Mudah";

        harga[0] = "45000";
        harga[1] = "34100";
        harga[2] = "50200";
        harga[3] = "31000";
        harga[4] = "65000";
        harga[5] = "26700";
        harga[6] = "58000";
        harga[7] = "71500";
        harga[8] = "15000";
        harga[9] = "46000";
    }

    public void addFood(){
        nama = new String[10];
        harga = new String[10];

        nama[0] = "Pizza Fat";
        nama[1] = "Donat Gembira";
        nama[2] = "Junk Food";
        nama[3] = "Burger Additional";
        nama[4] = "Vegetarian";
        nama[5] = "Tumis Udang";
        nama[6] = "Shusi Original";
        nama[7] = "Lobster Tumis";
        nama[8] = "Risols Gendeng";
        nama[9] = "Steak Mudah";

        harga[0] = "45000";
        harga[1] = "34100";
        harga[2] = "50200";
        harga[3] = "31000";
        harga[4] = "65000";
        harga[5] = "26700";
        harga[6] = "58000";
        harga[7] = "71500";
        harga[8] = "15000";
        harga[9] = "46000";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_food) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        Toast.makeText(this, "Food", Toast.LENGTH_SHORT).show();
    }
}
