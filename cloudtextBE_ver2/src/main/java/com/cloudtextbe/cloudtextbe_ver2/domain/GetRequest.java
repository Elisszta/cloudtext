package com.cloudtextbe.cloudtextbe_ver2.domain;

import lombok.Data;

/**
 * This class is used to receive user's file download / file list request
 */
@Data
public class GetRequest {
    /** user's name */
    String userName;
    /** The target of interaction */
    String fileName;
}
