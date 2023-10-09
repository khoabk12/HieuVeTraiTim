package com.devindie.hieuvetraitim.devutils

import android.util.Log


fun log(msg: String) {
    val stackTrace = Exception().stackTrace[1]
    var fileName = stackTrace.fileName
    if (fileName == null) fileName = "" // It is necessary if you want to use proguard obfuscation.
    val info = (stackTrace.methodName + " (" + fileName + ":"
            + stackTrace.lineNumber + ")")
    Log.e(fileName, "$info: $msg")
}
