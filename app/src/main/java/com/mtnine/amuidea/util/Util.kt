package com.mtnine.amuidea.util

object Util {
    fun isAllValid(vararg args: String?): Boolean {

        //checking all strings passed and if a single string is not valid returning false.
        args.forEach {
            if (isNotValid(it))
                return false
        }
        return true
    }

    private fun isValid(string: String?): Boolean {
        return string != null && string.isNotEmpty() && string.isNotBlank()
    }

    private fun isNotValid(string: String?): Boolean {
        return isValid(string).not()
    }
}