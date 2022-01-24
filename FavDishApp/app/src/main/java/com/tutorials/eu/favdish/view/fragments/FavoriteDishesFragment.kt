package com.tutorials.eu.favdish.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tutorials.eu.favdish.application.FavDishApplication
import com.tutorials.eu.favdish.databinding.FragmentFavoriteDishesBinding
import com.tutorials.eu.favdish.model.entities.FavDish
import com.tutorials.eu.favdish.view.activities.MainActivity
import com.tutorials.eu.favdish.view.adapters.FavDishAdapter
import com.tutorials.eu.favdish.viewmodel.FavDishViewModel
import com.tutorials.eu.favdish.viewmodel.FavDishViewModelFactory

class FavoriteDishesFragment : Fragment() {


    private var mBinding: FragmentFavoriteDishesBinding? = null


    private val mFavDishViewModel : FavDishViewModel by viewModels{
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding =
            FragmentFavoriteDishesBinding.inflate(inflater, container, false)

        return mBinding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Adicione um observador ao LiveData retornado por getAllDishesList.
         * O método onChanged() é acionado quando os dados
         * observados são alterados e a atividade está em primeiro plano.
         */
        mFavDishViewModel.favoriteDishes.observe(viewLifecycleOwner){
                dishes ->
            dishes.let{
                mBinding!!.rvFavoriteDishesList.layoutManager =
                    GridLayoutManager(requireActivity(), 2)
                val adapter = FavDishAdapter(this@FavoriteDishesFragment)
                mBinding!!.rvFavoriteDishesList.adapter = adapter

                if(it.isNotEmpty()){
                        mBinding!!.rvFavoriteDishesList.visibility = View.VISIBLE
                        mBinding!!.tvNoFavoriteDishesAddedYet.visibility = View.GONE

                        adapter.dishesList(it)

                }else{

                    mBinding!!.rvFavoriteDishesList.visibility = View.GONE
                    mBinding!!.tvNoFavoriteDishesAddedYet.visibility = View.VISIBLE
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()

        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
    }
    fun dishDetails(favDish: FavDish) {

        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.hideBottomNavigationView()
        }

        findNavController()
            .navigate(FavoriteDishesFragmentDirections.
            actionFavoriteDishesToDishDetails(favDish))
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}