/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.file;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 *
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class FileDashboardPresenter extends CrmGenericPresenter<FileDashboardView> {
	private static final long serialVersionUID = 1L;

	public FileDashboardPresenter() {
		super(FileDashboardView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		CrmToolbar.navigateItem(CrmTypeConstants.FILE);
		if (AppContext.canRead(RolePermissionCollections.CRM_DOCUMENT)) {
			super.onGo(container, data);
			view.displayFiles();
			AppContext.addFragment("crm/file/dashboard", "Customer: File Dashboard");
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
