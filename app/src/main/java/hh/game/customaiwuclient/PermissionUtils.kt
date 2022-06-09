package hh.game.customaiwuclient

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import com.anggrayudi.storage.SimpleStorageHelper
import com.anggrayudi.storage.file.findFolder

class PermissionUtils {
    companion object {
        lateinit var sharepreferences: SharedPreferences
        lateinit var storageHelper: SimpleStorageHelper
        private lateinit var activity: Activity
        var storageid: String? = null
        val FOLDER_PATH = "folderpath"
        var basegamefolder: DocumentFile? = null
        var titlefolder: DocumentFile? = null
        var gamelists: List<DocumentFile>? = null
        var cheatsgamefolder: DocumentFile? = null
        var gamecodes: ArrayList<Pair<String, Boolean>>? = null
        var cheatcodes: List<DocumentFile>? = null

        fun init(context: Context): Boolean {
            if (storageid == null) {
                sharepreferences = context.getSharedPreferences("Config", Context.MODE_PRIVATE)
                var permissionlist = context.contentResolver.persistedUriPermissions
                if (permissionlist.size > 0) {
                    storageid = sharepreferences.getString("storageid", null)
                    var uri = Uri.parse(sharepreferences.getString(FOLDER_PATH, null))
                    var documentFile = DocumentFile.fromTreeUri(context, uri)

                    basegamefolder = documentFile?.findFolder("sdmc")

                    if (basegamefolder == null) {

                        return false
                    } else {
                        var subgamefolder = basegamefolder?.findFolder("Nintendo 3DS")
                        if (subgamefolder == null) {

                            return false
                        } else {
                            titlefolder =
                                subgamefolder.findFolder("00000000000000000000000000000000")
                                    ?.findFolder("00000000000000000000000000000000")
                                    ?.findFolder("title")
                            titlefolder?.let {
                                gamelists = it.listFiles().toList()
                            }
                            gamecodes = ArrayList()
                            gamelists?.forEach {
                                var gamefrontcode = it.name
                                it.listFiles().forEach {
                                    gamecodes?.add(Pair(gamefrontcode + it.name, false))
                                }
                            }
                            cheatsgamefolder = documentFile?.findFolder("cheats")
                            if (cheatsgamefolder == null) cheatsgamefolder =
                                documentFile?.createDirectory("cheats")
                            cheatcodes = cheatsgamefolder?.listFiles()?.toList()
                            return true
                        }
                    }
                }
            }
            return false
        }
    }
}