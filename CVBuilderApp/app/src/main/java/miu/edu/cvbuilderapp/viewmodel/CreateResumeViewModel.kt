package miu.edu.cvbuilderapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import miu.edu.cvbuilderapp.repository.LocalRepository
import miu.edu.cvbuilderapp.repository.database.Education
import miu.edu.cvbuilderapp.repository.database.Experience
import kotlinx.coroutines.*

class CreateResumeViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val createResumeViewModelJob = Job()
    override val coroutineContext = Dispatchers.Main + createResumeViewModelJob
    private var resumeId: Long = 1L;
    val educationList: LiveData<List<Education>>
    val experienceList: LiveData<List<Experience>>

    var personalDetailsSaved: Boolean = true
    var educationDetailsSaved: Boolean = true
    var experienceDetailsSaved: Boolean = true
    var projectDetailsSaved: Boolean = true

    private var repository: LocalRepository = LocalRepository(getApplication())

    init {
        educationList = repository.getAllEducationForResume(resumeId)
        experienceList = repository.getAllExperienceForResume(resumeId)
    }

    fun insertBlankEducation() = launch {
        val education = Education("", "", "", "", resumeId)
        repository.insertEducation(education)
    }

    fun insertBlankExperience() = launch {
        val experience = Experience("", "", "", resumeId)
        repository.insertExperience(experience)
    }

    fun updateEducation(education: Education) = launch {
        repository.updateEducation(education)
    }

    fun updateExperience(experience: Experience) = launch {
        repository.updateExperience(experience)
    }

    fun deleteEducation(education: Education) = launch {
        repository.deleteEducation(education)
    }

    fun deleteExperience(experience: Experience) = launch {
        repository.deleteExperience(experience)
    }

    override fun onCleared() {
        super.onCleared()
        createResumeViewModelJob.cancel()
    }
}