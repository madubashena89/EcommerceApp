package top.stores.ecommerceapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import top.stores.ecommerceapp.RoomDB.EmployeeEntity
import top.stores.ecommerceapp.repository.EmployeeRepository

public class EmployeeViewModel (application: Application) : AndroidViewModel(application){

    private var empRepository: EmployeeRepository = EmployeeRepository(application)
    private var allEmployees: LiveData<List<EmployeeEntity>> = empRepository.getAllEmployees()

    fun insertEmployee(employee: List<EmployeeEntity>?) {
        empRepository.insertEmployee(employee)
    }

    fun deleteAllEmployees() {
        empRepository.deleteAllEmployees()
    }

    fun getAllEMployees(): LiveData<List<EmployeeEntity>> {
        return allEmployees
    }
}