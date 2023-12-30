package com.cloudtextbe.cloudtextbe_ver2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used to carry the result of save and get request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    /** user's name */
    String userName;
    /** The target of interaction */
    String fileName;
    /** The actual context of file. */
    String context;
}
