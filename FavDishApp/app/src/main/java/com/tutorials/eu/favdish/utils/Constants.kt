package com.tutorials.eu.favdish.utils

/**
 * Isso é usado para definir as constants que podemos usar em todo o aplicativo.
 */
object Constants {

    const val DISH_TYPE: String = "DishType"
    const val DISH_CATEGORY: String = "DishCategory"
    const val DISH_COOKING_TIME: String = "DishCookingTime"

    const val DISH_IMAGE_SOURCE_LOCAL: String = "Local"
    const val DISH_IMAGE_SOURCE_ONLINE: String = "Online"

    const val ALL_ITENS: String = "All"
    const val FILTER_SELECTION:String = "FilterSelection"

    const val EXTRA_DISH_DETAILS: String = "DishDetails"

    const val API_ENDPOINT: String = "recipes/random"

    const val API_KEY: String = "apiKey"
    const val LIMITED_LICENSE: String = "limitLicense"
    const val TAGS: String = "tags"
    const val NUMBER: String = "number"
    const val BASE_URL = "https://api.spoonacular.com/"

    const val API_KEY_VALUE: String = "873d4c9a92e64faca04394ad7e6a4841"
    const val LIMIT_LICENSE_VALUE: Boolean = true
    const val TAGS_VALUE: String = "vegetarian, desert"
    const val NUMBER_VALUE: Int = 1

    /**
     * This function will return the Dish Type List items.
     * Esta função retornará os itens da Lista de Dish Type.
     */
    fun dishTypes(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("breakfast")
        list.add("lunch")
        list.add("snacks")
        list.add("dinner")
        list.add("salad")
        list.add("side dish")
        list.add("dessert")
        list.add("other")
        return list
    }

    /**
     *  Esta função retornará os itens da Lista de Dish Category.
     */
    fun dishCategories(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("Pizza")
        list.add("BBQ")
        list.add("Bakery")
        list.add("Burger")
        list.add("Cafe")
        list.add("Chicken")
        list.add("Dessert")
        list.add("Drinks")
        list.add("Hot Dogs")
        list.add("Juices")
        list.add("Sandwich")
        list.add("Tea & Coffee")
        list.add("Wraps")
        list.add("Other")
        return list
    }


    /**
     *  Esta função retornará os itens da lista de Dish Cook Time. O tempo adicionado está em minutos.
     */
    fun dishCookTime(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("10")
        list.add("15")
        list.add("20")
        list.add("30")
        list.add("45")
        list.add("50")
        list.add("60")
        list.add("90")
        list.add("120")
        list.add("150")
        list.add("180")
        return list
    }
}