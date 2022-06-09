package hh.game.customaiwuclient.Utils

import com.aiwu.core.AIWUJNIUtils
import java.util.*

class AiwuGameUtils {
    data class SignDetail(val Time:Long,val Sign:String)
    companion object{
        fun getSign(gameId:String):SignDetail{
            var treeMap= TreeMap<String,String>()
            var time=System.currentTimeMillis()/1000
            treeMap.put("Serial","00000000-28fb-4bae-ffff-ffffef05ac4a")
            treeMap.put("VersionCode","2353")
            treeMap.put("isLogin","0")
            treeMap.put("Act","getEmuGamebyPackageName")
            treeMap.put("PackageName",gameId)
            treeMap.put("Time",time.toString())
            var sbuilder=StringBuilder()
            for(s in treeMap.keys){
                sbuilder.append(s)
                sbuilder.append("=")
                sbuilder.append(treeMap.get(s))
                sbuilder.append("&")
            }
            var sign=AIWUJNIUtils().onCreate().wlbHt(sbuilder.toString(),time)
            return SignDetail(time,sign)
        }
    }
}