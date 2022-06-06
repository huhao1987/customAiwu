package com.aiwu.core

class AIWUJNIUtils {
    private var aiwujniUtils:AIWUJNIUtils?=null
    fun onCreate():AIWUJNIUtils{
        if(aiwujniUtils==null) {
            System.loadLibrary("SubAiwuSo")
            aiwujniUtils=AIWUJNIUtils()
        }
        return aiwujniUtils!!
    }
    external fun wlbHt(str: String, j: Long):String
}