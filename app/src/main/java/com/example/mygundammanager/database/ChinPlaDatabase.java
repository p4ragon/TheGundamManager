package com.example.mygundammanager.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Import dei modelli Third Party
import com.example.mygundammanager.thirdparty.model.SNAA;
import com.example.mygundammanager.thirdparty.model.CangToys;
import com.example.mygundammanager.thirdparty.model.Blokees; // 1. AGGIUNTO MODEL BLOKEES

// Import dei DAO Third Party
import com.example.mygundammanager.thirdparty.dao.SNAADao;
import com.example.mygundammanager.thirdparty.dao.CangToysDao;
import com.example.mygundammanager.thirdparty.dao.BlokeesDao; // 2. AGGIUNTO DAO BLOKEES

@Database(
        entities = {
                SNAA.class,
                CangToys.class,
                Blokees.class // 3. REGISTRATA ENTITY BLOKEES
        },
        version = 2, // 4. INCREMENTATA LA VERSION A 2 PER REGISTRARE LA NUOVA TABELLA
        exportSchema = false
)
public abstract class ChinPlaDatabase extends RoomDatabase {

    private static volatile ChinPlaDatabase INSTANCE;

    // Metodi astratti per ottenere i DAO
    public abstract SNAADao snaaDao();
    public abstract CangToysDao cangToysDao();
    public abstract BlokeesDao blokeesDao(); // 5. METODO PER ESPORRE BLOKEESDAO

    // Pattern Singleton per ottenere l'istanza unica del DB dei ChinPla
    public static ChinPlaDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ChinPlaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    ChinPlaDatabase.class,
                                    "chinpla_manager_database"
                            )
                            .fallbackToDestructiveMigration() // Distrugge e ricrea il DB se lo schema cambia in dev
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}