package com.cloudtextbe.cloudtextbe_ver2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class is made to carry file list.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileListResponse {
    String userName;
    List<String> list;
}
