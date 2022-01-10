package com.tutorials.eu.favdish.view.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutorials.eu.favdish.databinding.ItemCustomListLayoutBinding
import com.tutorials.eu.favdish.view.activities.AddUpdateDishActivity

class CustomListItemAdapter(
        private val activity: Activity,
        private val listItems: List<String>,
        private val selection: String
) :
        RecyclerView.Adapter<CustomListItemAdapter.ViewHolder>() {

    /**
     * Infla as views do item que são projetadas no arquivo de layout xml
     *
     * crie um novo
     * {@link ViewHolder} e inicializa alguns campos privados a serem usados pelo RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCustomListLayoutBinding =
                ItemCustomListLayoutBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }

    /**
     * Vincula cada item na ArrayList a uma view
     *
     * Chamado quando RecyclerView precisa de um novo {@link ViewHolder} do tipo fornecido
     * para representar um item.
     *
     * Este novo ViewHolder deve ser construído com uma nova View que pode representar os itens
     * do tipo fornecido. Você pode criar uma nova View manualmente ou inflar uma a partir de um XML
     * arquivo de layout.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = listItems[position]

        holder.tvText.text = item

        holder.itemView.setOnClickListener {

            if (activity is AddUpdateDishActivity) {
                activity.selectedListItem(item, selection)
            }
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return listItems.size
    }

    /**
     * Um ViewHolder descreve uma visão de item e metadados sobre seu lugar no RecyclerView.
     */
    class ViewHolder(view: ItemCustomListLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        // Holds the TextView that will add each item to
        val tvText = view.tvText
    }
}