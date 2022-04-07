package miu.edu.cvbuilderapp.utilities

import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object AppDispatchers {
	private val diskIO : ExecutorService = Executors.newSingleThreadExecutor()
	val diskDispatcher = diskIO.asCoroutineDispatcher()
}

