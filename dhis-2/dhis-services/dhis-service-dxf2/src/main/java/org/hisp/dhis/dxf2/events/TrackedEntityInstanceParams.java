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
package org.hisp.dhis.dxf2.events;

import lombok.Value;
import lombok.With;

/**
 * @author Morten Olav Hansen <mortenoh@gmail.com>
 */
@With
@Value
public class TrackedEntityInstanceParams
{
    public static final TrackedEntityInstanceParams TRUE = new TrackedEntityInstanceParams( true,
        TrackedEntityInstanceEnrollmentParams.TRUE,
        true,
        true, false, false );

    public static final TrackedEntityInstanceParams FALSE = new TrackedEntityInstanceParams( false,
        TrackedEntityInstanceEnrollmentParams.FALSE, false,
        false, false, false );

    public static final TrackedEntityInstanceParams DATA_SYNCHRONIZATION = new TrackedEntityInstanceParams( true,
        TrackedEntityInstanceEnrollmentParams.TRUE,
        true, true, true, true );

    private boolean includeRelationships;

    private TrackedEntityInstanceEnrollmentParams teiEnrollmentParams;

    private boolean includeProgramOwners;

    private boolean includeAttributes;

    private boolean includeDeleted;

    private boolean dataSynchronizationQuery;

    public boolean isIncludeRelationships()
    {
        return includeRelationships;
    }

    public boolean isIncludeEnrollments()
    {
        return teiEnrollmentParams.isIncludeEnrollments();
    }

    public boolean isIncludeProgramOwners()
    {
        return includeProgramOwners;
    }

    public boolean isIncludeAttributes()
    {
        return includeAttributes;
    }

    public boolean isIncludeDeleted()
    {
        return includeDeleted;
    }

    public boolean isDataSynchronizationQuery()
    {
        return dataSynchronizationQuery;
    }

    public EnrollmentParams getEnrollmentParams()
    {
        return this.teiEnrollmentParams.getEnrollmentParams();
    }

    public TrackedEntityInstanceParams withEnrollmentParams( EnrollmentParams enrollmentParams )
    {
        return this.withTeiEnrollmentParams( getTeiEnrollmentParams().withEnrollmentParams( enrollmentParams ) );
    }

    public EventParams getEventParams()
    {
        return getEnrollmentParams().getEnrollmentEventsParams().getEventParams();
    }

    public TrackedEntityInstanceParams withEventParams( EventParams eventParams )
    {
        EnrollmentParams enrollmentParams = this.teiEnrollmentParams.getEnrollmentParams();

        EnrollmentEventsParams eventParamsToUpdate = enrollmentParams
            .getEnrollmentEventsParams()
            .withEventParams( eventParams );

        return withTeiEnrollmentParams( this.teiEnrollmentParams.withEnrollmentParams( enrollmentParams
            .withEnrollmentEventsParams( eventParamsToUpdate ) ) );
    }
}
