package com.example.recipe.adapter

import android.graphics.Color.rgb
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.databinding.ListLayoutBinding
import com.example.recipe.model.Recipe
import com.example.recipe.utils.layoutInflater

class RecipeAdapter(
    //private val recipes : List<Recipe>,
    private val selectedRecipe : (Recipe) -> Unit
):RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val list = mutableListOf<Recipe>()
    var oldItem : View? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RecipeViewHolder.getInstance(parent).apply {
        itemView.setOnClickListener{selectedRecipe(list[adapterPosition])
            oldItem.let { it?.setBackgroundColor(rgb(255,255,255)) }

            itemView.setBackgroundColor(rgb(209,238,245))
            oldItem= itemView
    }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.loadRecipe(list[position])
    }

    override fun getItemCount() = list.size

    class RecipeViewHolder(private val binding: ListLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun loadRecipe(recipe : Recipe)=with(binding){
            tvName.text = recipe.name
            tvDescription.text = recipe.description
            tvIngredients.text = recipe.ingredients
        }

        companion object{
            fun getInstance(parent : ViewGroup) = ListLayoutBinding.inflate(parent.layoutInflater,parent,false)
                .run{RecipeViewHolder(this)}
        }

    }

    fun updateUrls(list: List<Recipe>){
        //val size = this.list.size
        this.list.clear()
        //clear()
      //  notifyItemRangeRemoved(0,size)

        this.list.addAll(list)
      //  notifyItemRangeInserted(0, list.size)
    }
}