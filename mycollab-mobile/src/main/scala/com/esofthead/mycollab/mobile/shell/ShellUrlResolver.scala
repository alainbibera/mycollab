/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.shell

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.crm.CrmUrlResolver
import com.esofthead.mycollab.mobile.module.project.ProjectUrlResolver
import com.esofthead.mycollab.mobile.shell.events.ShellEvent
import com.esofthead.mycollab.vaadin.mvp.UrlResolver
import com.vaadin.ui.UI

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class ShellUrlResolver extends UrlResolver {
    this.addSubResolver("crm", new CrmUrlResolver().build)
    this.addSubResolver("project", new ProjectUrlResolver().build)

    def navigateByFragement(fragement: String) {
        if (fragement != null && fragement.length > 0) {
            val tokens: Array[String] = fragement.split("/")
            this.handle(tokens: _*)
        }
        else {
            EventBusFactory.getInstance.post(new ShellEvent.GotoMainPage(UI.getCurrent, null))
        }
    }

    override protected def defaultPageErrorHandler(): Unit = {
        EventBusFactory.getInstance.post(new ShellEvent.GotoMainPage(UI.getCurrent, null))
    }
}
