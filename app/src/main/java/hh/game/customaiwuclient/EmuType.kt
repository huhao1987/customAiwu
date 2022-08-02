package hh.game.customaiwuclient

import java.util.regex.Matcher
import java.util.regex.Pattern

object EmuType {
    val gameTypelist by lazy {
        ArrayList<Pair<String,Int>>().apply {
            add(Pair("All",0))
            add(Pair("Java",1))
            add(Pair("PSP",2))
            add(Pair("GBA",3))
            add(Pair("FC",4))
            add(Pair("SFC",6))
            add(Pair("Mame",7))
            add(Pair("mame+",8))
            add(Pair("NDS",9))
            add(Pair("GBC",10))
            add(Pair("MD",11))
            add(Pair("3ds",12))
            add(Pair("Wii",13))
            add(Pair("NGC",14))
            add(Pair("ONS",15))
            add(Pair("easyRPG",16))
            add(Pair("PS1",17))
            add(Pair("DC",18))
            add(Pair("N64",19))
            add(Pair("SS",20))
            add(Pair("OpenBOR",21))
            add(Pair("PS2",22))
            add(Pair("SMS",23))
            add(Pair("NS",24))
            add(Pair("PCE",25))
            add(Pair("DOS",26))
        }
    }

    fun getType(typeNum:Int):String=
        gameTypelist.filter {
            typeNum==it.second
        }.getOrNull(0)?.first?:"All"

    fun getTypeNum(type:String):Int=
        gameTypelist.filter {
            type.equals(it.first)
        }.getOrNull(0)?.second?:0

    fun getCompleteUrl(text: String?): String {
        val p= Pattern.compile(
            "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?",
            Pattern.CASE_INSENSITIVE
        )
        val matcher: Matcher = p.matcher(text)
        matcher.find()
        return matcher.group()
    }
}