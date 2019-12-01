package cn.luozhanming.github.net

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.rx2.Rx2Apollo

fun <D: Operation.Data,T,V:Operation.Variables>ApolloClient.rxQuery(query:Query<D,T,V>) =
    Rx2Apollo.from(query(query))