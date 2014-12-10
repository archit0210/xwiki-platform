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
package org.xwiki.lesscss;

import org.xwiki.model.reference.ObjectPropertyReference;
import org.xwiki.stability.Unstable;

/**
 * A reference to a LESS resource containing in the wiki.
 *
 * @since 6.4M2
 * @version $Id$
 */
@Unstable
public class LESSEntityResourceReference implements LESSResourceReference
{
    private ObjectPropertyReference objectPropertyReference;

    /**
     * Constructor.
     * @param objectPropertyReference reference to the property of an XObject storing some LESS code
     */
    public LESSEntityResourceReference(ObjectPropertyReference objectPropertyReference)
    {
        this.objectPropertyReference = objectPropertyReference;
    }

    /**
     * @return the reference to a property of an XObject storing some LESS code
     */
    public ObjectPropertyReference getObjectPropertyReference()
    {
        return objectPropertyReference;
    }

    @Override
    public boolean equals(Object o)
    {
        return false;
    }

    @Override
    public int hashCode()
    {
        return 1;
    }
}
