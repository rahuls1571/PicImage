package com.example.picsumimage.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.picsumimage.model.PicSumModel
import com.example.picsumimage.pagination.ItemPagingSource
import com.example.picsumimage.retrofit.Retrofit
import com.example.picsumimage.retrofit.RetrofitInterface
import kotlinx.coroutines.flow.Flow

class PicSumViewModel : ViewModel() {
    // Initialize Retrofit interface
   var retrofitInterface : RetrofitInterface = Retrofit.getRetrofit().create(RetrofitInterface :: class.java)
    // Function return Paging Data
    fun getAllPhotos() : Flow<PagingData<PicSumModel>> {
        // Set PageSize and enable pLaceholders
       return  Pager( config = PagingConfig(6, enablePlaceholders = false),
       // Call Source Factory
        pagingSourceFactory = {ItemPagingSource(retrofitInterface)})
        // Set CachedIn for Not lossing Data will change orientation
                    .flow.cachedIn(viewModelScope)
    }

}