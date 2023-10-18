
package com.getunin.entity;



import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "name_file")
    private String nameFile;

    @Column(name = "url")
    private String url;

    @OneToOne
    @JoinColumn(name = "file_type")
    private Parameter fileType;

    @Column(name = "qr_url")
    private String qrUrl;

    @Column(name = "qr_name")
    private String qrName;

}
