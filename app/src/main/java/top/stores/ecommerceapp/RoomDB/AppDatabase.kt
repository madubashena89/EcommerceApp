package top.stores.ecommerceapp.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [EmployeeEntity::class], version = 1, exportSchema = false)
abstract class EmplloyeeDatabase : RoomDatabase() {
    abstract fun employeeeDao(): EmployeeDao

    companion object {

        @Volatile
        public var INSTANCE: EmplloyeeDatabase? = null

        fun getDatabase(context: Context): EmplloyeeDatabase? {
            if (INSTANCE == null) {
                synchronized(EmplloyeeDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            EmplloyeeDatabase::class.java, "employee_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}