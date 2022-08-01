package com.example.MoRe.network.viewModel.start

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MoRe.network.model.ResStart
import com.example.MoRe.network.model.base.BaseResponse
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.repository.Repository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

//class StartViewModel(private val repository: Repository): ViewModel() {
//    var startResponseVM = MutableLiveData<Resource<Response<ResStart>>>()
//    var res1: ResStart = ResStart("", "")
//    var res2: ResStart by mutableStateOf(ResStart("", ""))
//
//    fun getStart( context: Context){
//        viewModelScope.launch(Dispatchers.IO){
//
//            try {
//                val response = repository.getStart()
//                viewModelScope.launch(Dispatchers.Main){
//                    startResponseVM.value = Resource.Success(response)
//                    res2 = Resource.Success(response).data?.body()!!
//                    Log.e("Masuk view model", res2.toString())
//                }
//            } catch (cause: Throwable){
//                viewModelScope.launch(Dispatchers.Main){
//                    startResponseVM.value = Resource.Error("start Gagal", null)
//                }
//                when (cause) {
//                    is HttpException -> {
//                        cause.response()?.errorBody()?.source()?.let{
//                            val error = Gson().fromJson(
//                                it.readString(Charsets.UTF_8),
//                                BaseResponse::class.java
//                            )
//                            viewModelScope.launch (Dispatchers.Main){
//                                startResponseVM.value = Resource.Error(error.errorMsg.toString(), null)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){
   var startResponseVM = MutableLiveData<Resource<Response<ResStart>>>()
    var res1: ResStart = ResStart("", "")
    var res2:ResStart by mutableStateOf(ResStart("", ""))

    fun getStart() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = repository.getStart()
                viewModelScope.launch(Dispatchers.Main){
                    startResponseVM.value = Resource.Success(response)
                    res2 = Resource.Success(response).data?.body()!!
                    Log.e("Masuk view model", res2.toString())
                }
            } catch (cause: Throwable){
                viewModelScope.launch(Dispatchers.Main){
                    startResponseVM.value = Resource.Error("start Gagal", null)
                }
                when (cause) {
                    is HttpException -> {
                        cause.response()?.errorBody()?.source()?.let{
                            val error = Gson().fromJson(
                                it.readString(Charsets.UTF_8),
                                BaseResponse::class.java
                            )
                            viewModelScope.launch (Dispatchers.Main){
                                startResponseVM.value = Resource.Error(error.errorMsg.toString(), null)
                            }
                        }
                    }
                }
            }
        }
    }
}