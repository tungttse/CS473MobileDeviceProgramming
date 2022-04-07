package miu.edu.cvbuilderapp.ui.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import miu.edu.cvbuilderapp.R
import miu.edu.cvbuilderapp.adapter.ExperienceAdapter
import miu.edu.cvbuilderapp.repository.database.Experience
import miu.edu.cvbuilderapp.ui.activities.MainActivity
import miu.edu.cvbuilderapp.utilities.*
import miu.edu.cvbuilderapp.viewmodel.CreateResumeViewModel
import kotlinx.android.synthetic.main.fragment_experience.*

class ExperienceFragment : Fragment() {

	private lateinit var experienceAdapter : ExperienceAdapter
	private lateinit var linearLayoutManager : LinearLayoutManager
	private lateinit var createResumeViewModel : CreateResumeViewModel
	private var experienceList: List<Experience> =arrayListOf(
		Experience("VNG", "Software Engineer", "01/2012-12/2025", 1)
	)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		experienceAdapter = ExperienceAdapter(
			// click event for create new
				{ item: Experience ->
					// On Save Button Click
					item.saved = true
					createResumeViewModel.apply {
						updateExperience(item)
					}
				},
			// click event for delete
				{ item: Experience ->
					// On Delete Button Click
					createResumeViewModel.deleteExperience(item)
					(activity as MainActivity).displaySnackbar("Experience deleted.")
				},
			// click event for edit
				{ item: Experience ->
					// On Edit Button Click
					item.saved = false
					createResumeViewModel.apply {
						updateExperience(item)
					}
				})
		linearLayoutManager = LinearLayoutManager(context)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_experience, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		activity?.let {
			createResumeViewModel = ViewModelProviders
					.of(it)
					.get(CreateResumeViewModel::class.java)
		}

		createResumeViewModel.experienceList
				.observe(viewLifecycleOwner, Observer {
					experienceAdapter.updateExperienceList(it ?: experienceList)
					createResumeViewModel.experienceDetailsSaved = it == null || it.isEmpty() || it.areAllItemsSaved()
					toggleNoExperienceLayout(it?.size ?: 0)
				})
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		experienceRecyclerView.apply {
			adapter = experienceAdapter
			layoutManager = linearLayoutManager
		}
	}

	private fun toggleNoExperienceLayout(size : Int) {
		if (size > 0) {
			experienceRecyclerView.visible()
			noExperienceView.invisible()
		} else {
			experienceRecyclerView.invisible()
			noExperienceView.visible()
		}
	}
}
