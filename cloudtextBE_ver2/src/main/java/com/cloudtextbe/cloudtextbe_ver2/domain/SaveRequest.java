package com.cloudtextbe.cloudtextbe_ver2.domain;

import lombok.Data;

/**
 * This class is used to receive user's file saving request
 */
@Data
public class SaveRequest {
    /** user's name */
    String userName;
    /** The target of interaction */
    String fileName;
    /** The actual context of file. */
    String context;
}
