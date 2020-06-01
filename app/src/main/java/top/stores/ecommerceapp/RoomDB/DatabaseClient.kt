//package top.stores.ecommerceapp.RoomDB
//
//import android.content.Context
//import androidx.room.Room
//
//
//class DatabaseClient(mCtx: Context) {
//    private val mCtx: Context
//
//    //our app database object
//    val appDatabase: AppDatabase
//
//    companion object {
//        private var mInstance: DatabaseClient? = null
//
//        @Synchronized
//        fun getInstance(mCtx: Context): DatabaseClient? {
//            if (mInstance == null) {
//                mInstance = DatabaseClient(mCtx)
//            }
//            return mInstance
//        }
//    }
//
//    init {
//        this.mCtx = mCtx
//
//        //creating the app database with Room database builder
//        //alldata is the name of the database
//        appDatabase =
//            Room.databaseBuilder(mCtx, AppDatabase::class.java, "alldata").build()
//    }
//}
