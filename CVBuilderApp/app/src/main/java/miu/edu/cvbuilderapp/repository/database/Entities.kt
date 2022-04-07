package miu.edu.cvbuilderapp.repository.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

abstract class ResumeEntity {
	@PrimaryKey(autoGenerate = true)
	var id : Long = 0L

	@ColumnInfo(name = "saved")
	var saved : Boolean = false

	abstract fun isEmpty() : Boolean

	fun isNotEmpty() : Boolean = !isEmpty()
}

@Entity(tableName = "education")
data class Education (
		@ColumnInfo(name = "instituteName")
		var instituteName: String,
		@ColumnInfo(name = "degree")
		var degree: String,
		@ColumnInfo(name = "yearOfGraduation")
		var year: String,
		@ColumnInfo(name = "performance")
		var performance: String,
		@ColumnInfo(name = "resumeId")
		var resumeId: Long) : ResumeEntity() {

	override fun isEmpty() : Boolean {
		if (instituteName.isBlank() &&
				degree.isBlank() &&
				year.isBlank() &&
				performance.isBlank()) {
			return true
		}
		return false
	}

}

@Entity(tableName = "experience")
data class Experience (
		@ColumnInfo(name = "companyName")
		var companyName: String,
		@ColumnInfo(name = "jobTitle")
		var jobTitle: String,
		@ColumnInfo(name = "duration")
		var duration: String,
		@ColumnInfo(name = "resumeId")
		var resumeId: Long) : ResumeEntity() {

	override fun isEmpty(): Boolean {
		if (companyName.isBlank() &&
				jobTitle.isBlank() &&
				duration.isBlank()) {
			return true
		}
		return false
	}
}
