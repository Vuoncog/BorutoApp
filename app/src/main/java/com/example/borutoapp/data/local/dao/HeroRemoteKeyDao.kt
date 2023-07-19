package com.example.borutoapp.data.local.dao

import androidx.room.*
import com.example.borutoapp.domain.model.HeroRemoteKey
import com.example.borutoapp.utilities.Constants.HERO_REMOTE_KEY_DATABASE_TABLE

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT * FROM $HERO_REMOTE_KEY_DATABASE_TABLE WHERE id=:id")
    fun getRemoteKey(id: Int): HeroRemoteKey?

    @Query("DELETE FROM $HERO_REMOTE_KEY_DATABASE_TABLE")
    fun deleteAllRemoteKey()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRemoteKeys(heroRemoteKeys: List<HeroRemoteKey>)
}