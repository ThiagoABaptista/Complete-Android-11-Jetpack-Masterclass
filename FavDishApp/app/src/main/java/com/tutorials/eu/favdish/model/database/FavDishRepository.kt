package com.tutorials.eu.favdish.model.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tutorials.eu.favdish.model.entities.FavDish
import kotlinx.coroutines.flow.Flow


class FavDishRepository(private val favDishDao: FavDishDao) {

    @WorkerThread
    suspend fun insertFavDishData(favDish: FavDish) {
        favDishDao.insertFavDishDetails(favDish)
    }

    // TODO Step 2: Create a variable for the dishes list to access it from ViewModel.
    // START
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allDishesList: Flow<List<FavDish>> = favDishDao.getAllDishesList()
    // END
}