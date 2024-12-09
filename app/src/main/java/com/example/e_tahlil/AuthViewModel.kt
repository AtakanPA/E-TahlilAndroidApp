package com.example.e_tahlil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel:ViewModel() {

    private val auth: FirebaseAuth =FirebaseAuth.getInstance()
    private val firestore:FirebaseFirestore=FirebaseFirestore.getInstance()
    private val _authState=MutableLiveData<AuthState>()
    val authState:LiveData<AuthState> = _authState

    private val _userRole=MutableLiveData<String>()
    val userRole:LiveData<String> = _userRole

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){

        if(auth.currentUser==null){

            _authState.value=AuthState.Unauthenticated
        }else{
            _authState.value=AuthState.Authenticated
            fetchUserRole(auth.currentUser!!.uid);
        }



    }

    fun fetchUserRole(userId:String){
        firestore.collection("users").document(userId).get().addOnSuccessListener {
            document->
            if(document.exists()){
                val role=document.getString("role")
                _userRole.value=role ?: "user"
            }




        }
            .addOnFailureListener{
                exception->
                _userRole.value="user"
                exception.printStackTrace()
            }





    }


    fun login(email:String,password:String){
        if(email.isEmpty()||password.isEmpty()) {
            _authState.value=AuthState.Error("Email ve şifre boş olamaz")
            return

        }

        _authState.value=AuthState.Loading
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            task->
            if(task.isSuccessful){
                _authState.value=AuthState.Authenticated
                auth.currentUser?.uid?.let { fetchUserRole(it) } // Kullanıcı giriş yapınca rolünü kontrol et
            }
            else{
                _authState.value=AuthState.Error(task.exception?.message ?: "Bir şeyler yanlış gitti")


            }


        }

    }
    fun signup(email:String,password:String){
        if(email.isEmpty()||password.isEmpty()) {
            _authState.value=AuthState.Error("Email ve şifre boş olamaz")
            return

        }

        _authState.value=AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                task->
            if(task.isSuccessful){
                _authState.value=AuthState.Authenticated
                auth.currentUser?.uid?.let { uid->

                    firestore.collection("users").document(uid).set(mapOf("role" to "user"))
                        .addOnSuccessListener {
                            fetchUserRole(uid);

                        }

                }
            }
            else{
                _authState.value=AuthState.Error(task.exception?.message ?: "Bir şeyler yanlış gitti")


            }


        }

    }
    fun signout()
    {

        auth.signOut()
        _authState.value=AuthState.Unauthenticated
        _userRole.value=""

    }
}



sealed class AuthState{
    object Authenticated:AuthState()
    object Unauthenticated:AuthState()
    object Loading:AuthState()
    data class Error(val message:String):AuthState()

}