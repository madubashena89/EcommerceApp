package top.stores.ecommerceapp.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "infomation_table")
data class Recipe (
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0,

    @ColumnInfo(name = "t_name")
    var name: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "price")
    var price :Double = 0.0,

    @ColumnInfo(name = "thumbnail")
    var thumbnail: String? = null,

    @ColumnInfo(name = "chef")
    var chef: String? = null,

    @ColumnInfo(name = "timestamp")
    var timestamp: String? = null

)