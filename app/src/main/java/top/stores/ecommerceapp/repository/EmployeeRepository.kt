package top.stores.ecommerceapp.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import top.stores.ecommerceapp.RoomDB.EmplloyeeDatabase
import top.stores.ecommerceapp.RoomDB.EmployeeDao
import top.stores.ecommerceapp.RoomDB.EmployeeEntity

class EmployeeRepository(application: Application) {

    private var employeeDao: EmployeeDao

    private var allEmployees: LiveData<List<EmployeeEntity>>

    init {
        val empDatabase: EmplloyeeDatabase = EmplloyeeDatabase.getDatabase(
            application.applicationContext
        )!!
        employeeDao = empDatabase.employeeeDao()
        allEmployees = employeeDao.getEmployees()
    }

    fun insertEmployee(employee: List<EmployeeEntity>?) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(employeeDao).execute(employee)
    }

    fun deleteAllEmployees() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            employeeDao
        ).execute()
    }

    fun getAllEmployees(): LiveData<List<EmployeeEntity>> {
        return allEmployees
    }

    private class InsertNoteAsyncTask(empDao: EmployeeDao) : AsyncTask<List<EmployeeEntity>?, Unit, Unit>() {
        val empDao = empDao

        override fun doInBackground(vararg p0: List<EmployeeEntity>?) {
            empDao.insert(p0[0]!!)
        }

    }


    private class DeleteAllNotesAsyncTask(val empDao: EmployeeDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            empDao.deleteAll()
        }
    }

}