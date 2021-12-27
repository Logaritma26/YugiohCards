package com.log.yugiohcards.lib.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import timber.log.Timber

data class Route(
    private val baseRoute: String,
    private val args: Map<String, ArgSpecs> = mapOf(),
    private val optionalArgs: Map<String, ArgSpecs> = mapOf()
) {
    fun path(): String {
        var url: String = baseRoute
        for (arg in args) {
            url += "/{${arg.key}}"
        }
        var firstOptionalCount = true
        for (arg in optionalArgs) {
            if (firstOptionalCount) {
                url += "?${arg.key}={${optionalArgs[arg.key]}}"
                firstOptionalCount = false
            } else {
                url += "&${arg.key}={${optionalArgs[arg.key]}}"
            }
        }
        Timber.d("full route path: $url")
        return url
    }

    fun namedNavArgs(): List<NamedNavArgument> {
        return listOf(
            *args.map {
                navArgument(it.key) {
                    type = it.value.type
                    nullable = it.value.nullable
                    //defaultValue = it.value.defaultValue
                }
            }.toTypedArray(),
            *optionalArgs.map {
                navArgument(it.key) {
                    type = it.value.type
                    nullable = it.value.nullable
                    it.value.defaultValue?.let { defValue ->
                        defaultValue = defValue
                    }
                }
            }.toTypedArray()
        )
    }

    fun provideNavPath(
        args: Map<String, Any> = mapOf()
    ): String? {
        try {
            var navPath: String = baseRoute
            for (arg in args) {
                navPath += "/${args[arg.key]}"
            }
            var firstOptionalCount = true
            for (arg in args) {
                if (optionalArgs.containsKey(arg.key)) {
                    if (firstOptionalCount) {
                        navPath += "?${arg.key}=${args[arg.key]}"
                        firstOptionalCount = false
                    } else {
                        navPath += "&${arg.key}=${args[arg.key]}"
                    }
                }
            }
            Timber.d("completed nav path: $navPath")
            return navPath
        } catch (err: Exception) {
            Timber.e("providing nav url failed: ${(err.localizedMessage ?: err.toString())}")
            return null
        }
    }
}

data class ArgSpecs(
    val type: NavType<*>,
    val nullable: Boolean = false,
    val defaultValue: Any? = null,
)


