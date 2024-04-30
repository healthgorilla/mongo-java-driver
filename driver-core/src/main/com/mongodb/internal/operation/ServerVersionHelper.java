/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.internal.operation;


import com.mongodb.connection.ConnectionDescription;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;

/**
 * This class is NOT part of the public API. It may change at any time without notification.
 */
public final class ServerVersionHelper {
    public static final Logger HG_LOGGER = Loggers.getLogger("hg");

    public static final int THREE_DOT_ZERO_WIRE_VERSION = 3;
    public static final int THREE_DOT_TWO_WIRE_VERSION = 4;
    public static final int THREE_DOT_FOUR_WIRE_VERSION = 5;
    public static final int THREE_DOT_SIX_WIRE_VERSION = 6;
    public static final int FOUR_DOT_ZERO_WIRE_VERSION = 7;
    public static final int FOUR_DOT_TWO_WIRE_VERSION = 8;
    public static final int FOUR_DOT_FOUR_WIRE_VERSION = 9;

    public static boolean serverIsAtLeastVersionThreeDotZero(final ConnectionDescription description) {
        return !serverIsLessThanVersionThreeDotZero(description);
    }

    public static boolean serverIsAtLeastVersionThreeDotTwo(final ConnectionDescription description) {
        return !serverIsLessThanVersionThreeDotTwo(description);
    }

    public static boolean serverIsAtLeastVersionThreeDotFour(final ConnectionDescription description) {
        return !serverIsLessThanVersionThreeDotFour(description);
    }

    public static boolean serverIsAtLeastVersionThreeDotSix(final ConnectionDescription description) {
        return !serverIsLessThanVersionThreeDotSix(description);
    }

    public static boolean serverIsAtLeastVersionFourDotZero(final ConnectionDescription description) {
        return !serverIsLessThanVersionFourDotZero(description);
    }

    public static boolean serverIsAtLeastVersionFourDotTwo(final ConnectionDescription description) {
        return !serverIsLessThanVersionFourDotTwo(description);
    }

    public static boolean serverIsAtLeastVersionFourDotFour(final ConnectionDescription description) {
        return !serverIsLessThanVersionFourDotFour(description);
    }

    public static boolean serverIsLessThanVersionThreeDotZero(final ConnectionDescription description) {
        return wrapAndLogServerIsLessThanExpectedVersion(description.getMaxWireVersion(), THREE_DOT_ZERO_WIRE_VERSION);
    }

    public static boolean serverIsLessThanVersionThreeDotTwo(final ConnectionDescription description) {
        return wrapAndLogServerIsLessThanExpectedVersion(description.getMaxWireVersion(), THREE_DOT_TWO_WIRE_VERSION);
    }

    public static boolean serverIsLessThanVersionThreeDotFour(final ConnectionDescription description) {
        return wrapAndLogServerIsLessThanExpectedVersion(description.getMaxWireVersion(), THREE_DOT_FOUR_WIRE_VERSION);
    }

    public static boolean serverIsLessThanVersionThreeDotSix(final ConnectionDescription description) {
        return wrapAndLogServerIsLessThanExpectedVersion(description.getMaxWireVersion(), THREE_DOT_SIX_WIRE_VERSION);
    }

    public static boolean serverIsLessThanVersionFourDotZero(final ConnectionDescription description) {
        return wrapAndLogServerIsLessThanExpectedVersion(description.getMaxWireVersion(), FOUR_DOT_ZERO_WIRE_VERSION);
    }

    public static boolean serverIsLessThanVersionFourDotTwo(final ConnectionDescription description) {
        return wrapAndLogServerIsLessThanExpectedVersion(description.getMaxWireVersion(), FOUR_DOT_TWO_WIRE_VERSION);
    }

    public static boolean serverIsLessThanVersionFourDotFour(final ConnectionDescription description) {
        return wrapAndLogServerIsLessThanExpectedVersion(description.getMaxWireVersion(), FOUR_DOT_FOUR_WIRE_VERSION);
    }

    public static void logMessageAndPrintStackTrace(String logMessage, String errorMessage) {
        HG_LOGGER.warn(logMessage, new Exception(errorMessage));
    }

    public static boolean wrapAndLogServerIsLessThanExpectedVersion(final int maxWireVersion, final int expectedMaxWireVersion) {
        boolean isIncompatible = maxWireVersion < expectedMaxWireVersion;
        if (isIncompatible) {
            logMessageAndPrintStackTrace("ServerVersionHelper: Server version is not at least " + getWireVersionFromInt(expectedMaxWireVersion) + ", but is " + getWireVersionFromInt(maxWireVersion), "Unsupported server command");
        }
        return isIncompatible;
    }

    private static String getWireVersionFromInt(int wireVersion) {
        switch (wireVersion) {
            case THREE_DOT_ZERO_WIRE_VERSION:
                return "3.0";
            case THREE_DOT_TWO_WIRE_VERSION:
                return "3.2";
            case THREE_DOT_FOUR_WIRE_VERSION:
                return "3.4";
            case THREE_DOT_SIX_WIRE_VERSION:
                return "3.6";
            case FOUR_DOT_ZERO_WIRE_VERSION:
                return "4.0";
            case FOUR_DOT_TWO_WIRE_VERSION:
                return "4.2";
            case FOUR_DOT_FOUR_WIRE_VERSION:
                return "4.4";
            default:
                return "unknown";
        }
    }

    private ServerVersionHelper() {
    }
}
