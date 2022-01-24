package com.tutorials.eu.favdish.model.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.tutorials.eu.favdish.model.entities.FavDish
import kotlinx.coroutines.flow.Flow

/**
 * Um Repositório gerencia consultas e permite que você use vários backend.
 *
 * O DAO é passado para o construtor do repositório em oposição a todo o banco de dados.
 * Isso ocorre porque ele só precisa de acesso ao DAO, uma vez que o DAO contém todos os métodos
 * de leitura / gravação para o banco de dados.
 * Não há necessidade de expor todo o banco de dados ao repositório.
 * @param favDishDao - Passe o FavDishDao como o parâmetro.
 */
class FavDishRepository(private val favDishDao: FavDishDao) {

    /**
     * Por padrão, a Room executa consultas suspensas fora do thread principal, portanto, não
     * precisamos implementar qualquer outra coisa para garantir que não estamos fazendo
     * um trabalho de banco de dados de longa duração fora do tópico principal.
     */
    @WorkerThread
    suspend fun insertFavDishData(favDish: FavDish) {
        favDishDao.insertFavDishDetails(favDish)
    }

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allDishesList: Flow<List<FavDish>> = favDishDao.getAllDishesList()

    @WorkerThread
    suspend fun updateFavDishData(favDish: FavDish){
        favDishDao.updateFavDishDetails(favDish)
    }


    val favoriteDishes : Flow<List<FavDish>> = favDishDao.getFavoritesDishesList()
}