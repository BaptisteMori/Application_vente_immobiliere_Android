package fr.unicaen.info.users.a21606807.ventesimmobilires;

import android.arch.persistence.room.Database;

@Database(entities = {Propriete.class}, version = 1)
public abstract class AppDatabase {
    public abstract ProprieteDao prorieteDao();
}

/* a mettre dans le code

https://developer.android.com/training/data-storage/room/#java

AppDatabase db = Room.databaseBuilder(getApplicationContext(),
        AppDatabase.class, "database-name").build();

 */
