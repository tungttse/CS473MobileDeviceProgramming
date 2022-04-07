package miu.edu.cvbuilderapp.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import miu.edu.cvbuilderapp.utilities.AppDispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [(Education::class), (Experience::class)], version = 1)
abstract class ResumeDatabase : RoomDatabase() {

	abstract fun educationDAO() : EducationDAO
	abstract fun experienceDAO() : ExperienceDAO

	companion object : SingletonHolder<ResumeDatabase, Context>(
			{
				Room.databaseBuilder(it.applicationContext,
						ResumeDatabase::class.java,
						"resumes")
						.fallbackToDestructiveMigration()
						.build()
			}
	)

}