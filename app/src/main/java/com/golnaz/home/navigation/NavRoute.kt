package com.golnaz.home.navigation

sealed class NavRoute(val path: String) {

    object MainScreen: NavRoute("main")

    object DetailsScreen: NavRoute("detail")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    companion object {
        const val ARG_ID = "id"
    }
}