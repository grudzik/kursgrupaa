package com.example.homework1.course.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [(PokemonRecord::class), (PokeDexRecord::class), (OwnedPokemonRecord::class)],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    //abstract fun ownedPokemonsDao(): OwnedPokemonDao
    //abstract fun pokedexDao(): PokeDexDao


    companion object {

        private var sInstance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "poke-base"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }
    }

}