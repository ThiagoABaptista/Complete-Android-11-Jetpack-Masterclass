package com.tutorials.eu.favdish.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tutorials.eu.favdish.model.entities.FavDish
import kotlinx.coroutines.flow.Flow

@Dao
interface FavDishDao {

    /**
     * Todas as queries devem ser executadas em uma thread separada. Elas não podem ser executados a partir da thread principal ou causarão um travamento.
     * Room tem suporte para corrotinas Kotlin.
     * Isso permite que suas queries sejam anotadas com o modificador de suspensão e, em seguida, chamadas de uma co-rotina
     * ou de outra função de suspensão.
     */

    /**
     * Uma função para inserir detalhes de pratos favoritos no banco de dados local usando o Room.
     *
     * @param favDish - Aqui passaremos a classe de entidade que criamos.
     */
    @Insert
    suspend fun insertFavDishDetails(favDish: FavDish)

    /**
     * Quando os dados são alterados, geralmente você deseja realizar alguma ação, como exibir os dados atualizados na UI.
     * Isso significa que você deve observar os dados para que, quando eles mudem, você possa reagir.
     *
     * Para observar as alterações de dados, usaremos o Flow from kotlinx-coroutines.
     * Use um valor de retorno do tipo Flow na descrição do seu método,
     * e o Room gera todo o código necessário para atualizar o Flow quando o banco de dados é atualizado.
     *
     * Um Flow é uma sequência assíncrona de valores
     * O Flow produz valores um por vez (em vez de todos de uma vez) que podem gerar valores de operações assíncronas
     * como solicitações de rede, chamadas de banco de dados ou outro código assíncrono.
     * Suporta co-rotinas em sua API, então você também pode transformar um Flow usando co-rotinas!
     */
    @Query("SELECT * FROM FAV_DISHES_TABLE ORDER BY ID")
    fun getAllDishesList(): Flow<List<FavDish>>

    @Update
    suspend fun updateFavDishDetails(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE WHERE favorite_dish = 1")
    fun getFavoritesDishesList() : Flow<List<FavDish>>
}