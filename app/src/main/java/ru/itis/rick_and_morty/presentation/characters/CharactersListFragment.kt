package ru.itis.rick_and_morty.presentation.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import ru.itis.rick_and_morty.R
import ru.itis.rick_and_morty.databinding.FragmentCharactersListBinding
import ru.itis.rick_and_morty.domain.models.CharacterModel
import javax.inject.Inject

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private val characterViewModel: CharactersViewModel by viewModels()

    private lateinit var binding: FragmentCharactersListBinding

    @Inject
    lateinit var glide: RequestManager

    private val characteAadapter by lazy {
        CharacterListAdapter(
            glide,
            onClick = {
                binding.root.findNavController()
                    .navigate(R.id.action_charactersListFragment_to_characterDetailFragment)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersListBinding.inflate(layoutInflater)

        setupRecyclerView()
        initObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel.fetchWeather(1)
    }

    private fun updateUi(model: CharacterModel) {
        binding.run {
            Log.e("DEBUG", model.results.toString())
            characteAadapter.characters = model.results
        }
    }

    private fun setupRecyclerView() {
        binding.run {
            with(rvCharacters) {
                adapter = characteAadapter
            }
        }
    }

    private fun initObservers() {
        characterViewModel.characters.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = { model ->
                    updateUi(model)
                    Snackbar.make(
                        binding.root,
                        "Success",
                        Snackbar.LENGTH_LONG
                    ).show()
                }, onFailure = {
                    Log.e("asd", it.message.toString())
                })
        }

        characterViewModel.error.observe(viewLifecycleOwner) {
            when (it) {
                is HttpException -> {

                }
                is NullPointerException -> {

                }
                else -> {

                }
            }
        }
    }
}
