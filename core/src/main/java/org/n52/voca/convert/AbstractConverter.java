/*
 * Copyright (C) 2019-2019 52Â°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.voca.convert;

import org.n52.sos.predefined.AbstractPredefined;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;


public abstract class AbstractConverter<T extends AbstractPredefined<?>, S> {

    private static JsonNodeFactory FACTORY = JsonNodeFactory.withExactBigDecimals(false);
    protected static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper()
                .setNodeFactory(FACTORY)
                .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .setSerializationInclusion(Include.NON_NULL);
    }

    protected AbstractPredefined<?> convert(S data) {
        return convert(data, "unknown");
    }

    protected abstract AbstractPredefined<?> convert(S data, String name);

}
