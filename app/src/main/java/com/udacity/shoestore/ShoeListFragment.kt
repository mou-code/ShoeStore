package com.udacity.shoestore

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentShoeListBinding.inflate(inflater, container, false)


        setHasOptionsMenu(true)

        //Setting up the action bar to show
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        //Setting the Listeners for the buttons
        binding.addButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoeListFragment_to_shoeDetailFragment)
        )

        fun InflateShoe(shoe: Shoe) {

            val textView = android.widget.TextView(activity)
            val tf = Typeface.createFromAsset(
                context!!.assets,
                "Roboto-Light.ttf"
            )
            textView.setTypeface(tf)

            textView.text = "Model\t\t\t\t\t\t- ${shoe.name}\nBrand\t\t\t\t\t\t- ${shoe.company}\n" +
                    "Size\t\t\t\t\t\t\t- ${shoe.size}\nDescription\t\t- " +
                    "${shoe.description} "

            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
          //  params.bottomMargin = 6
            params.topMargin = 3
            textView.textSize = 20F


            textView.setTextColor(Color.DKGRAY);
            textView.setPadding(10, 8, 10, 8)
            val backgroundcolor: Int = Color.parseColor("#e6e6e6")
            textView.setBackgroundColor(backgroundcolor)
            binding.shoelistLinearlayout.addView(textView, params)
            binding.notifyChange()

        }


        //Observing any changes in the Shoe list
        sharedViewModel.getShoe().observe(viewLifecycleOwner) {
            binding.emptyText.visibility = View.GONE
           for (shoe in sharedViewModel.getShoe().value!!) {
               InflateShoe(shoe)
           }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)

    }
}