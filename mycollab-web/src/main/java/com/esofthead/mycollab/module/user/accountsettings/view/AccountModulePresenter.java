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
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.shell.view.MainView;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.web.DesktopApplication;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class AccountModulePresenter extends AbstractPresenter<AccountModule> {
    private static final long serialVersionUID = 1L;

    public AccountModulePresenter() {
        super(AccountModule.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        AppContext.getInstance().updateLastModuleVisit(ModuleNameConstants.ACCOUNT);
        MainView mainView = (MainView) container;
        mainView.addModule(view);

        String[] params = (String[]) data.getParams();
        if (params == null || params.length == 0) {
            view.gotoUserProfilePage();
        } else {
            DesktopApplication.rootUrlResolver.getSubResolver("account").handle(params);
        }
    }
}
