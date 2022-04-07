package miu.edu.cvbuilderapp.repository

import androidx.lifecycle.LiveData
import miu.edu.cvbuilderapp.repository.database.Education
import miu.edu.cvbuilderapp.repository.database.Experience

interface Repository {
	// Tasks related to education-list
	fun getAllEducationForResume(resumeId: Long) : LiveData<List<Education>>
	fun getAllEducationForResumeOnce(resumeId: Long) : List<Education>
	suspend fun insertEducation(education: Education): Long
	suspend fun deleteEducation(education: Education)
	suspend fun updateEducation(education: Education)

	// Tasks related to experience-list
	fun getAllExperienceForResume(resumeId: Long) : LiveData<List<Experience>>
	fun getAllExperienceForResumeOnce(resumeId: Long) : List<Experience>
	suspend fun insertExperience(experience: Experience): Long
	suspend fun deleteExperience(experience: Experience)
	suspend fun updateExperience(experience: Experience)
}