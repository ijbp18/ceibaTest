package co.com.ceiba.mobile.pruebadeingreso

import com.google.common.truth.Truth
import co.com.ceiba.mobile.pruebadeingreso.data.network.mappers.toUsersUI
import co.com.ceiba.mobile.pruebadeingreso.data.network.responsemodel.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.data.repository.RepositoryImpl
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI
import co.com.ceiba.mobile.pruebadeingreso.utils.CoroutineTestRule
import co.com.ceiba.mobile.pruebadeingreso.utils.MockResponseFileReader
import co.com.ceiba.mobile.pruebadeingreso.utils.createInternalService
import com.google.gson.GsonBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result as ResultLocal

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RepositoryTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val server = MockWebServer()
    private lateinit var sut: RepositoryImpl
    private lateinit var mockedResponse: String

    private val gson = GsonBuilder().create()

    @Before
    fun init() {
        server.start(8000)
        val baseUrl = server.url("/").toString()
        sut = RepositoryImpl(
            createInternalService(baseUrl),
            coroutinesTestRule.testDispatcherProvider,

        )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    //Retrieving terms and conditions
    @Test
    fun verifyOkResult_whenUserConsultsUsersList() {
        runBlocking {
            val httpCode = 200
            val expectedItemListSize = 2
            mockedResponse =
                MockResponseFileReader("raw.getuserlist200.json").content

            server.enqueue(
                MockResponse()
                    .setResponseCode(httpCode)
                    .setBody(mockedResponse)
            )
            val expectedModel =
                gson.fromJson(
                    mockedResponse,
                    UserResponse::class.java
                ).toUsersUI()

            val actualFlow = sut.getUsers()
            val stateList = mutableListOf<ResultLocal<ArrayList<UsersUI>>>()

            actualFlow.collect {
                stateList.add(it)
            }

            val unWrapperValue = stateList.last() as ResultLocal.Success

            Truth.assertThat(stateList.first()).isInstanceOf(ResultLocal.Loading::class.java)
            Truth.assertThat(stateList.size).isEqualTo(expectedItemListSize)
            Truth.assertThat(unWrapperValue.data).isEqualTo(expectedModel)
        }
    }



    @Test
    fun testNullData_whenServerReturnsEmptyErrorAnd500HttpCode_onUserList() {
        runBlocking {
            val httpCode = 500
            mockedResponse = ""
            val expectedItemListSize = 2
            server.enqueue(
                MockResponse().setResponseCode(httpCode).setBody(mockedResponse)
            )

            val actualFlow = sut.getUsers()
            val stateList = mutableListOf<ResultLocal<ArrayList<UsersUI>>>()

            actualFlow.collect {
                stateList.add(it)
            }

            val unWrapperValue = stateList.last() as ResultLocal.Failure
            Truth.assertThat(stateList.first()).isInstanceOf(ResultLocal.Loading::class.java)
            Truth.assertThat(stateList.size).isEqualTo(expectedItemListSize)
            Truth.assertThat(unWrapperValue).isInstanceOf(ResultLocal.Failure::class.java)
            Truth.assertThat(unWrapperValue.error).isNull()
        }
    }

    @Test
    fun testNullData_whenServerRetrievesInvalidJsonAsResponseAnd500HttpCode_onUserList() {
        runBlocking {
            mockedResponse = "12390-adfjlzxcvklj3q4m52/asdfj234509jaskd.,mzxcv awupqwe"
            val expectedItemListSize = 2
            server.enqueue(
                MockResponse().setResponseCode(500).setBody(mockedResponse)
            )

            val actualFlow = sut.getUsers()
            val stateList = mutableListOf<ResultLocal<ArrayList<UsersUI>>>()

            actualFlow.collect {
                stateList.add(it)
            }

            val unWrapperValue = stateList.last() as ResultLocal.Failure
            Truth.assertThat(stateList.first()).isInstanceOf(ResultLocal.Loading::class.java)
            Truth.assertThat(stateList.size).isEqualTo(expectedItemListSize)
            Truth.assertThat(unWrapperValue).isInstanceOf(ResultLocal.Failure::class.java)
            Truth.assertThat(unWrapperValue.error).isNull()

        }
    }


}