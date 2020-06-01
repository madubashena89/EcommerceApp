package top.stores.ecommerceapp.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName
import top.stores.ecommerceapp.model.Employee

@Entity(tableName = "infomation_table")
data class EmployeeEntity(

    @PrimaryKey(autoGenerate = true)
    var id :Int = 0,

    @ColumnInfo(name = "first_name")
    @SerializedName("firstname")
    var firstName: String? = null,

    @ColumnInfo(name = "last_name")
    @SerializedName("lastname")
    var lastName: String? = null,

    @ColumnInfo(name = "age")
    @SerializedName("age")
    var age: Int? = null

)