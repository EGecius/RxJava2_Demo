package com.egecius.rxjava2_demo_2.rx._patterns.cashedrepo

import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CombinedRepoTest {

    lateinit var sut: CombinedRepo

    @Mock
    lateinit var persistenceRepo: PersistenceRepo
    @Mock
    lateinit var networkRepo: NetworkRepo

    private val usersPersistence = listOf(User("persistence1"), User("persistence2"))
    private val usersNetwork = listOf(User("network1"), User("network2"))

    @Before
    fun setUp() {
        sut = CombinedRepo(networkRepo, persistenceRepo)
    }

    @Test
    fun `when only persistence data available, delivers from there`() {
        givenPersistenceReturnsData()
        givenNetworkReturnNothing()

        val testObserver = sut.getUsers().test()

        testObserver.assertResult(usersPersistence)
    }

    private fun givenPersistenceReturnsData() {
        given(persistenceRepo.getUsers()).willReturn(Observable.just(usersPersistence))
    }

    private fun givenNetworkReturnNothing() {
        given(networkRepo.getUsers()).willReturn(Observable.empty())
    }

    @Test
    fun `when only network data available, delivers from there`() {
        givenPersistenceReturnsNothing()
        giveNetworkReturnData()

        val testObserver = sut.getUsers().test()

        testObserver.assertResult(usersNetwork)
    }

    private fun givenPersistenceReturnsNothing() {
        given(persistenceRepo.getUsers()).willReturn(Observable.empty())
    }

    private fun giveNetworkReturnData() {
        given(networkRepo.getUsers()).willReturn(Observable.just(usersNetwork))
    }

    @Test
    fun `when no data in either persistence or network, returns nothing`() {
        givenPersistenceReturnsNothing()
        givenNetworkReturnNothing()

        val testObserver = sut.getUsers().test()

        testObserver.assertNoValues().assertComplete()
    }

    @Test
    fun `when data available from both, first returns from cache and then from network`() {
        givenPersistenceReturnsData()
        giveNetworkReturnData()

        val testObserver = sut.getUsers().test()

        testObserver.assertResult(usersPersistence, usersNetwork)
    }

}