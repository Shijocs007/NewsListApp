package com.example.newsapp.network

import kotlinx.coroutines.flow.*

/**
 * the method is modified by referring developer  android
 * @see <a href="https://developer.android.com/jetpack/guide?hl=de#addendum">networkBoundResource<T></a>
 * @param query databse query for fetching result
 * @param fetch for performing network operation
 * @param saveFetchResult handle saving data from network response to room db
 * @param shouldFetch decides if need to do network operation or not, default value true.
 * @return flow object of the response
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}