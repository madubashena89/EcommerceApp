package top.stores.ecommerceapp.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmployeeDao {

    @get:Query("SELECT * FROM infomation_table")
    val all: List<EmployeeEntity?>?

    @Insert
    fun insert(employee : List<EmployeeEntity>?)

    @Query("DELETE FROM infomation_table")
    fun deleteAll()

    @Query("SELECT * from infomation_table ORDER BY id ASC")
    fun getEmployees() : LiveData<List<EmployeeEntity>>
}