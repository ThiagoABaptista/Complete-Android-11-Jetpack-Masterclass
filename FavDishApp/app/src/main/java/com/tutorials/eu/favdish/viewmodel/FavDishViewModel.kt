package com.tutorials.eu.favdish.viewmodel

import androidx.lifecycle.*
import com.tutorials.eu.favdish.model.database.FavDishRepository
import com.tutorials.eu.favdish.model.entities.FavDish
import kotlinx.coroutines.launch

/**
 * A função do ViewModel é fornecer dados para a UI e sobreviver às mudanças de configuração.
 * Um ViewModel atua como um centro de comunicação entre o Repositório e a UI.
 * Você também pode usar um ViewModel para compartilhar dados entre fragmentos.
 * O ViewModel faz parte da biblioteca do ciclo de vida.
 *
 */
class FavDishViewModel(private val repository: FavDishRepository) : ViewModel() {

    /**
     * Lançamento de uma nova co-rotina para inserir os dados de forma não bloqueadora.
     */
    fun insert(dish: FavDish) = viewModelScope.launch {
        //Chama a função do repositorio e passa os detalhes
        repository.insertFavDishData(dish)
    }

    // TODO Step 3: Get all the dishes list from the database in the ViewModel to pass it to the UI.
    // START
    /**
     * Usar LiveData e armazenar em cache o que allDishes retorna tem vários benefícios:
     * Podemos colocar um observador nos dados (em vez de pesquisar as alterações) e apenas
     * atualizar a UI quando os dados realmente mudarem.
     * O repositório é completamente separado da UI por meio do ViewModel.
     */
    val allDishesList: LiveData<List<FavDish>> = repository.allDishesList.asLiveData()
    // END
}

/**
 * Para criar o ViewModel, implementamos um ViewModelProvider.Factory que obtém como parâmetro as dependências
 * necessárioas para criar FavDishViewModel: o FavDishRepository.
 * Ao usar viewModels e ViewModelProvider.Factory, a estrutura cuidará do lifecycle do ViewModel.
 * Ele sobreviverá às mudanças de configuração e mesmo se a atividade for recriada,
 * você sempre obterá a instância correta da classe FavDishViewModel.
 */
class FavDishViewModelFactory(private val repository: FavDishRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavDishViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavDishViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}