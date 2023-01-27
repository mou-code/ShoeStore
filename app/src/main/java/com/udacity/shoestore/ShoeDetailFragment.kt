package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoe_detail.*

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = FragmentShoeDetailBinding.inflate(inflater, container, false)


        //binding the shared view models
        binding.myShoe = sharedViewModel

        //Setting the Listeners for the buttons
        binding.cancelButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigateUp()
        }

        binding.saveButton.setOnClickListener{view: View ->
            sharedViewModel.addShoe()

            with(binding){
                shoenameEdit.text.clear()
                shoesizeEdit.text.clear()
                companynameEdit.text.clear()
                descriptionEdit.text.clear()
            }

            Navigation.findNavController(view).navigate(R.id.action_shoeDetailFragment_to_shoeListFragment2)
            Toast.makeText(activity,"Shoe saved Successfuly",Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}