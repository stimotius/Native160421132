package com.ubaya.adv160421132week2

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.adv160421132week2.databinding.FragmentMainBinding
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(
            inflater,
            container, false
        )
        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtAngka1.text = Random.nextInt(1, 100).toString()
        binding.txtAngka2.text = Random.nextInt(1, 100).toString()
        var score = 0
        binding.btnStart.setOnClickListener {
            val result = (binding.txtAngka1.text.toString().toInt() + binding.txtAngka2.text.toString().toInt())
            val answer = binding.txtAnswer.text.toString()
            if (answer.toInt() == result) {
                val dialog = AlertDialog.Builder(activity)
                dialog.setTitle("Message")
                score++
                dialog.setMessage("Your answer is correct.\nYour score is: $score")
                dialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                    binding.txtAnswer.text?.clear()
                    binding.txtAngka1.text = Random.nextInt(1, 100).toString()
                    binding.txtAngka2.text = Random.nextInt(1, 100).toString()
                })
                dialog.create().show()
            } else {
                val res = MainFragmentDirections.actionResultFragment(score.toString())
                Navigation.findNavController(it).navigate(res)
                score = 0
            }
        }
    }
}