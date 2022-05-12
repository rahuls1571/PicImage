package com.example.picsumimage.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.picsumimage.model.PicSumModel
import com.example.picsumimage.retrofit.RetrofitInterface


// Created Constructor
// Implements Paging Source class
class ItemPagingSource(private val retrofitInterface: RetrofitInterface) : PagingSource<Int, PicSumModel>()
{

    override fun getRefreshKey(state: PagingState<Int, PicSumModel>): Int? {
        // Return Anchor Position
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicSumModel>{

        return try {
            // Params know page position
            // Set page At 0
            val page : Int = params.key ?: 0
            // Call API Data/ Fetch Data
            val response = retrofitInterface.getModel(page)
            // SET Data , PrevKey/ Prev Page , NextKey / Next Page
            LoadResult.Page(
                    data = ((response)),
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if ((response).isEmpty()) null else page + 1
                )
        } catch (e: Exception){
             // Exception
             LoadResult.Error(e)
         }

    }






}


