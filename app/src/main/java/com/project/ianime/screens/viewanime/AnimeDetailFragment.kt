package com.project.ianime.screens.viewanime

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.project.ianime.R
import com.project.ianime.databinding.FragmentAnimeBinding
import com.project.ianime.di.AnimeApplication
import com.project.ianime.root.BaseFragment
import com.project.ianime.screens.manageanime.EditAnimeFragment
import com.project.ianime.utils.image.ImageUtils
import com.project.ianime.viewmodels.AnimeViewModel
import com.project.ianime.viewmodels.AnimeViewModelFactory
import javax.inject.Inject

/**
 * Anime detail screen which shows a specific anime with all the details
 */
class AnimeDetailFragment : BaseFragment() {
    private var _binding: FragmentAnimeBinding? = null
    val binding get() = _binding!!

    private val imageUtils: ImageUtils by lazy {
        ImageUtils()
    }

    lateinit var animeViewModel: AnimeViewModel

    @Inject
    lateinit var animeViewModelFactory: AnimeViewModelFactory

    private lateinit var animeTargetId: String

    lateinit var toolbar: Toolbar
    lateinit var animeProfile: ImageView
    lateinit var animeName: TextView
    lateinit var animeType: TextView
    lateinit var animeCountry: TextView
    lateinit var animeReleasedYear: TextView
    lateinit var animeStatus: TextView
    lateinit var animeIntro: TextView

    override fun updateActionBar(): Boolean {
        actionBarService.setNavigateBackAction(toolbar, this)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animeApplication = requireActivity().application as AnimeApplication
        animeApplication.applicationComponent.inject(this)

        arguments?.let {
            animeTargetId = it.getString(ANIME_TARGET_ID).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimeBinding.inflate(inflater, container, false)
        animeViewModel = ViewModelProvider(this, animeViewModelFactory)[AnimeViewModel::class.java]

        // set up UI elements
        toolbar = binding.topAppBar.toolBar
        animeProfile = binding.animeProfile
        animeName = binding.animeName
        animeType = binding.animeType
        animeCountry = binding.animeCountry
        animeReleasedYear = binding.animeYear
        animeStatus = binding.animeStatus
        animeIntro = binding.animeIntro
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Test using mocked data
        getAnimeDetails()
//        loadAnimeDetails()
    }

    private fun loadAnimeDetails() {
        animeViewModel.getAnimeById(animeTargetId)?.let { targetAnime ->
            // set title to each anime name
            actionBarService.setTitle(targetAnime.animeName, toolbar)

            // load data details
            animeName.text = targetAnime.animeName
            targetAnime.animeImageUrl?.let {
                imageUtils.loadImageFromDisk(it, animeProfile)
            }
            animeType.text = getString(R.string.anime_type_title, getString(targetAnime.type.label))
            animeCountry.text = getString(R.string.anime_country_title, getString(targetAnime.country.label))
            val releaseYear = targetAnime.releaseYear ?: getString(R.string.not_applicable_data)
            animeReleasedYear.text = getString(R.string.anime_release_year_title, releaseYear)
            animeStatus.text = getString(R.string.anime_status_title, getString(targetAnime.status.label))
            val animeDescription: String = targetAnime.synopsis ?: getString(R.string.empty_description_message)
            animeIntro.text = animeDescription
        }
    }

    /**
     * TODO: removed when api is ready
     */
    private fun getAnimeDetails() {
        actionBarService.setTitle("Throne of Seal", toolbar)
        animeName.text = "Throne of Seal"
        animeCountry.text = getString(R.string.anime_country_title, "China")
        animeType.text = getString(R.string.anime_type_title, "God")
        animeReleasedYear.text = getString(R.string.anime_release_year_title, "2021")
        animeStatus.text = getString(R.string.anime_status_title, "In Progress")
        animeIntro.text = "A boy on his own way to fight"

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit_anime -> {
                appNavigation.showFragmentOverTop(
                    EditAnimeFragment.newInstance(),
                    baseContainerId
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ANIME_TARGET_ID = "anime_id"
    }
}