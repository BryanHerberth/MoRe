package com.example.MoRe.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.PabrikSearchState
import com.example.MoRe.model.SearchWidgetState
import com.example.MoRe.network.model.res.Data
import com.example.MoRe.network.model.res.DataPabrik
import com.example.MoRe.network.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository): ViewModel(){

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



    init {
        getPabrikData()
    }

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

    fun getPabrikData()
    {
        viewModelScope.launch {
            repository.getPabrik()
        }
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState){
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String){
        searchText.value = newValue
        if (newValue.isEmpty()) {
            matchedPabrik.value = arrayListOf()
            return
        }
        val PabrikFromSearch = allPabrik.filter { x ->
            x.nama_pabrik.contains(newValue, true)
        }
        matchedPabrik.value = PabrikFromSearch
    }


    private var allPabrik: ArrayList<DataPabrik> = ArrayList<DataPabrik>()
    private val searchText: MutableStateFlow<String> = MutableStateFlow("")
    private var matchedPabrik: MutableStateFlow<List<DataPabrik>> = MutableStateFlow(arrayListOf())

    val PabrikSearchState = combine(
        searchText,
        matchedPabrik
    ) {
            text, matchedUsers ->

        PabrikSearchState(
            text,
            matchedUsers,
        )
    }

    fun onSearchTextChanged(changedSearchText: String) {
        searchText.value = changedSearchText
        if (changedSearchText.isEmpty()) {
            matchedPabrik.value = arrayListOf()
            return
        }
        val PabrikFromSearch = allPabrik.filter { x ->
            x.nama_pabrik.contains(changedSearchText, true)
        }
        matchedPabrik.value = PabrikFromSearch
    }
}