package miu.edu.cvbuilderapp.repository.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EducationDAO {

	@Query("SELECT * FROM education")
	fun getAllEducation() : MutableList<Education>

	@Query("SELECT * FROM education WHERE resumeId=:resumeId")
	fun getEducationForResume(resumeId : Long) : LiveData<List<Education>>

	@Query("SELECT * FROM education WHERE resumeId=:resumeId")
	fun getEducationForResumeOnce(resumeId : Long) : List<Education>

	@Query("SELECT count(*) FROM education")
	fun getEducationId() : Long

	@Insert
	fun insertEducation(education : Education) : Long

	@Delete
	fun deleteEducation(education : Education)

	@Update
	fun updateEducation(education: Education)
}

@Dao
interface ExperienceDAO {

	@Query("SELECT * FROM experience")
	fun getAllExperience() : LiveData<List<Experience>>

	@Query("SELECT * FROM experience WHERE resumeId=:resumeId")
	fun getExperienceForResume(resumeId : Long) : LiveData<List<Experience>>

	@Query("SELECT * FROM experience WHERE resumeId=:resumeId")
	fun getExperienceForResumeOnce(resumeId : Long) : List<Experience>

	@Query("SELECT count(*) FROM experience")
	fun getExperienceId() : Long

	@Insert
	fun insertExperience(experience : Experience) : Long

	@Delete
	fun deleteExperience(experience : Experience)

	@Update
	fun updateExperience(experience : Experience)
}
