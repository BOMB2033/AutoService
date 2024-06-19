package com.example.aitoservice.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class RepositoryInMemoryImpl {

    private var databaseUsersReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
    private var databaseServicesReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("services")
    private val uid:String = Firebase.auth.currentUser!!.uid

    var dataClass = DataClass(
        user = User(),
        emptyList(),
    )
    private val data = MutableLiveData(dataClass)

    fun getAll() = data

    fun loadUser() {
        val listener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.mapNotNull { it.getValue(User::class.java) }.forEach{
                    if (it.uid == uid)
                        dataClass.user = it
                }
                data.value = dataClass
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        }
        databaseUsersReference.addValueEventListener(listener)
    }

    fun addService(uidUser: String, uidService:String) {
        dataClass.user = dataClass.user.copy(service = dataClass.user.service.plus(uidService))
        data.value = dataClass
        databaseUsersReference.child(uidUser).removeValue()
        databaseUsersReference.child(uidUser).setValue(dataClass.user)
    }
    fun addUser(email:String) {
        dataClass.user = User(uid, email)
        data.value = dataClass
        databaseUsersReference.child(uid).removeValue()
        databaseUsersReference.child(uid).setValue(dataClass.user)
    }

    fun loadServices() {
        val listener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataClass.services = emptyList()
                dataSnapshot.children.mapNotNull { it.getValue(Service::class.java) }.forEach{
                        dataClass.services = dataClass.services.plus(it)
                }
                data.value = dataClass
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        }
        databaseServicesReference.addValueEventListener(listener)
        databaseServicesReference.setValue(
            listOf(
                Service("1", "Диагностика ходовой"),
                Service("2", "Диагностика ЭБУ"),
                Service("3", "Замена масел"),
                Service("4", "Замена тормозных дисков"),
                Service("5", "Замена ремня ГРМ"),
                Service("6", "Замена цепи ГРМ"),
                Service("7", "Замена подушек двигателя"),
                Service("8", "Ремонт ГБЦ"),
                Service("9", "Замена ступичных подшипников"),
                Service("10", "Замена предохранителей"),
                Service("11", "Замена осветительных элементов"),
                Service("12", "Покраска"),
                Service("13", "Грунтовка"),
                Service("14", "Выпрямление мятен"),
            )
        )
    }
}


class DataViewModel : ViewModel() {
    private val repository = RepositoryInMemoryImpl()
    val data = repository.getAll()
    val uid:String = Firebase.auth.currentUser!!.uid
    fun addService(uidUser: String, uidService: String) = repository.addService(uidUser, uidService)
    fun addUser(email: String) = repository.addUser(email)
    fun loadUser() = repository.loadUser()
    fun loadServices() = repository.loadServices()
}