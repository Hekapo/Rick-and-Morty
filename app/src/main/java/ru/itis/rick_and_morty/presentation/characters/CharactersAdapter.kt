package ru.itis.rick_and_morty.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.itis.rick_and_morty.databinding.OneCharacterBinding
import ru.itis.rick_and_morty.domain.models.ResultModel

class CharacterListAdapter(
    private val glide: RequestManager,
    private val onClick: (ResultModel) -> Unit
) : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    var characters: List<ResultModel> = listOf()

    inner class CharacterViewHolder(private val binding: OneCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultModel: ResultModel) {
            with(binding) {
                tvName.text = resultModel.name
                tvStatus.text = resultModel.status
                tvLocation.text = resultModel.location.name
                tvSpecies.text = resultModel.species

                ivImage.let {
                    glide.load(resultModel.image)
                        .dontTransform()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .override(220)
                        .into(it)
                }
                onClick(resultModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            OneCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}
