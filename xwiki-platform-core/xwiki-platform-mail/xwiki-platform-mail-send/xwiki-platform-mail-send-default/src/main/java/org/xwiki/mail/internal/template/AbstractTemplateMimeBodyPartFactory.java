/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.mail.internal.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import org.xwiki.mail.MimeBodyPartFactory;
import org.xwiki.mail.internal.AbstractMimeBodyPartFactory;
import org.xwiki.model.reference.DocumentReference;

import com.xpn.xwiki.api.Attachment;

/**
 * Creates an Body Part from a Document Reference pointing to a Document containing an XWiki.Mail XObject (the first one
 * found is used).
 *
 * @version $Id$
 * @since 6.1RC1
 */
public abstract class AbstractTemplateMimeBodyPartFactory extends AbstractMimeBodyPartFactory<DocumentReference>
{
    @Inject
    @Named("text/html")
    private MimeBodyPartFactory<String> htmlBodyPartFactory;

    /**
     * @return the Template Manager instance to use, this allows passing either the default component implementation or
     * a secure one for scripts
     */
    protected abstract MailTemplateManager getTemplateManager();

    @Override
    public MimeBodyPart create(DocumentReference documentReference, Map<String, Object> parameters)
        throws MessagingException
    {
        Map<String, String> velocityVariables = (Map<String, String>) parameters.get("velocityVariables");

        String textContent = getTemplateManager().evaluate(documentReference, "text", velocityVariables);
        String htmlContent = getTemplateManager().evaluate(documentReference, "html", velocityVariables);

        Map<String, Object> htmlParameters = new HashMap<>();
        htmlParameters.put("alternate", textContent);

        String attachmentProperty = "attachments";
        List<Attachment> attachments = (List<Attachment>) parameters.get(attachmentProperty);
        if (attachments != null) {
            htmlParameters.put(attachmentProperty, attachments);
        }
        return this.htmlBodyPartFactory.create(htmlContent, htmlParameters);
    }
}
