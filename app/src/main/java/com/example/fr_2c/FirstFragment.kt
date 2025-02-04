package com.example.fr_2c

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fr_2c.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        //(activity as AppCompatActivity).supportActionBar?.title = "Pull of actual news"
        val recyclerView: RecyclerView = _binding!!.rcView
        binding.apply { recyclerView.layoutManager = LinearLayoutManager(context); recyclerView.adapter = adaptator}
        binding.button3.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
        }

        binding.buttonAPI.setOnClickListener {
            adaptator.updateNewsList(viewModel.newsAPI)
            viewModel.state = "api"
        }

        binding.buttonDB.setOnClickListener {
            viewModel.newsAPI = adaptator.NewsList
            adaptator.updateNewsList(viewModel.newsDB)
            viewModel.state = "db"
        }

        binding.button2.setOnClickListener {
            if (viewModel.state == "api")
                for (elem in adaptator.NewsList){
                    dbViewModel.insert(elem)
                }
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            viewModel.getNews();
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}