package com.ynd.core.dto

class EmailDTO implements Serializable {
    String toEmailId
    String toCCEmailId
    String toBccEmailId
    String body
    String subject
    Object attachment
    List<FileEmailDTO> fileEmailDTOs
}
