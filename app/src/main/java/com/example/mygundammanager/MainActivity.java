package com.example.mygundammanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mygundammanager.databinding.ActivityMainBinding;
import com.example.mygundammanager.database.AppDatabase;
import com.example.mygundammanager.database.ChinPlaDatabase;
import com.example.mygundammanager.ui.adapter.Category;
import com.example.mygundammanager.ui.adapter.CategoryAdapter;
import com.example.mygundammanager.ui.adapter.ChinPlaAdapter;
import com.example.mygundammanager.ui.adapter.GundamAdapter;

import com.example.mygundammanager.bandai.model.EntryGradeGundam;
import com.example.mygundammanager.bandai.model.HighGradeGundam;
import com.example.mygundammanager.bandai.model.RealGradeGundam;
import com.example.mygundammanager.bandai.model.MasterGradeGundam;
import com.example.mygundammanager.bandai.model.PerfectGradeGundam;
import com.example.mygundammanager.bandai.model.MegaSizeGundam;
import com.example.mygundammanager.bandai.model.MetalBuildGundam;
import com.example.mygundammanager.bandai.model.MetalRobotSpiritsGundam;

import com.example.mygundammanager.thirdparty.model.SNAA;
import com.example.mygundammanager.thirdparty.model.CangToys;
import com.example.mygundammanager.thirdparty.model.Blokees;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private GundamAdapter gundamAdapter;
    private ChinPlaAdapter chinPlaAdapter;
    private Category currentCategory;

    // Gestione Immagine
    private ActivityResultLauncher<String> imagePickerLauncher;
    private Uri selectedImageUri;
    private ImageView dialogPreviewImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inizializza il picker foto
        setupImagePicker();

        setupCategoryGrid();
        setupFinancialSummary();
        setupBackPressedHandler();

        if (binding.fabAddItem != null) {
            binding.fabAddItem.setOnClickListener(v -> showAddItemDialog());
        }
    }

    private void setupImagePicker() {
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        selectedImageUri = uri;
                        // Concede il permesso persistente di lettura dell'URI
                        try {
                            getContentResolver().takePersistableUriPermission(
                                    uri,
                                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                            );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (dialogPreviewImageView != null) {
                            dialogPreviewImageView.setImageURI(uri);
                        }
                    }
                }
        );
    }

    private void setupCategoryGrid() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Entry Grade", true));
        categoryList.add(new Category("High Grade", true));
        categoryList.add(new Category("Real Grade", true));
        categoryList.add(new Category("Master Grade", true));
        categoryList.add(new Category("Perfect Grade", true));
        categoryList.add(new Category("Mega Size", true));
        categoryList.add(new Category("Metal Build", true));
        categoryList.add(new Category("Metal Robot Spirits", true));
        categoryList.add(new Category("SNAA", false));
        categoryList.add(new Category("CangToys", false));
        categoryList.add(new Category("Blokees", false));

        binding.recyclerViewCategories.setLayoutManager(new GridLayoutManager(this, 2));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, category -> {
            currentCategory = category;
            binding.recyclerViewCategories.setVisibility(View.GONE);
            binding.recyclerViewItems.setVisibility(View.VISIBLE);
            if (binding.fabAddItem != null) {
                binding.fabAddItem.setVisibility(View.VISIBLE);
            }

            loadItemsForCategory(category);
        });

        binding.recyclerViewCategories.setAdapter(categoryAdapter);

        gundamAdapter = new GundamAdapter(this);
        chinPlaAdapter = new ChinPlaAdapter(this);
        binding.recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadItemsForCategory(Category category) {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase bandaiDb = AppDatabase.getInstance(getApplicationContext());
            ChinPlaDatabase chinplaDb = ChinPlaDatabase.getInstance(getApplicationContext());

            if (category.isBandai()) {
                List items;
                switch (category.getName()) {
                    case "Entry Grade": items = bandaiDb.entryGradeDao().getAllEntryGrades(); break;
                    case "High Grade": items = bandaiDb.highGradeDao().getAllHighGrades(); break;
                    case "Real Grade": items = bandaiDb.realGradeDao().getAllRealGrades(); break;
                    case "Master Grade": items = bandaiDb.masterGradeDao().getAllMasterGrades(); break;
                    case "Perfect Grade": items = bandaiDb.perfectGradeDao().getAllPerfectGrades(); break;
                    case "Mega Size": items = bandaiDb.megaSizeDao().getAllMegaSizes(); break;
                    case "Metal Robot Spirits": items = bandaiDb.metalRobotSpiritsDao().getAllMetalRobotSpirits(); break;
                    case "Metal Build": items = bandaiDb.metalBuildDao().getAllMetalBuilds(); break;
                    default: items = new ArrayList<>();
                }
                runOnUiThread(() -> {
                    binding.recyclerViewItems.setAdapter(gundamAdapter);
                    gundamAdapter.setGundamList(items);
                });
            } else {
                List items;
                switch (category.getName()) {
                    case "SNAA": items = chinplaDb.snaaDao().getAllSNAAs(); break;
                    case "CangToys": items = chinplaDb.cangToysDao().getAllCANGs(); break;
                    case "Blokees": items = chinplaDb.blokeesDao().getAllBlokees(); break;
                    default: items = new ArrayList<>();
                }
                runOnUiThread(() -> {
                    binding.recyclerViewItems.setAdapter(chinPlaAdapter);
                    chinPlaAdapter.setChinPlaList(items);
                });
            }
        });
    }

    private void showAddItemDialog() {
        if (currentCategory == null) return;
        selectedImageUri = null; // Resetta la selezione foto precedente

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null);

        EditText etName = dialogView.findViewById(R.id.et_model_name);
        EditText etPrice = dialogView.findViewById(R.id.et_model_price);
        EditText etDescription = dialogView.findViewById(R.id.et_model_description);
        CheckBox cbSpareRunners = dialogView.findViewById(R.id.cb_spare_runners);
        Button btnSelectPhoto = dialogView.findViewById(R.id.btn_select_photo);
        dialogPreviewImageView = dialogView.findViewById(R.id.img_preview);

        btnSelectPhoto.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));

        builder.setView(dialogView)
                .setTitle("Aggiungi kit: " + currentCategory.getName())
                .setPositiveButton("Salva", (dialog, which) -> {
                    String name = etName.getText().toString().trim();
                    String priceStr = etPrice.getText().toString().trim();
                    String description = etDescription != null ? etDescription.getText().toString().trim() : "";
                    boolean spareRunners = cbSpareRunners != null && cbSpareRunners.isChecked();
                    String photoUriStr = selectedImageUri != null ? selectedImageUri.toString() : "";

                    if (!name.isEmpty() && !priceStr.isEmpty()) {
                        double price = Double.parseDouble(priceStr);
                        insertItemIntoDatabase(name, price, description, spareRunners, photoUriStr);
                    } else {
                        Toast.makeText(this, "Nome e prezzo sono obbligatori!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Annulla", null)
                .show();
    }

    private void insertItemIntoDatabase(String name, double price, String description, boolean spareRunners, String photoUri) {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase bandaiDb = AppDatabase.getInstance(getApplicationContext());
            ChinPlaDatabase chinplaDb = ChinPlaDatabase.getInstance(getApplicationContext());
            long currentTime = System.currentTimeMillis();

            if (currentCategory.isBandai()) {
                switch (currentCategory.getName()) {
                    case "Entry Grade": bandaiDb.entryGradeDao().insert(new EntryGradeGundam(name, price, description, spareRunners, currentTime, photoUri)); break;
                    case "High Grade": bandaiDb.highGradeDao().insert(new HighGradeGundam(name, price, description, spareRunners, currentTime, photoUri)); break;
                    case "Real Grade": bandaiDb.realGradeDao().insert(new RealGradeGundam(name, price, description, spareRunners, currentTime, photoUri)); break;
                    case "Master Grade": bandaiDb.masterGradeDao().insert(new MasterGradeGundam(name, price, description, spareRunners, currentTime, photoUri)); break;
                    case "Perfect Grade": bandaiDb.perfectGradeDao().insert(new PerfectGradeGundam(name, price, description, spareRunners, currentTime, photoUri)); break;
                    case "Mega Size": bandaiDb.megaSizeDao().insert(new MegaSizeGundam(name, price, description, spareRunners, currentTime, photoUri)); break;
                    case "Metal Robot Spirits": bandaiDb.metalRobotSpiritsDao().insert(new MetalRobotSpiritsGundam(name, price, description, spareRunners, currentTime, photoUri)); break;
                    case "Metal Build": bandaiDb.metalBuildDao().insert(new MetalBuildGundam(name, price, description, spareRunners, currentTime, photoUri)); break;
                }
            } else {
                switch (currentCategory.getName()) {
                    case "SNAA": chinplaDb.snaaDao().insert(new SNAA(name, price, description, spareRunners, currentTime, photoUri)); break;
                    case "CangToys": chinplaDb.cangToysDao().insert(new CangToys(name, price, description, spareRunners, currentTime, photoUri)); break;
                    case "Blokees": chinplaDb.blokeesDao().insert(new Blokees(name, price, description, spareRunners, currentTime, photoUri)); break;
                }
            }

            runOnUiThread(() -> {
                loadItemsForCategory(currentCategory);
                setupFinancialSummary();
                Toast.makeText(this, "Kit salvato con foto!", Toast.LENGTH_SHORT).show();
            });
        });
    }

    private void setupFinancialSummary() {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase bandaiDb = AppDatabase.getInstance(getApplicationContext());
            ChinPlaDatabase chinplaDb = ChinPlaDatabase.getInstance(getApplicationContext());

            // 1. Recupero dei totali dai DAO Bandai
            Double egTotal = bandaiDb.entryGradeDao().getTotalPrice();
            Double hgTotal = bandaiDb.highGradeDao().getTotalPrice();
            Double rgTotal = bandaiDb.realGradeDao().getTotalPrice();
            Double mgTotal = bandaiDb.masterGradeDao().getTotalPrice();
            Double pgTotal = bandaiDb.perfectGradeDao().getTotalPrice();
            Double msTotal = bandaiDb.megaSizeDao().getTotalPrice();
            Double mbTotal = bandaiDb.metalBuildDao().getTotalPrice();
            Double mrsTotal = bandaiDb.metalRobotSpiritsDao().getTotalPrice(); // <--- VERIFICA QUESTA RIGA

            // 2. Recupero dei totali dai DAO Third Party
            Double snaaTotal = chinplaDb.snaaDao().getTotalPrice();
            Double cangToysTotal = chinplaDb.cangToysDao().getTotalPrice();
            Double blokeesTotal = chinplaDb.blokeesDao().getTotalPrice();

            // 3. Conversione in double sicura contro i null
            double egVal = egTotal != null ? egTotal : 0.0;
            double hgVal = hgTotal != null ? hgTotal : 0.0;
            double rgVal = rgTotal != null ? rgTotal : 0.0;
            double mgVal = mgTotal != null ? mgTotal : 0.0;
            double pgVal = pgTotal != null ? pgTotal : 0.0;
            double msVal = msTotal != null ? msTotal : 0.0;
            double mbVal = mbTotal != null ? mbTotal : 0.0;
            double mrsVal = mrsTotal != null ? mrsTotal : 0.0; // <--- DICHIARAZIONE mrsVal

            double snaaVal = snaaTotal != null ? snaaTotal : 0.0;
            double cangVal = cangToysTotal != null ? cangToysTotal : 0.0;
            double blokeesVal = blokeesTotal != null ? blokeesTotal : 0.0;

            // 4. Calcolo del totale generale
            double grandTotal = egVal + hgVal + rgVal + mgVal + pgVal + msVal + mbVal + mrsVal + snaaVal + cangVal + blokeesVal;

            List<String> expenseList = new ArrayList<>();
            expenseList.add(String.format(Locale.getDefault(), "Entry Grade: € %.2f", egVal));
            expenseList.add(String.format(Locale.getDefault(), "High Grade: € %.2f", hgVal));
            expenseList.add(String.format(Locale.getDefault(), "Real Grade: € %.2f", rgVal));
            expenseList.add(String.format(Locale.getDefault(), "Master Grade: € %.2f", mgVal));
            expenseList.add(String.format(Locale.getDefault(), "Perfect Grade: € %.2f", pgVal));
            expenseList.add(String.format(Locale.getDefault(), "Mega Size: € %.2f", msVal));
            expenseList.add(String.format(Locale.getDefault(), "Metal Build: € %.2f", mbVal));
            expenseList.add(String.format(Locale.getDefault(), "Metal Robot Spirits: € %.2f", mrsVal));
            expenseList.add(String.format(Locale.getDefault(), "SNAA: € %.2f", snaaVal));
            expenseList.add(String.format(Locale.getDefault(), "CangToys: € %.2f", cangVal));
            expenseList.add(String.format(Locale.getDefault(), "Blokees: € %.2f", blokeesVal));

            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        MainActivity.this,
                        android.R.layout.simple_dropdown_item_1line,
                        expenseList
                );
                binding.spinnerCategoryExpenses.setAdapter(adapter);
                binding.tvTotalCollectionPrice.setText(
                        String.format(Locale.getDefault(), "Totale Collezione: € %.2f", grandTotal)
                );
            });
        });
    }

    private void setupBackPressedHandler() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binding.recyclerViewItems.getVisibility() == View.VISIBLE) {
                    binding.recyclerViewItems.setVisibility(View.GONE);
                    if (binding.fabAddItem != null) {
                        binding.fabAddItem.setVisibility(View.GONE);
                    }
                    binding.recyclerViewCategories.setVisibility(View.VISIBLE);
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }
}