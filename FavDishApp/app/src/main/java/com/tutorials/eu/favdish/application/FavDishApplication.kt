package com.tutorials.eu.favdish.application

import android.app.Application
import com.tutorials.eu.favdish.model.database.FavDishRepository
import com.tutorials.eu.favdish.model.database.FavDishRoomDatabase

/**
 * Uma classe application onde podemos definir o escopo variavel para usarmos na aplicação.
 */
class FavDishApplication : Application() {

    /**
     * Usando by lazy para que o database e o repositório seja criado apenas quando necessário
     * em vez quando a aplicação inicia.
     */
    /**
     *
     * A palavra-chave "lazy" usada para criar a nova instância que usa a função de inicialização especificada
     * e o modo thread-safety padrão [LazyThreadSafetyMode.SYNCHRONIZED].
     *
     * Se a inicialização de um valor lançar uma exceção, ele tentará reinicializar o valor no próximo acesso.
     *
     * Observe que a instância retornada usa a si mesma para sincronizar. Não sincronize a partir de código externo em
     * a instância retornada, pois pode causar um deadlock acidental. Além disso, esse comportamento pode ser alterado no futuro.
     */
    private val database by lazy { FavDishRoomDatabase.getDatabase(this@FavDishApplication) }

    /**
     * Usando by lazy para que o banco de dados e o repositório sejam criados apenas quando forem necessários
     * em vez de quando o aplicativo é iniciado.
     */
    // A variable para o repository.
    val repository by lazy { FavDishRepository(database.favDishDao()) }
}