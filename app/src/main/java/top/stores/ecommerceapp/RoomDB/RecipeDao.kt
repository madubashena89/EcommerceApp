package top.stores.ecommerceapp.RoomDB

import androidx.room.Insert
import androidx.room.Query
import top.stores.ecommerceapp.repository.Repo

interface RecipeDao {
    @get:Query("SELECT * FROM task")
    val all: List<Recipe?>?

    @Insert
    fun insert(recipe: List<Repo>?)
}