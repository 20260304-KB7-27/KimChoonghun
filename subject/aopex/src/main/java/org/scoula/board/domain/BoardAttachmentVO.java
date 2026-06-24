package org.scoula.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.common.util.UploadFiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardAttachmentVO {
    private Long no;
    private Long bno;
    private String filename;
    private byte[] file;
    private String contentType;
    private Long size;
    private Date regDate;

    public static BoardAttachmentVO of(MultipartFile part, Long bno) throws IOException {
        return builder()
                .bno(bno)
                .filename(part.getOriginalFilename())
                .file(part.getBytes())
                .contentType(part.getContentType())
                .size(part.getSize())
                .build();
    }

    public String getFileSize() {
        return UploadFiles.getFormatSize(size);
    }
}
