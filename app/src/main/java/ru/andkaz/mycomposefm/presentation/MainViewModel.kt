package ru.andkaz.mycomposefm.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.andkaz.mycomposefm.damain.models.SaveUserNameParam
import ru.andkaz.mycomposefm.damain.models.UserName
import ru.andkaz.mycomposefm.damain.usecase.GetUserNameUseCase
import ru.andkaz.mycomposefm.damain.usecase.SaveUserNameUseCase

//
class MainViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase
): ViewModel() {
    private var resultLiveMutable = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultLiveMutable


    init{
        Log.e("AAA","VM created")
    }

    override fun onCleared() {
        Log.e("AAA","VM cleared")
        super.onCleared()

    }
    /*
    fun getResultLive():LiveData<String>{
        return resultLive
    }*/

    fun save(text:String){
        val params = SaveUserNameParam(name=text)
        val resultData =saveUserNameUseCase.execute(param=params) // by lazy
        //dataTextView.text ="Save result = $
        resultLiveMutable.value="Save result = ${resultLive.value.toString()}"
        //return result

    }
    fun load(){
        val userName: UserName =getUserNameUseCase.execute()
        resultLiveMutable.value="${userName.firstName} ${userName.lastName}"


        //return  result//getUserNameUseCase.execute().firstName

    }

}