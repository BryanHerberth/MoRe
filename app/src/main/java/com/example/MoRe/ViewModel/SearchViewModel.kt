package com.example.MoRe.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.SearchWidgetState
import com.example.MoRe.network.model.res.Data
import com.example.MoRe.network.model.res.DataPabrik
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel(){

    private val  _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val  _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    var listPabrik = mutableStateOf<List<DataPabrik>>(listOf())

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