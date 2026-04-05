package com.github.peco2282.mcdatapack.startup

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope

class MyProjectActivity : ProjectActivity {

  override suspend fun execute(project: Project) {
    val mcfunctionFiles = FilenameIndex.getAllFilesByExt(
      project, "mcfunction", GlobalSearchScope.projectScope(project)
    )
    if (mcfunctionFiles.isNotEmpty()) {
      NotificationGroupManager.getInstance()
        .getNotificationGroup("MC-Datapack")
        ?.createNotification(
          "MC-Datapack",
          "Found ${mcfunctionFiles.size} .mcfunction file(s) in this project.",
          NotificationType.INFORMATION
        )
        ?.notify(project)
    }
  }
}
