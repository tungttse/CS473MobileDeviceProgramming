package miu.edu.cvbuilderapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.withContext
import miu.edu.cvbuilderapp.repository.database.Education
import miu.edu.cvbuilderapp.repository.database.Experience
import miu.edu.cvbuilderapp.repository.database.ResumeDatabase
import miu.edu.cvbuilderapp.utilities.AppDispatchers


class LocalRepository(application: Application) : Repository {

    val database: ResumeDatabase = ResumeDatabase.getInstance(application)

    override fun getAllEducationForResume(resumeId: Long): LiveData<List<Education>> =
        database.educationDAO().getEducationForResume(resumeId)

    override fun getAllEducationForResumeOnce(resumeId: Long): List<Education> =
        database.educationDAO().getEducationForResumeOnce(resumeId)

    override suspend fun insertEducation(education: Education): Long =
        withContext(AppDispatchers.diskDispatcher) {
            database.educationDAO().insertEducation(education)
        }

    override suspend fun deleteEducation(education: Education) =
        withContext(AppDispatchers.diskDispatcher) {
            database.educationDAO().deleteEducation(education)
        }

    override suspend fun updateEducation(education: Education) =
        withContext(AppDispatchers.diskDispatcher) {
            database.educationDAO().updateEducation(education)
        }

    override fun getAllExperienceForResume(resumeId: Long): LiveData<List<Experience>> =
        database.experienceDAO().getExperienceForResume(resumeId)

    override fun getAllExperienceForResumeOnce(resumeId: Long): List<Experience> =
        database.experienceDAO().getExperienceForResumeOnce(resumeId)

    override suspend fun insertExperience(experience: Experience): Long =
        withContext(AppDispatchers.diskDispatcher) {
            database.experienceDAO().insertExperience(experience)
        }

    override suspend fun deleteExperience(experience: Experience) =
        withContext(AppDispatchers.diskDispatcher) {
            database.experienceDAO().deleteExperience(experience)
        }

    override suspend fun updateExperience(experience: Experience) =
        withContext(AppDispatchers.diskDispatcher) {
            database.experienceDAO().updateExperience(experience)
        }
}