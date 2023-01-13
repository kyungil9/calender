package com.calender.presentation

class security {
    companion object{
        fun isRooting(): Boolean {
            var flag = false
            try {
                Runtime.getRuntime().exec("su")
                flag = true
            } catch (ex: Exception) {
            }
            return flag
        }
    }
}