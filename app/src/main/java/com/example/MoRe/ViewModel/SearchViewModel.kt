package com.example.MoRe.ViewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.SearchWidgetState
import com.example.MoRe.model.getPabrik
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.res.Data
import com.example.MoRe.network.model.res.DataPabrik
import com.example.MoRe.network.model.res.getpabrik.DataArr
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrik
import com.example.MoRe.network.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(

    val repository: Repository): ViewModel(){

    private val  _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val  _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    val PabrikList = mutableListOf<List<DataPabrik>>()


    fun getPabriks(query: String): List<DataPabrik> {

        val filteredList = linkedSetOf<DataPabrik>()

        PabrikList.forEach { list: List<DataPabrik> ->

            list.forEach { tutorialSectionModel ->

                if (tutorialSectionModel.nama_pabrik.contains(query, ignoreCase = true)) {
                    filteredList.add(tutorialSectionModel)
                }


                }
            }


//        println("🤖 ViewModel Query: $query, filteredList: ${filteredList.size}")

        return filteredList.toList()
    }

//
//
//     fun loadView(){
//        viewModelScope.launch{
//            val result = repository.getPabrik()
//
//            when (result){
////                is Resource.Success(DataArr) ->
////            {}
//
//            }
//            val loadPabrik = Resource.Success(result).data?.body()
//
//
//        }
//    }
//    fun listPabrik () : List<DataPabrik>{
//        val data = listOf<DataPabrik>()
//        val pabrikList = ArrayList<DataPabrik>()
//        data.forEach {
//            pabrikList.add(DataPabrik(updateSearchTextState(newValue = searchTextState)))
//        }
//        return pabrikList
//    }
//
//    private val _ListPabrik = MutableLiveData<List<DataPabrik>>()
//    val list : LiveData<List<DataPabrik>>
//        get() = _ListPabrik
//
//    fun performQuery(
//        query: String,
//        resPabrik: DataPabrik
//    ) {
//        val filteredList = resPabrik
//        DataPabrik(resPabrik).filter { Pabrik ->
//            if (Pabrik.namaPabrik.contains(query.trim(), ignoreCase = true)){
//                filteredList.add(DataPabrik(Pabrik.namaPabrik))
//            }
//        }
//        _ListPabrik.postValue(filteredList)
//    }

    var listPabrik = mutableStateOf<List<DataPabrik>>(listOf())
    var _listPabrik = mutableStateOf<ResGetPabrik?>(null)


    private var cachedListPabrik = listOf<DataPabrik>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    fun searchPabrikList(query: String) {
        val listToSearch = if(isSearchStarting) {
            listPabrik.value
        } else {
            cachedListPabrik
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                listPabrik.value = cachedListPabrik
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.nama_pabrik.contains(query.trim(), ignoreCase = true) ||
                        it.id_pabrik == query.trim()
            }
            if(isSearchStarting) {
                cachedListPabrik = listPabrik.value
                isSearchStarting = false
            }
            listPabrik.value = results
            isSearching.value = true
        }
    }


    fun updateSearchWidgetState(newValue: SearchWidgetState){
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String){
        _searchTextState.value = newValue
    }

}