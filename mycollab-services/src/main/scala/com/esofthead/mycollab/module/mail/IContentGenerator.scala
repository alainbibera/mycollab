/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.mail

import java.util.Locale

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
trait IContentGenerator {
    /**
     *
     * @param key
     * @param value
     */
    def putVariable(key: String, value: Any)

    /**
     *
     * @param subject
     * @return
     */
    def generateSubjectContent(subject: String): String

    /**
     *
     * @param templateFilePath
     * @return
     */
    def generateBodyContent(templateFilePath: String): String

    /**
     *
     * @param templateFilePath
     * @param currentLocale
     * @return
     */
    def generateBodyContent(templateFilePath: String, currentLocale: Locale): String

    /**
     *
     * @param templateFilePath
     * @param currentLocale
     * @param defaultLocale
     * @return
     */
    def generateBodyContent(templateFilePath: String, currentLocale: Locale, defaultLocale: Locale): String
}
