package com.example.drinkapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.drinkapp.Model.DataModel;
import com.example.drinkrecipe.Adapter.DataAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<recyclerView, dataAdapter> extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public com.example.drinkrecipe.Adapter.DataAdapter dataAdapter;
    public RecyclerView recyclerView;
    public ArrayList<DataModel> dataModelArrayList = new ArrayList<DataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputData("Greentea", "artikel1.html");
        inputData("Choco Hazelnut", "artikel1.html");
        inputData("Strawberry Milkshake", "artikel1.html");
        inputData("Lemon Tea", "artikel1.html");
        inputData("Thai Tea", "ThaiTea");
        inputData("Chocolate", "artikel1.html");

        //menampilkan data ke dalam recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(this, dataModelArrayList);
        recyclerView.setAdapter(dataAdapter);


        //menambahkan footer
        DataModel footerModel = new DataModel();
        footerModel.setViewType(2);
        dataModelArrayList.add(footerModel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }

    //fungsi input
    public void inputData(String judul, String konten) {
        DataModel dataModel = new DataModel();
        dataModel.setJudul(judul);
        dataModel.setKonten(konten);
        dataModel.setViewType(1);
        dataModelArrayList.add(dataModel);
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}

