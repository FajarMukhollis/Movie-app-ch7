package com.fajar.movieapp.presentation.ui.user.login

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fajar.movieapp.data.firebase.authentication.UserAuthManager
import com.fajar.movieapp.data.firebase.datasource.UserRemoteDataSource
import com.fajar.movieapp.data.firebase.datasource.UserRemoteDataSourceImpl
import com.fajar.movieapp.data.local.datasource.UserLocalDataSource
import com.fajar.movieapp.data.local.datasource.UserLocalDataSourceImpl
import com.fajar.movieapp.data.local.preference.UserDataStoreManager
import com.fajar.movieapp.data.network.service.MovieApiService
import com.fajar.movieapp.data.repository.UserRepository
import com.fajar.movieapp.data.repository.UserRepositoryImpl
import com.fajar.movieapp.utils.getOrAwaitValue
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var apiService: MovieApiService
    private lateinit var dataStore: UserDataStoreManager
    private lateinit var userAuthManager: UserAuthManager
    private lateinit var localDataSource: UserLocalDataSource
    private lateinit var remoteDataSource: UserRemoteDataSource
    private lateinit var repository: UserRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        val context = ApplicationProvider.getApplicationContext<Context>()

        apiService = mockk()
        dataStore = UserDataStoreManager(context)
        userAuthManager = UserAuthManager(context)
        localDataSource = UserLocalDataSourceImpl(dataStore)
        remoteDataSource = UserRemoteDataSourceImpl(userAuthManager)
        repository = UserRepositoryImpl(localDataSource, remoteDataSource)
        viewModel = LoginViewModel(repository)
    }

    @Test
    fun `when user logged in`() {
        //given
        val status = true

        //when
        viewModel.setUserLogin(status)

        val actual = viewModel.getUserLogin().getOrAwaitValue()
        //Assert.assertEquals(status, actual)
        //Assert.assertEquals(status, actual)
        Assert.assertTrue(actual)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}