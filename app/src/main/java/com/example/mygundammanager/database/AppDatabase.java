package com.example.mygundammanager.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Import dei modelli
import com.example.mygundammanager.bandai.model.EntryGradeGundam;
import com.example.mygundammanager.bandai.model.HighGradeGundam;
import com.example.mygundammanager.bandai.model.RealGradeGundam;
import com.example.mygundammanager.bandai.model.MasterGradeGundam;
import com.example.mygundammanager.bandai.model.PerfectGradeGundam;
import com.example.mygundammanager.bandai.model.MetalBuildGundam;
import com.example.mygundammanager.bandai.model.MetalRobotSpiritsGundam;
import com.example.mygundammanager.bandai.model.MegaSizeGundam;

// Import dei DAO
import com.example.mygundammanager.bandai.dao.EntryGradeDao;
import com.example.mygundammanager.bandai.dao.HighGradeDao;
import com.example.mygundammanager.bandai.dao.RealGradeDao;
import com.example.mygundammanager.bandai.dao.MasterGradeDao;
import com.example.mygundammanager.bandai.dao.PerfectGradeDao;
import com.example.mygundammanager.bandai.dao.MetalBuildDao;
import com.example.mygundammanager.bandai.dao.MetalRobotSpiritsDao;
import com.example.mygundammanager.bandai.dao.MegaSizeDao;

@Database(
        entities = {
                EntryGradeGundam.class,
                HighGradeGundam.class,
                RealGradeGundam.class,
                MasterGradeGundam.class,
                PerfectGradeGundam.class,
                MetalBuildGundam.class,
                MetalRobotSpiritsGundam.class,
                MegaSizeGundam.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    // Metodi astratti per ottenere i DAO
    public abstract EntryGradeDao entryGradeDao();
    public abstract HighGradeDao highGradeDao();
    public abstract RealGradeDao realGradeDao();
    public abstract MasterGradeDao masterGradeDao();
    public abstract PerfectGradeDao perfectGradeDao();
    public abstract MetalBuildDao metalBuildDao();
    public abstract MetalRobotSpiritsDao metalRobotSpiritsDao();
    public abstract MegaSizeDao megaSizeDao();

    // Pattern Singleton per ottenere l'istanza unica del DB
    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "gundam_manager_database" // Nome del file SQLite salvato sul telefono
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}