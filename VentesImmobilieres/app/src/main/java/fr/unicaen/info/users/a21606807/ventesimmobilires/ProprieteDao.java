package fr.unicaen.info.users.a21606807.ventesimmobilires;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface ProprieteDao {

    @Query("SELECT * FROM Propiete")
    List<Propriete> getAll();

    @Insert
    void insertAll(Propriete ... porietes);

    @Delete
    void delete(Propriete propriete);

}

/*

    def room_version = "1.1.1"

    implementation "android.arch.persistence.room:runtime:$room_version"
            annotationProcessor "android.arch.persistence.room:compiler:$room_version" // use kapt for Kotlin

            // optional - RxJava support for Room
            implementation "android.arch.persistence.room:rxjava2:$room_version"

            // optional - Guava support for Room, including Optional and ListenableFuture
            implementation "android.arch.persistence.room:guava:$room_version"

            // Test helpers
            testImplementation "android.arch.persistence.room:testing:$room_version"
*/
