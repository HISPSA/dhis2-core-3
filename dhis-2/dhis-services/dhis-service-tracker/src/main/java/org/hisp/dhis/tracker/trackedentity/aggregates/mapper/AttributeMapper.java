/*
 * Copyright (c) 2004-2022, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.hisp.dhis.tracker.trackedentity.aggregates.mapper;

import static org.hisp.dhis.tracker.trackedentity.aggregates.query.TeiAttributeQuery.COLUMNS.ATTR_CODE;
import static org.hisp.dhis.tracker.trackedentity.aggregates.query.TeiAttributeQuery.COLUMNS.ATTR_NAME;
import static org.hisp.dhis.tracker.trackedentity.aggregates.query.TeiAttributeQuery.COLUMNS.ATTR_SKIP_SYNC;
import static org.hisp.dhis.tracker.trackedentity.aggregates.query.TeiAttributeQuery.COLUMNS.ATTR_UID;
import static org.hisp.dhis.tracker.trackedentity.aggregates.query.TeiAttributeQuery.COLUMNS.ATTR_VALUE_TYPE;
import static org.hisp.dhis.tracker.trackedentity.aggregates.query.TeiAttributeQuery.COLUMNS.CREATED;
import static org.hisp.dhis.tracker.trackedentity.aggregates.query.TeiAttributeQuery.COLUMNS.STOREDBY;
import static org.hisp.dhis.tracker.trackedentity.aggregates.query.TeiAttributeQuery.COLUMNS.UPDATED;
import static org.hisp.dhis.tracker.trackedentity.aggregates.query.TeiAttributeQuery.COLUMNS.VALUE;
import static org.hisp.dhis.tracker.trackedentity.aggregates.query.TeiAttributeQuery.getColumnName;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hisp.dhis.common.ValueType;
import org.hisp.dhis.trackedentity.TrackedEntityAttribute;
import org.hisp.dhis.trackedentityattributevalue.TrackedEntityAttributeValue;

public interface AttributeMapper
{
    default TrackedEntityAttributeValue getAttribute( ResultSet rs )
        throws SQLException
    {
        TrackedEntityAttributeValue attributeValue = new TrackedEntityAttributeValue();

        attributeValue.setCreated( rs.getTimestamp( getColumnName( CREATED ) ) );
        attributeValue.setLastUpdated( rs.getTimestamp( getColumnName( UPDATED ) ) );
        attributeValue.setValue( rs.getString( getColumnName( VALUE ) ) );
        attributeValue.setStoredBy( rs.getString( getColumnName( STOREDBY ) ) );

        TrackedEntityAttribute attribute = new TrackedEntityAttribute();
        attribute.setUid( rs.getString( getColumnName( ATTR_UID ) ) );
        attribute.setName( rs.getString( getColumnName( ATTR_NAME ) ) );
        attribute.setCode( rs.getString( getColumnName( ATTR_CODE ) ) );
        attribute.setValueType( ValueType.fromString( rs.getString( getColumnName( ATTR_VALUE_TYPE ) ) ) );
        attribute.setSkipSynchronization( rs.getBoolean( getColumnName( ATTR_SKIP_SYNC ) ) );
        attributeValue.setAttribute( attribute );

        return attributeValue;
    }
}