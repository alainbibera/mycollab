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
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.EmailDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.IntDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.PhoneDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.TextAreaDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.UrlDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.crm.i18n.LeadI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public class LeadDefaultDynaFormLayoutFactory {
	public static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();

		DynaSection infoSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN)
				.orderIndex(0)
				.header(AppContext
						.getMessage(LeadI18nEnum.SECTION_LEAD_INFORMATION))
				.build();

		infoSection
				.addField(new TextDynaFieldBuilder()
						.fieldName("firstname")
						.displayName(
								AppContext
										.getMessage(LeadI18nEnum.FORM_FIRSTNAME))
						.fieldIndex(0).build());

		infoSection.addField(new EmailDynaFieldBuilder().fieldName("email")
				.displayName(AppContext.getMessage(LeadI18nEnum.FORM_EMAIL))
				.fieldIndex(1).build());

		infoSection.addField(new TextDynaFieldBuilder().fieldName("lastname")
				.displayName(AppContext.getMessage(LeadI18nEnum.FORM_LASTNAME))
				.fieldIndex(2).build());

		infoSection.addField(new PhoneDynaFieldBuilder()
				.fieldName("officephone")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_OFFICE_PHONE))
				.fieldIndex(3).build());

		infoSection.addField(new TextDynaFieldBuilder().fieldName("title")
				.displayName(AppContext.getMessage(LeadI18nEnum.FORM_TITLE))
				.fieldIndex(4).build());

		infoSection.addField(new PhoneDynaFieldBuilder().fieldName("mobile")
				.displayName(AppContext.getMessage(LeadI18nEnum.FORM_MOBILE))
				.fieldIndex(5).build());

		infoSection.addField(new TextDynaFieldBuilder()
				.fieldName("department")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_DEPARTMENT))
				.fieldIndex(6).build());

		infoSection.addField(new PhoneDynaFieldBuilder()
				.fieldName("otherphone")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_OTHER_PHONE))
				.fieldIndex(7).build());

		infoSection.addField(new TextDynaFieldBuilder()
				.fieldName("accountname")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_ACCOUNT_NAME))
				.fieldIndex(8).build());

		infoSection.addField(new PhoneDynaFieldBuilder().fieldName("fax")
				.displayName(AppContext.getMessage(LeadI18nEnum.FORM_FAX))
				.fieldIndex(9).build());

		infoSection.addField(new TextDynaFieldBuilder()
				.fieldName("source")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_LEAD_SOURCE))
				.fieldIndex(10).build());

		infoSection.addField(new UrlDynaFieldBuilder().fieldName("website")
				.displayName(AppContext.getMessage(LeadI18nEnum.FORM_WEBSITE))
				.fieldIndex(11).build());

		infoSection.addField(new TextDynaFieldBuilder().fieldName("industry")
				.displayName(AppContext.getMessage(LeadI18nEnum.FORM_INDUSTRY))
				.fieldIndex(12).build());

		infoSection.addField(new TextDynaFieldBuilder().fieldName("status")
				.displayName(AppContext.getMessage(LeadI18nEnum.FORM_STATUS))
				.fieldIndex(13).build());

		infoSection.addField(new IntDynaFieldBuilder()
				.fieldName("noemployees")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_NO_EMPLOYEES))
				.fieldIndex(14).build());

		infoSection
				.addField(new TextDynaFieldBuilder()
						.fieldName("assignuser")
						.displayName(
								AppContext
										.getMessage(GenericI18Enum.FORM_ASSIGNEE))
						.fieldIndex(15).build());

		defaultForm.addSection(infoSection);

		DynaSection addressSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(1)
				.header(AppContext.getMessage(LeadI18nEnum.SECTION_ADDRESS))
				.build();

		addressSection.addField(new TextDynaFieldBuilder()
				.fieldName("primaddress")
				.displayName(
						AppContext
								.getMessage(LeadI18nEnum.FORM_PRIMARY_ADDRESS))
				.fieldIndex(0).build());

		addressSection.addField(new TextDynaFieldBuilder()
				.fieldName("otheraddress")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_OTHER_ADDRESS))
				.fieldIndex(1).build());

		addressSection.addField(new TextDynaFieldBuilder()
				.fieldName("primcity")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_PRIMARY_CITY))
				.fieldIndex(2).build());

		addressSection.addField(new TextDynaFieldBuilder()
				.fieldName("othercity")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_OTHER_CITY))
				.fieldIndex(3).build());

		addressSection.addField(new TextDynaFieldBuilder()
				.fieldName("primstate")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_PRIMARY_STATE))
				.fieldIndex(4).build());

		addressSection.addField(new TextDynaFieldBuilder()
				.fieldName("otherstate")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_OTHER_STATE))
				.fieldIndex(5).build());

		addressSection
				.addField(new TextDynaFieldBuilder()
						.fieldName("primpostalcode")
						.displayName(
								AppContext
										.getMessage(LeadI18nEnum.FORM_PRIMARY_POSTAL_CODE))
						.fieldIndex(6).build());

		addressSection
				.addField(new TextDynaFieldBuilder()
						.fieldName("otherpostalcode")
						.displayName(
								AppContext
										.getMessage(LeadI18nEnum.FORM_OTHER_POSTAL_CODE))
						.fieldIndex(7).build());

		addressSection.addField(new TextDynaFieldBuilder()
				.fieldName("primcountry")
				.displayName(
						AppContext
								.getMessage(LeadI18nEnum.FORM_PRIMARY_COUNTRY))
				.fieldIndex(8).build());

		addressSection.addField(new TextDynaFieldBuilder()
				.fieldName("othercountry")
				.displayName(
						AppContext.getMessage(LeadI18nEnum.FORM_OTHER_COUNTRY))
				.fieldIndex(9).build());

		defaultForm.addSection(addressSection);

		DynaSection descSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN)
				.orderIndex(2)
				.header(AppContext.getMessage(LeadI18nEnum.SECTION_DESCRIPTION))
				.build();

		descSection.addField(new TextAreaDynaFieldBuilder()
				.fieldName("description")
				.displayName(
						AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION))
				.fieldIndex(0).build());

		defaultForm.addSection(descSection);
	}

	public static DynaForm getForm() {
		return defaultForm;
	}
}
