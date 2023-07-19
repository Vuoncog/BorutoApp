package com.example.borutoapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.utilities.Constants.HERO_DATABASE_TABLE

@Dao
interface HeroDao {

    @Query("SELECT * FROM $HERO_DATABASE_TABLE ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM $HERO_DATABASE_TABLE WHERE id=:heroId")
    fun getSelectedHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHeroes(heroes: List<Hero>)

    @Query("DELETE FROM $HERO_DATABASE_TABLE")
    fun deleteAllHeroes()
}