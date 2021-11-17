package com.example.recipe.view

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.example.recipe.R
import com.example.recipe.adapter.RecipeAdapter
import com.example.recipe.databinding.HomeLayoutBinding
import com.example.recipe.databinding.ListLayoutBinding
import com.example.recipe.model.Recipe
import com.example.recipe.repo.RecipeRepo
import com.example.recipe.viewmodel.RecipeViewModel


class HomeFragment: Fragment() {

    private var _binding : HomeLayoutBinding?= null
    val binding get() = _binding!!
    var selected : Recipe? = null
    var id1 =0
    var idSave =0

    private val viewModel: RecipeViewModel by viewModels {
        RecipeViewModel.Factory(RecipeRepo(context?.applicationContext as Application))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = HomeLayoutBinding.inflate(inflater, container, false)
        .also { _binding = it }.root

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val r1 =Recipe(id1++,"pizza","pizza for yo momma","pizza")
        val r2 =Recipe(id1++,"chicken","pizza","pizza")
        val r4 =Recipe(id1++,"daisies","pizza","pizza")
        val r5 =Recipe(id1++,"firefly","pizza","herbs, pickles, and cheese")
        //val r3 : List<Recipe> = listOf(r1,r2,r4,r5)
       // val r6 = Recipe(autpo,"pai","osdf","osodf")
        viewModel.addrecipe(r1)
        viewModel.addrecipe(r2)
        viewModel.addrecipe(r4)
        viewModel.addrecipe(r5)
        Log.d("Initial",viewModel.recipes.value.toString())

        viewModel.recipes.observe(viewLifecycleOwner){
            binding.rvRecs.adapter = RecipeAdapter(::selectedRecipe)
            (binding.rvRecs.adapter as RecipeAdapter).updateUrls(it)
            Log.d("MainActivity", "onCreate: $it")


        }


        binding.btnAdd.setOnClickListener(){
           // viewModel.addrecipe(r5)
           // Log.d("add",viewModel.recipes.value.toString())
         //   Log.d("Home","add clicked")
            id1++
            edits()
        }

        binding.btnEdit.setOnClickListener() {
            binding.etName.setText(selected!!.name)
            binding.etDesc.setText(selected!!.description)
            binding.etIng.setText(selected!!.ingredients)
            idSave = selected!!.id!!
            id1 = idSave
            edits()
        }

        binding.btnDelete.setOnClickListener(){
            Log.d("Delete",selected!!.name)
            viewModel.deleterecipe(selected!!)
        }


        binding.btnCancel.setOnClickListener(){
            binding.btnDelete.visibility = View.INVISIBLE
            binding.btnEdit.visibility = View.INVISIBLE
            selected = null
            binding.tvSelect.text = "None Selected"
        }

        binding.btnSave.setOnClickListener(){
            binding.apply {
                var newRecipe : Recipe = Recipe(id1,
                etName.text.toString(),
                etDesc.text.toString(),
                etIng.text.toString())
                viewModel.addrecipe(newRecipe)
            prompt.visibility = View.GONE
            newName.visibility = View.GONE
            newDesc.visibility = View.GONE
            newIng.visibility = View.GONE
            etName.visibility = View.GONE
            etDesc.visibility = View.GONE
            etIng.visibility = View.GONE
            btnSave.visibility = View.GONE}
        }



        }


    private fun edits()=with(binding) {
        prompt.visibility = View.VISIBLE
        newName.visibility = View.VISIBLE
        newDesc.visibility = View.VISIBLE
        newIng.visibility = View.VISIBLE
        etName.visibility = View.VISIBLE
        etDesc.visibility = View.VISIBLE
        etIng.visibility = View.VISIBLE
        btnSave.visibility = View.VISIBLE
    }

    private fun selectedRecipe(recipe: Recipe){
        binding.btnDelete.visibility = View.VISIBLE
        binding.btnEdit.visibility = View.VISIBLE
        selected = recipe
        binding.tvSelect.text = recipe.name

    }

}