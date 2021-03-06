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

package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.domain.CommentWithBLOBs;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugResolution;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.esofthead.mycollab.module.project.view.bug.components.BugResolutionComboBox;
import com.esofthead.mycollab.module.project.view.bug.components.VersionMultiSelectField;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugRelatedItemService;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.ui.grid.GridFormLayoutHelper;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.ComponentContainer;
import org.vaadin.maddon.layouts.MHorizontalLayout;

import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class ResolvedInputWindow extends Window {
    private static final long serialVersionUID = 1L;

    private final SimpleBug bug;
    private final EditForm editForm;
    private VersionMultiSelectField fixedVersionSelect;
    private final IBugCallbackStatusComp callbackForm;

    ResolvedInputWindow(final IBugCallbackStatusComp callbackForm,
                        final SimpleBug bug) {
        super("Resolve bug '" + bug.getSummary() + "'");
        this.bug = bug;
        this.callbackForm = callbackForm;

        this.setWidth("800px");
        this.setResizable(false);
        this.setModal(true);
        this.editForm = new EditForm();
        this.editForm.setBean(bug);
        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.setMargin(new MarginInfo(false, false, true, false));
        contentLayout.addComponent(this.editForm);
        this.setContent(contentLayout);
        this.center();
    }

    private class EditForm extends AdvancedEditBeanForm<BugWithBLOBs> {

        private static final long serialVersionUID = 1L;
        private RichTextArea commentArea;

        @Override
        public void setBean(final BugWithBLOBs newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setBeanFormFieldFactory(new EditFormFieldFactory(EditForm.this));
            super.setBean(newDataSource);
        }

        class FormLayoutFactory implements IFormLayoutFactory {
            private static final long serialVersionUID = 1L;
            private GridFormLayoutHelper informationLayout;

            @Override
            public ComponentContainer getLayout() {
                final VerticalLayout layout = new VerticalLayout();
                this.informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 6);
                layout.addComponent(this.informationLayout.getLayout());

                final MHorizontalLayout controlsBtn = new MHorizontalLayout().withMargin(true);
                layout.addComponent(controlsBtn);

                final Button resolveBtn = new Button(
                        AppContext.getMessage(BugI18nEnum.BUTTON_RESOLVED),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(
                                    final Button.ClickEvent event) {
                                if (EditForm.this.validateForm()) {
                                    ResolvedInputWindow.this.bug
                                            .setStatus(BugStatus.Resolved
                                                    .name());

                                    final BugRelatedItemService bugRelatedItemService = ApplicationContextUtil
                                            .getSpringBean(BugRelatedItemService.class);
                                    bugRelatedItemService
                                            .updateFixedVersionsOfBug(
                                                    ResolvedInputWindow.this.bug
                                                            .getId(),
                                                    ResolvedInputWindow.this.fixedVersionSelect
                                                            .getSelectedItems());

                                    // Save bug status and assignee
                                    final BugService bugService = ApplicationContextUtil
                                            .getSpringBean(BugService.class);
                                    bugService.updateSelectiveWithSession(
                                            ResolvedInputWindow.this.bug,
                                            AppContext.getUsername());

                                    // Save comment
                                    final String commentValue = EditForm.this.commentArea
                                            .getValue();
                                    if (commentValue != null
                                            && !commentValue.trim().equals("")) {
                                        final CommentWithBLOBs comment = new CommentWithBLOBs();
                                        comment.setComment(commentValue);
                                        comment.setCreatedtime(new GregorianCalendar()
                                                .getTime());
                                        comment.setCreateduser(AppContext
                                                .getUsername());
                                        comment.setSaccountid(AppContext
                                                .getAccountId());
                                        comment.setType(ProjectTypeConstants.BUG);
                                        comment.setTypeid("" + bug.getId());
                                        comment.setExtratypeid(CurrentProjectVariables
                                                .getProjectId());

                                        final CommentService commentService = ApplicationContextUtil
                                                .getSpringBean(CommentService.class);
                                        commentService.saveWithSession(comment,
                                                AppContext.getUsername());
                                    }

                                    ResolvedInputWindow.this.close();
                                    ResolvedInputWindow.this.callbackForm
                                            .refreshBugItem();
                                }

                            }
                        });
                resolveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
                resolveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
                controlsBtn.with(resolveBtn).withAlign(resolveBtn, Alignment.MIDDLE_RIGHT);

                final Button cancelBtn = new Button(
                        AppContext
                                .getMessage(GenericI18Enum.BUTTON_CANCEL),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(
                                    final Button.ClickEvent event) {
                                ResolvedInputWindow.this.close();
                            }
                        });
                cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
                controlsBtn.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_RIGHT);

                layout.setComponentAlignment(controlsBtn,
                        Alignment.MIDDLE_RIGHT);

                return layout;
            }

            @Override
            public void attachField(final Object propertyId,
                                    final Field<?> field) {
                if (propertyId.equals("resolution")) {
                    this.informationLayout.addComponent(field,
                            AppContext.getMessage(BugI18nEnum.FORM_RESOLUTION),
                            0, 0);
                } else if (propertyId.equals("assignuser")) {
                    this.informationLayout
                            .addComponent(field, AppContext
                                            .getMessage(GenericI18Enum.FORM_ASSIGNEE),
                                    0, 1);
                } else if (propertyId.equals("fixedVersions")) {
                    this.informationLayout.addComponent(field, AppContext
                            .getMessage(BugI18nEnum.FORM_FIXED_VERSIONS), 0, 2);
                } else if (propertyId.equals("comment")) {
                    this.informationLayout.addComponent(field,
                            AppContext.getMessage(BugI18nEnum.FORM_COMMENT), 0,
                            3, 2, "100%");
                }
            }
        }

        private class EditFormFieldFactory extends
                AbstractBeanFieldGroupEditFieldFactory<BugWithBLOBs> {

            private static final long serialVersionUID = 1L;

            public EditFormFieldFactory(GenericBeanForm<BugWithBLOBs> form) {
                super(form);
            }

            @Override
            protected Field<?> onCreateField(final Object propertyId) {
                if (propertyId.equals("resolution")) {
                    ResolvedInputWindow.this.bug
                            .setResolution(BugResolution.Fixed.name());
                    return BugResolutionComboBox
                            .getInstanceForResolvedBugWindow();
                } else if (propertyId.equals("assignuser")) {
                    ResolvedInputWindow.this.bug
                            .setAssignuser(ResolvedInputWindow.this.bug
                                    .getLogby());
                    return new ProjectMemberSelectionField();
                } else if (propertyId.equals("fixedVersions")) {
                    ResolvedInputWindow.this.fixedVersionSelect = new VersionMultiSelectField();
                    return ResolvedInputWindow.this.fixedVersionSelect;
                } else if (propertyId.equals("comment")) {
                    EditForm.this.commentArea = new RichTextArea();
                    EditForm.this.commentArea.setNullRepresentation("");
                    return EditForm.this.commentArea;
                }

                return null;
            }
        }
    }
}
